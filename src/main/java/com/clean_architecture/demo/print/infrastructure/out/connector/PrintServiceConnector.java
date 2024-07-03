package com.clean_architecture.demo.print.infrastructure.out.connector;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Компонент, выполняющий вызовы к внешнему сервису печати
 */
@Component
public class PrintServiceConnector {

    /**
     * Получает сформированный файл по переданному запросу и адресу
     */
    public byte[] getAsByteArray(String templateFileName, Map<String, Object> tags) {
        // Какая-то логика с запросом во внешний сервис
        return "bytes".getBytes();
    }
}
