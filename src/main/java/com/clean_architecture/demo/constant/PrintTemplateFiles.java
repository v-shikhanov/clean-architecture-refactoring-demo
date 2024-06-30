package com.clean_architecture.demo.constant;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Шаблоны документов печати договоров
 */
@Getter
@AllArgsConstructor
public enum PrintTemplateFiles {

    /**
     * Россия - Шаблон договора ИМ (используется также в случае отбраковки других шаблонов)
     */
    RU_INTERNET_STORE("ru.internet_store", "contract/RU_InternetStoreContract.odt"),

    /**
     * Россия - Шаблон договора ИМ для самозанятого
     */
    RU_INTERNET_STORE_SELF_EMPLOYED("ru_self_employed.internet_store", "contract/RU_Self_Employed_InternetStoreContract.odt"),

    /**
     * Беларусь - Шаблон договора Курьерских услуг
     */
    BY_COURIER("by.courier", "contract/BY_CourierContract.odt"),

    /**
     * Казахстан - Шаблон договора КУ
     */
    KZ_COURIER("kz.client", "contract/KZ_CourierContract.odt"),

    /**
     * Казахстан - Шаблон договора ИМ
     */
    KZ_INTERNET_STORE("kz.internet_store", "contract/KZ_InternetStoreContract.odt"),

    /**
     * Казахстан - Шаблон договора ИМ Расширенный (с определенным Юридическим лицом со стороны СДЭК)
     */
    KZ_INTERNET_STORE_EXTENDED("kz_ext.internet_store", "contract/KZ_ext_InternetStoreContract.odt"),

    // И так далее
    ;


    private final String templateName;
    private final String fileName;

    @Nonnull
    public static String getFileNameByTemplate(@Nonnull String templateName) {
        return Arrays.stream(PrintTemplateFiles.values())
                .filter(v -> v.getTemplateName().equals(templateName))
                .findFirst()
                .map(PrintTemplateFiles::getFileName)
                .orElseThrow(() -> new UnsupportedOperationException(
                        "Template for contract with such type and templateName does not exist"));
    }
}
