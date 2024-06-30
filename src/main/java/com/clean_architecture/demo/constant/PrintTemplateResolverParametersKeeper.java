package com.clean_architecture.demo.constant;

import com.clean_architecture.demo.util.PrintTemplateResolverV2;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.clean_architecture.demo.constant.Country.BELARUS;
import static com.clean_architecture.demo.constant.Country.KAZAKHSTAN;
import static com.clean_architecture.demo.constant.Country.RUSSIA;

/**
 * Хранитель совокупности параметров для выбора шаблона печати.
 * Порядок расположения элементов важен - чем выше в списке, тем выше приоритет.
 */
@Getter
@AllArgsConstructor
public enum PrintTemplateResolverParametersKeeper {

    /**
     * Казахстан Базовый
     */
    KZ_BASIC(PrintTemplateConverterNames.KZ_BASIC, KAZAKHSTAN, null, KAZAKHSTAN, null, null),

    /**
     * Казахстан Расширенный
     */
    KZ_EXTENDED(PrintTemplateConverterNames.KZ_EXTENDED, null, null, RUSSIA, Boolean.FALSE, List.of(2L)),

    /**
     * Казахстан Расширенный с самозанятым лицом
     */
    KZ_EXTENDED_SELF_EMPLOYEE(PrintTemplateConverterNames.KZ_EXTENDED_SELF_EMPLOYEE, null, null, RUSSIA, Boolean.TRUE, List.of(2L)),

    /**
     * Беларусь Базовый
     */
    BY_BASIC(PrintTemplateConverterNames.BY_BASIC, null, BELARUS, BELARUS, null, List.of(1L)),

    /**
     * Россия Базовый
     */
    RU_BASIC(PrintTemplateConverterNames.RU_BASIC, null, null, null, null, null)

    // И тому подобные константы
    ;

    private final PrintTemplateConverterNames template;
    private final Country officeCountryCode;
    private final Country legalEntityCountryCode;
    private final Country contragentCountryCode;
    private final Boolean isSelfEmployedContragent;
    private final List<Long> contractTypeCodes;


    @Nonnull
    public static PrintTemplateConverterNames getSinglePrintTemplateConverterNames(
            @Nonnull PrintTemplateResolverV2.PrintTemplateConverterParameters parameters) {
        Objects.requireNonNull(parameters);
        return Arrays.stream(PrintTemplateResolverParametersKeeper.values())
                .filter(v -> matchPrintTemplateConverterName(v, parameters))
                .findFirst()
                .orElseThrow()
                .getTemplate();
    }

    private static boolean matchPrintTemplateConverterName(
            @Nonnull PrintTemplateResolverParametersKeeper parametersKeeper,
            @Nonnull PrintTemplateResolverV2.PrintTemplateConverterParameters parametersDbo) {
        return isEqualOffice(parametersKeeper, parametersDbo) &&
                isEqualLegalEntity(parametersKeeper, parametersDbo) &&
                isEqualCounterpartyCountry(parametersKeeper, parametersDbo) &&
                isEqualSelfEmployedCounterparty(parametersKeeper, parametersDbo) &&
                isEqualContractType(parametersKeeper, parametersDbo);
    }

    private static boolean isEqualOffice(
            @Nonnull PrintTemplateResolverParametersKeeper parametersKeeper,
            @Nonnull PrintTemplateResolverV2.PrintTemplateConverterParameters parametersDbo) {
        return parametersKeeper.officeCountryCode == null ||
                parametersKeeper.officeCountryCode.equals(parametersDbo.getOfficeCountry());
    }

    private static boolean isEqualLegalEntity(
            @Nonnull PrintTemplateResolverParametersKeeper parametersKeeper,
            @Nonnull PrintTemplateResolverV2.PrintTemplateConverterParameters parametersDbo) {
        return parametersKeeper.legalEntityCountryCode == null ||
                parametersKeeper.legalEntityCountryCode.equals(parametersDbo.getLegalEntityCountry());
    }

    private static boolean isEqualCounterpartyCountry(
            @Nonnull PrintTemplateResolverParametersKeeper parametersKeeper,
            @Nonnull PrintTemplateResolverV2.PrintTemplateConverterParameters parametersDbo) {
        return parametersKeeper.contragentCountryCode == null ||
                parametersKeeper.contragentCountryCode.equals(parametersDbo.getCounterpartyCountry());
    }

    private static boolean isEqualSelfEmployedCounterparty(
            @Nonnull PrintTemplateResolverParametersKeeper parametersKeeper,
            @Nonnull PrintTemplateResolverV2.PrintTemplateConverterParameters parametersDbo) {
        return parametersKeeper.isSelfEmployedContragent == null ||
                parametersKeeper.isSelfEmployedContragent == parametersDbo.isSelfEmployedCounterparty();
    }

    private static boolean isEqualContractType(
            @Nonnull PrintTemplateResolverParametersKeeper parametersKeeper,
            @Nonnull PrintTemplateResolverV2.PrintTemplateConverterParameters parametersDbo) {
        return parametersKeeper.contractTypeCodes == null ||
                parametersKeeper.contractTypeCodes.contains(parametersDbo.getContractTypeId());
    }
}
