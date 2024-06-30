package com.clean_architecture.demo.constant;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Константы стран для шаблона печати
 */
@Getter
@AllArgsConstructor
public enum PrintTemplateConverterNames {

    /**
     * Россия Базовый
     */
    RU_BASIC("ru", "ruBasicTemplateConverter", RegionalAffiliation.RUSSIAN_DRAWN_UP),

    /**
     * Россия с самозанятым лицом
     */
    RU_SELF_EMPLOYED("ru_self_employed", "ruSelfEmployedTemplateConverter", RegionalAffiliation.RUSSIAN_DRAWN_UP),

    /**
     * Беларусь Базовый
     */
    BY_BASIC("by", "byBasicTemplateConverter", RegionalAffiliation.RUSSIAN_DRAWN_UP),

    /**
     * Казахстан Базовый
     */
    KZ_BASIC("kz", "kzBasicTemplateConverter", RegionalAffiliation.RUSSIAN_DRAWN_UP),

    /**
     * Казахстан Расширенный
     */
    KZ_EXTENDED("kz_ext", "kzExtendedTemplateConverter", RegionalAffiliation.RUSSIAN_DRAWN_UP),
    /**
     * Казахстан Расширенный с самозанятым лицом
     */
    KZ_EXTENDED_SELF_EMPLOYEE("kz_ext_self_employee", "kzExtendedTemplateConverter",
            RegionalAffiliation.RUSSIAN_DRAWN_UP)

    // И так далее
    ;

    private final String code;
    private final String converterName;
    private final RegionalAffiliation regionalAffiliation;

    public static boolean isInternationalTemplate(@Nonnull PrintTemplateConverterNames template) {
        return Objects.requireNonNull(template)
                .getRegionalAffiliation()
                .equals(RegionalAffiliation.INTERNATIONAL);
    }

    /**
     * Региональная принадлежность шаблона для выделения международных договоров
     */
    private enum RegionalAffiliation {
        RUSSIAN_DRAWN_UP, // Оформленные на русском языке
        INTERNATIONAL
    }
}
