package com.clean_architecture.demo.print.application.value;

import com.clean_architecture.demo.print.domain.entity.Contract;
import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Value объект, содержащий результат печати
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class PrintResult {

    /**
     * Имя распечатанного документа
     */
    private final String name;

    /**
     * Сам документ, в виде строки
     */
    private final String content;

    @Nonnull
    public static PrintResult getInstance(
            @Nonnull Contract contract,
            @Nonnull String content) {
        return new PrintResult(buildName(contract), content);
    }

    @Nonnull
    private static String buildName(@Nonnull Contract contract) {
        var number = Optional.ofNullable(contract.getNumber())
                .filter(StringUtils::isNotBlank)
                .orElse("WITHOUT_NUMBER");
        return String.format("%s.doc", number);
    }
}
