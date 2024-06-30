package com.clean_architecture.demo.controller;

import com.clean_architecture.demo.constant.FileFormat;
import com.clean_architecture.demo.dto.DocumentBase64Dto;
import com.clean_architecture.demo.service.PrintTemplatesRegisterService;
import com.clean_architecture.demo.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PrintControllerV1 {

    private final TemplateService templateService;
    private final PrintTemplatesRegisterService printTemplatesRegisterService;

    @GetMapping("/v1/contract/print/{id}")
    public DocumentBase64Dto printById(
            @PathVariable Long id,
            @RequestHeader("Accept-Language") String lang) {

        // Какая-то логика с авторизацией

        printServiceReadinessValidation();
        final FileFormat format = FileFormat.resolve(null);
        final Pair<String, String> resultLinkToResultFilenamePair =
                templateService.getDocFileByContractId(id, format, lang);
        return new DocumentBase64Dto(resultLinkToResultFilenamePair.getLeft(),
                resultLinkToResultFilenamePair.getRight());
    }

    private void printServiceReadinessValidation() {
        if (!printTemplatesRegisterService.isDocReportReady()) {
            throw new UnsupportedOperationException("Templates were not loaded");
        }
    }
}
