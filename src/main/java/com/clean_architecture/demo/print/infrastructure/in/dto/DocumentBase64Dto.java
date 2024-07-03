package com.clean_architecture.demo.print.infrastructure.in.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Транспорт возвращающий договор для печати
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentBase64Dto {

    /**
     * base64 представление договора
     */
    private String base64;

    /**
     * Название файла
     */
    private String fileName;
}
