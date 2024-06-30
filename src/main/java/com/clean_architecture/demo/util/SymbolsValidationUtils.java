package com.clean_architecture.demo.util;

import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;

/**
 * Утильный класс для валидации символов
 */
public class SymbolsValidationUtils {

    /**
     * Валидирует символы в имени файла и удаляет запрещённые
     */
    @Nonnull
    public static String removeInvalidFileSymbols(@Nonnull String transliteratedFileName) {
        // Выполняет какую-то логику
        return StringUtils.EMPTY;
    }
}
