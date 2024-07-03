package com.clean_architecture.demo.print.application.port.out;

import com.clean_architecture.demo.print.domain.constant.TemplateType;
import jakarta.annotation.Nonnull;

import java.util.Map;

/**
 * Порт получения документа, заполненного значениями тэгов
 */
public interface PrintOutPort {

    /**
     * Заполняет выбранный шаблон переданными тэгами
     *
     * @return возвращает файл в Base64
     */
    @Nonnull
    String printTemplateWithTagsToFile(@Nonnull TemplateType template, @Nonnull Map<String, Object> tags);
}
