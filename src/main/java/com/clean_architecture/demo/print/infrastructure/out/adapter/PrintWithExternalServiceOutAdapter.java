package com.clean_architecture.demo.print.infrastructure.out.adapter;

import com.clean_architecture.demo.print.application.port.out.PrintOutPort;
import com.clean_architecture.demo.print.domain.constant.TemplateType;
import com.clean_architecture.demo.print.infrastructure.out.connector.PrintServiceConnector;
import com.clean_architecture.demo.print.infrastructure.out.constant.PrintTemplateFiles;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Map;


/**
 * Заполнение шаблона печати значениями тегов во внешнем сервисе
 */
@Component
@RequiredArgsConstructor
public class PrintWithExternalServiceOutAdapter implements PrintOutPort {

    private final PrintServiceConnector connector;

    @Nonnull
    @Override
    public String printTemplateWithTagsToFile(@Nonnull TemplateType template, @Nonnull Map<String, Object> tags) {
        var templateFileName = PrintTemplateFiles.getFileNameByTemplateType(template);
        return Base64.getEncoder().encodeToString(connector.getAsByteArray(templateFileName, tags));
    }
}
