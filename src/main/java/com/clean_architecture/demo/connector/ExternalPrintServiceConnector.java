package com.clean_architecture.demo.connector;

import com.clean_architecture.demo.dto.PrintRequestDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Компонент, выполняющий вызовы к внешнему сервису печати
 */
@Component
public class ExternalPrintServiceConnector {

    /**
     * Получает сформированный файл по переданному запросу и адресу
     */
    public Resource loadFromDocReportToResource(String printEndpointName, PrintRequestDto dto) {
        // Какая-то логика с запросом во внешний сервис
        return new ByteArrayResource("bytes".getBytes());
    }
}
