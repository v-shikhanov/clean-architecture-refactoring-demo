package com.clean_architecture.demo.print.infrastructure.out.constant;

import com.clean_architecture.demo.print.domain.constant.TemplateType;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Константа, связывающая конкретные файлы шаблонов печати с их типами из доменного слоя
 */
@Getter
@RequiredArgsConstructor
public enum PrintTemplateFiles {

    BY_CLIENT(TemplateType.BY_COURIER, "/contract/v3/BY_CourierContract.odt"),
    KZ_CLIENT(TemplateType.KZ_COURIER, "/contract/v4/KZ_CourierContract.odt"),
    KZ_INTERNET_STORE(TemplateType.KZ_INTERNET_STORE, "/contract/v4/KZ_InternetStoreContract.odt"),
    RUS_CLIENT(TemplateType.RUS_COURIER, "/contract/v4/RU_CourierContract.odt"),
    RUS_INTERNET_STORE(TemplateType.RUS_INTERNET_STORE, "/contract/v4/RU_InternetStoreContract.odt"),
    RUS_SELF_EMPLOYED_INTERNET_STORE(TemplateType.RUS_SELF_EMPLOYED_INTERNET_STORE,
            "/contract/v3/RU_Self_Employed_InternetStoreContract.odt"),
    SUB_AGENCY(TemplateType.SUB_AGENCY,
            "/contract/v4/SubAgencyContract.odt");

    private static final Map<TemplateType, String> FILE_MAP = Arrays.stream(PrintTemplateFiles.values())
            .collect(Collectors.toMap(PrintTemplateFiles::getTemplateType, PrintTemplateFiles::getFileName));

    private final TemplateType templateType;
    private final String fileName;

    @Nullable
    public static String getFileNameByTemplateType(@Nonnull TemplateType templateType) {
        return FILE_MAP.get(templateType);
    }
}
