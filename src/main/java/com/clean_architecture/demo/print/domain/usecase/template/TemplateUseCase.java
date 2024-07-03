package com.clean_architecture.demo.print.domain.usecase.template;

import com.clean_architecture.demo.print.domain.constant.TemplateType;
import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.usecase.tag.TagUseCase;
import jakarta.annotation.Nonnull;

import java.util.Set;

/**
 * Юзкейс шаблона печати.
 * <p>Умеет определять подходит ли он для печати конкретного договора.
 */
public interface TemplateUseCase {

    @Nonnull
    Set<Class<? extends TagUseCase>> getTemplateTags();

    @Nonnull
    TemplateType getType();

    boolean isMatched(@Nonnull Contract contract);
}
