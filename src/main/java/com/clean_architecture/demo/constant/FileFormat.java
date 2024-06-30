package com.clean_architecture.demo.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Доступные форматы для отчета по договору
 * <p>
 * По умолчанию используется расширение "doc" из контроллеров может прийти значение null,
 * при необходимости можно поменять на "pdf"
 */
@Getter
@RequiredArgsConstructor
public enum FileFormat {
    PDF("pdf"),
    DOC("doc");

    private final String fileExtension;

    public static FileFormat resolve(String format) {
        if (isBlank(format)) {
            return DOC; // значение по умолчанию
        }
        return valueOf(format.toUpperCase()); // Фактически, до этого места никогда не доходит
    }
}
