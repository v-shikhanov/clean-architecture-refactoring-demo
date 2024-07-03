package com.clean_architecture.demo.print.infrastructure.in;

import com.clean_architecture.demo.dto.DocumentBase64Dto;
import com.clean_architecture.demo.print.application.port.in.PrintContractInPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebPrintControllerV2 {

    private final PrintContractInPort printContractPort;

    /**
     * Печать договора по его идентификатору,
     * используя новую реализацию
     *
     * @return печатная форма договора в формате base64
     */
    @GetMapping("/v2/contract/print/{id}")
    public DocumentBase64Dto printById(@PathVariable Long id) {

        // Какая-то логика с авторизацией
        var printResult = printContractPort.printById(id);
        return new DocumentBase64Dto(printResult.getContent(), printResult.getName());
    }
}
