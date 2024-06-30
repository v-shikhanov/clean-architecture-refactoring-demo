package com.clean_architecture.demo.service;

import com.clean_architecture.demo.connector.ExternalPrintServiceConnector;
import com.clean_architecture.demo.constant.FileFormat;
import com.clean_architecture.demo.constant.PrintTemplateFiles;
import com.clean_architecture.demo.converter.TemplateConverter;
import com.clean_architecture.demo.converter.TemplateDtoToMapConverter;
import com.clean_architecture.demo.dao.ContractDao;
import com.clean_architecture.demo.dto.PrintRequestDto;
import com.clean_architecture.demo.entity.BaseContract;
import com.clean_architecture.demo.entity.Counterparty;
import com.clean_architecture.demo.util.PrintTemplateResolverV2;
import com.clean_architecture.demo.util.SymbolsValidationUtils;
import com.clean_architecture.demo.util.Transliterator;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Формирование заполненного шаблона договора:
 * - по стране и типу договора выбираем шаблон,
 * - из имени контрагента, номера договора и текущей даты формируем имя заполненного документа,
 * - создаем документ
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateService {

    /**
     * Определяется ограничением файловой системы на длину создаваемого файла в 255 символов. Название файла
     * разделено на три части со своими лимитами (название контрагента - 170 символов, номер договора - 50 символов,
     * остальная часть с запасом - 35 символов).
     */
    private static final int MAX_ALLOWED_LENGTH_FOR_NAME = 170;
    private static final int MAX_ALLOWED_LENGTH_FOR_NUMBER = 50;
    private static final String PRINT_PDF = "getPdfSynchScalable";
    private static final String PRINT_DOC = "getDoc";
    private final ContractFillerService contractFiller;
    private final ExternalPrintServiceConnector externalPrintConnector;
    private final ContractDao contractDao;
    private final CounterpartyService counterpartyService;
    private final PrintTemplateResolverV2 printTemplateResolverV2;
    private final Map<String, TemplateConverter> templateConverterMap;
    private final TemplateDtoToMapConverter templateDtoToMapConverter;

    /**
     * Печать договора по id
     * <p>
     * Возвращает пару (base64, filename),
     * где base64 это сформированный файл, а filename это его имя
     */
    @Nonnull
    public Pair<String, String> getDocFileByContractId(
            @Nonnull Long contractId,
            @Nonnull FileFormat format,
            @Nonnull String lang) {
        var contract = contractDao.get(contractId);
        Objects.requireNonNull(contract);
        return getDocReport(contract, format, lang);
    }

    /**
     * Печать договора
     * <p>
     * Возвращает пару (base64, filename),
     * где base64 это сформированный файл, а filename это его имя
     */
    @Nonnull
    private Pair<String, String> getDocReport(
            @Nonnull BaseContract contract, @Nonnull FileFormat format, @Nonnull String lang) {
        contractFiller.fillContractToTemplate(contract, lang);
        var printEndpointName = (format == FileFormat.DOC) ? PRINT_DOC : PRINT_PDF;
        var dto = getReportDto(contract, lang);
        var fileName = generateName(contract, format, lang);
        var resource = externalPrintConnector.loadFromDocReportToResource(printEndpointName, dto);
        return Pair.of(getBase64FromResource(resource), fileName);
    }

    @Nonnull
    private String getBase64FromResource(@Nonnull Resource resource) {
        Objects.requireNonNull(resource);
        try {
            return Base64.getEncoder().encodeToString(resource.getContentAsByteArray());
        } catch (IOException | NullPointerException e) {
            log.error("Error occurred during getting Base64 from Resource: {}", e.getMessage());
            throw new InternalError("Error occurred during getting Base64 from Resource", e);
        }
    }

    @Nonnull
    private PrintRequestDto getReportDto(
            @Nonnull BaseContract contract,
            @Nonnull String lang) {

        var reportDto = new PrintRequestDto();
        reportDto.setKey(contract.getNumber());

        var printTemplate = printTemplateResolverV2.resolve(contract);
        var templateName = String.format("%s.%s", printTemplate.getCode(), contract.getType()).toLowerCase();
        var getFileName = PrintTemplateFiles.getFileNameByTemplate(templateName);

        reportDto.setTemplateName(getFileName);

        var contragent = Optional.of(contract)
                .map(uuid -> counterpartyService.getByContract(contract, lang))
                .orElseThrow();

        var templateDto = templateConverterMap.get(printTemplate.getConverterName())
                .convert(contract, contragent, lang, printTemplate);
        reportDto.setData(templateDtoToMapConverter.toMap(templateDto));

        return reportDto;
    }

    @Nonnull
    private String generateName(@Nonnull BaseContract contract, @Nonnull FileFormat format, @Nonnull String lang) {
        var clientName = Optional.ofNullable(contract.getCounterparty())
                .map(Counterparty::getName)
                .filter(n -> n.length() <= MAX_ALLOWED_LENGTH_FOR_NAME)
                .orElse(EMPTY);
        var number = Optional.ofNullable(contract.getNumber())
                .filter(n -> n.length() <= MAX_ALLOWED_LENGTH_FOR_NUMBER)
                .orElse("WITHOUT_NUMBER");
        var fileName = String.format("contract_%s_%s_%s.%s",
                clientName, number, LocalDate.now(), format.getFileExtension());
        var transliteratedFileName = Transliterator.transliterateFileNameToEnglish(fileName);
        return SymbolsValidationUtils.removeInvalidFileSymbols(transliteratedFileName);
    }
}
