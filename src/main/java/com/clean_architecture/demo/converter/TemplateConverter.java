package com.clean_architecture.demo.converter;

import com.clean_architecture.demo.constant.ContractType;
import com.clean_architecture.demo.constant.PrintTemplateConverterNames;
import com.clean_architecture.demo.dto.TemplateDto;
import com.clean_architecture.demo.entity.BaseContract;
import com.clean_architecture.demo.entity.Counterparty;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

/**
 * Конвертер для шаблонов печати
 * <p>
 * Заполняет общие поля по договору,
 * наследники заполняют специфичные поля
 * для конкретного шаблона печати
 */
@RequiredArgsConstructor
public abstract class TemplateConverter {

    private static final String PRINT_UNDERSCORE = "____________";

    // Вспомогательные компоненты для заполнения полей, около 10 штук

    /**
     * Конвертация договора в транспорт шаблона печати
     *
     * @param contract      договор
     * @param lang          язык на котором пришёл запрос
     * @param printTemplate шаблон печати договора (@link PrintTemplate)
     * @return транспорт шаблона печати
     */
    public TemplateDto convert(
            @Nonnull BaseContract contract,
            @Nonnull Counterparty counterparty,
            @Nonnull String lang,
            @Nonnull PrintTemplateConverterNames printTemplate) {
        Objects.requireNonNull(contract);
        Objects.requireNonNull(counterparty);
        Objects.requireNonNull(lang);
        Objects.requireNonNull(printTemplate);

        var templateDto = new TemplateDto();
        var printTemplateCode = printTemplate.getCode();
        if (PrintTemplateConverterNames.isInternationalTemplate(printTemplate)) {
            setBasicFieldsForAnyTemplate(contract, counterparty, templateDto, lang);
            templateDto.setNumber(Optional.ofNullable(contract.getNumber())
                    .orElse(PRINT_UNDERSCORE));
            setInternationalTemplateFields(contract, counterparty, lang, templateDto, printTemplateCode);
            return templateDto;
        }

        setGeneralFields(contract, counterparty, templateDto, lang, printTemplateCode);
        setSpecificFields(contract, counterparty, lang, templateDto, printTemplateCode);

        if (ContractType.INTERNET_STORE_CONTRACT.equals(contract.getType())) {
            setGeneralFieldsForInternetStore(contract, templateDto);
            setSpecificFieldsForInternetStore(contract, counterparty, lang, templateDto, printTemplateCode);
        }
        return templateDto;
    }

    private void setGeneralFields(
            @Nonnull BaseContract contract,
            @Nonnull Counterparty counterparty,
            @Nonnull TemplateDto templateDto,
            @Nonnull String lang,
            @Nonnull String printTemplateCode) {
        setBasicFieldsForAnyTemplate(contract, counterparty, templateDto, lang);
        // Вызовы вспомогательных компонентов, доставание значений из вложенных сущностей
    }

    private void setBasicFieldsForAnyTemplate(BaseContract contract, Counterparty counterparty, TemplateDto templateDto,
            String lang) {
        // Вызовы вспомогательных компонентов, доставание значений из вложенных сущностей

    }

    private void setGeneralFieldsForInternetStore(@Nonnull BaseContract contract, @Nonnull TemplateDto templateDto) {
        // Вызовы вспомогательных компонентов, доставание значений из вложенных сущностей
    }

    public void setSpecificFields(
            BaseContract contract,
            Counterparty cdek,
            String lang,
            TemplateDto templateDto,
            String printTemplateCode) {
        // Вызовы вспомогательных компонентов, доставание значений из вложенных сущностей
    }

    public void setSpecificFieldsForInternetStore(
            BaseContract contract,
            Counterparty cdek,
            String lang,
            TemplateDto templateDto,
            String printTemplateCode) {
        // Вызовы вспомогательных компонентов, доставание значений из вложенных сущностей
    }

    public void setInternationalTemplateFields(BaseContract contract,
            Counterparty cdek,
            String lang,
            TemplateDto templateDto,
            String printTemplateCode) {
        // Вызовы вспомогательных компонентов, доставание значений из вложенных сущностей
    }
}
