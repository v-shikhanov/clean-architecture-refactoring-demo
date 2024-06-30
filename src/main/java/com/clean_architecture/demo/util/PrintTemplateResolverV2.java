package com.clean_architecture.demo.util;

import com.clean_architecture.demo.constant.Country;
import com.clean_architecture.demo.constant.PrintTemplateConverterNames;
import com.clean_architecture.demo.constant.PrintTemplateResolverParametersKeeper;
import com.clean_architecture.demo.entity.BaseContract;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Сервис для выбора шаблона печати
 */
@Component
@RequiredArgsConstructor
public class PrintTemplateResolverV2 {

    @Nonnull
    public PrintTemplateConverterNames resolve(@Nonnull BaseContract contract) {
        Objects.requireNonNull(contract);

        var printTemplateParameters = PrintTemplateConverterParameters.builder()
                .officeCountry(getOfficeCountry(contract))
                .legalEntityCountry(getLegalEntityCountry(contract))
                .counterpartyCountry(getContragentCountry(contract))
                .isSelfEmployedCounterparty(isSelfEmployedContragentFlagMarked(contract))
                .contractTypeId(getContractTypeCode(contract))
                .build();
        return PrintTemplateResolverParametersKeeper
                .getSinglePrintTemplateConverterNames(printTemplateParameters);
    }

    private boolean isSelfEmployedContragentFlagMarked(BaseContract contract) {
        // Логика по определению флага самозанятости
        return false;
    }

    @Nonnull
    private Country getOfficeCountry(@Nonnull BaseContract contract) {
        // Логика по определению страны офиса, заключившего договор
        return Country.RUSSIA;
    }

    @Nonnull
    private Country getLegalEntityCountry(@Nonnull BaseContract contract) {
        // Логика по определению страны юридического лица
        return Country.RUSSIA;
    }

    @Nonnull
    private Country getContragentCountry(@Nonnull BaseContract contract) {
        // Логика по определению страны контрагента
        return Country.RUSSIA;
    }

    @Nonnull
    private static Long getContractTypeCode(@Nonnull BaseContract contract) {
        // Логика по определению типа договора
        return 0L;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrintTemplateConverterParameters {
        private Country officeCountry;
        private Country legalEntityCountry;
        private Country counterpartyCountry;
        private boolean isSelfEmployedCounterparty;
        private Long contractTypeId;
    }
}
