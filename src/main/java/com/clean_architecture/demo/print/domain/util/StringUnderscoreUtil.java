package com.clean_architecture.demo.print.domain.util;


import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.function.Function;

/**
 * Утильный класс предоставляет набор методов позволяющий извлекать строку и в случае если она null,
 * пустая (в том числе состоит просто из пробела), заменять на прочерк
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUnderscoreUtil {

    public static final String UNDERSCORE = "____________";

    @Nonnull
    public static <T> String extractOrUnderscore(@Nullable T source, @Nonnull Function<T, String> extractor) {
        return Optional.ofNullable(source)
                .map(extractor)
                .filter(StringUtils::isNotBlank)
                .orElse(UNDERSCORE);
    }

    @Nonnull
    public static String valueOrUnderscore(@Nullable String string) {
        return StringUtils.defaultIfBlank(string, UNDERSCORE);
    }
}
