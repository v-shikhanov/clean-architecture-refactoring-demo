package com.clean_architecture.demo.print.domain.validation.impl;

import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.usecase.tag.TagUseCase;
import com.clean_architecture.demo.print.domain.usecase.template.TemplateUseCase;
import com.clean_architecture.demo.print.domain.validation.Validator;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Шаблонный метод для валидаторов, применяющих бизнес-правило в зависимости от набора тегов в шаблоне.
 * <p>
 * При наследовании, в конструктор класса должен быть передан список тегов, для которых будет применяться бизнес-правило
 * Если в шаблоне печати присутствует любой из этих тегов, будет выполнена валидация. В противном случае она будет
 * пропущена.
 */
@RequiredArgsConstructor
public abstract class SkipByTagsValidatorTemplateMethod implements Validator {

    private final List<Class<? extends TagUseCase>> tagsToValidate;
    private final String errorCode;

    public void validate(@Nonnull Contract contract, @Nonnull TemplateUseCase template) {
        if (isNeedToSkip(template) || isValid(contract)) {
            return;
        }

        throw new IllegalStateException(errorCode);
    }

    protected boolean isNeedToSkip(@Nonnull TemplateUseCase templateUseCase) {
        return !CollectionUtils.containsAny(templateUseCase.getTemplateTags(), tagsToValidate);
    }

    /**
     * Метод, внутри которого должна быть реализована проверка бизнес-правила
     */
    protected abstract boolean isValid(@Nonnull Contract contract);
}
