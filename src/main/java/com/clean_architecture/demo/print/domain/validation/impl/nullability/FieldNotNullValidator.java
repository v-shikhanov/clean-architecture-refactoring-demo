package com.clean_architecture.demo.print.domain.validation.impl.nullability;

import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.usecase.tag.TagUseCase;
import com.clean_architecture.demo.print.domain.validation.impl.SkipByTagsValidatorTemplateMethod;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * Абстрактный класс валидатора, проверяющего, что значение поля не null, если в шаблоне присутствует любой из тегов,
 * переданных в конструктор.
 */
public abstract class FieldNotNullValidator<T> extends SkipByTagsValidatorTemplateMethod {

    protected FieldNotNullValidator(
            @Nonnull List<Class<? extends TagUseCase>> tagsToValidate,
            @Nonnull String errorCode) {
        super(tagsToValidate, errorCode);
    }

    @Override
    protected boolean isValid(@Nonnull Contract contract) {
        var value = getFieldValue(contract);
        return Objects.nonNull(value);
    }

    /**
     * Метод извлекающий значение поля, которое должно быть провалидировано
     */
    @Nullable
    protected abstract T getFieldValue(@Nonnull Contract contract);
}
