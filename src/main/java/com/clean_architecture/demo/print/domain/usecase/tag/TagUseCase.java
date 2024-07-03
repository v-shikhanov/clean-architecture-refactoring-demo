package com.clean_architecture.demo.print.domain.usecase.tag;

import com.clean_architecture.demo.print.domain.entity.Contract;
import jakarta.annotation.Nonnull;

/**
 * Юзкейс печатаемого тега. Содержит название тега и метод по
 * извлечению значения из доменного объекта
 */
public interface TagUseCase {

    @Nonnull
    String getName();

    @Nonnull
    Object getValue(@Nonnull Contract contract);
}
