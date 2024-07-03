package com.clean_architecture.demo.print.domain.util;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtractUtil {

    @Nullable
    public static <T, R> R safeExtract(@Nullable T source, @Nonnull Function<T, R> extractor) {
        return Optional.ofNullable(source)
                .map(extractor)
                .orElse(null);
    }

    @Nonnull
    public static <T, R> R getOrDefault(@Nullable T object, @Nonnull Function<T, R> function,
            @Nonnull R defaultResult) {
        return Optional.ofNullable(object)
                .map(function)
                .orElse(defaultResult);
    }
}
