package com.clean_architecture.demo.print.domain.validation;


import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.usecase.template.TemplateUseCase;
import jakarta.annotation.Nonnull;

/**
 * Базовый класс валидатора для проверки сущности договора {@link Contract} напротив выбранного шаблона печати
 * {@link TemplateUseCase}.
 * <p>
 * Реализации интерфейса должны быть атомарными, выполняющими проверку одного бизнес-правила
 */
public interface Validator {

    void validate(@Nonnull Contract contract, @Nonnull TemplateUseCase template);
}
