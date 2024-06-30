package com.clean_architecture.demo.dto;

import lombok.Data;

import java.util.Map;

/**
 * Транспортный объект для запроса к внешнему сервису печати
 */
@Data
public class PrintRequestDto {

    /**
     * Идентификатор/ключ документа
     */
    private String key;

    /**
     * Название шаблона документа
     */
    private String templateName;

    /**
     * Данные для заполнения в шаблоне печати
     */
    private Map<String, Object> data;
}
