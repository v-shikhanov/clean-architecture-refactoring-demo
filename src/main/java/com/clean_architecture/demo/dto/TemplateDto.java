package com.clean_architecture.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Транспортный объект для печати договора
 */
@Getter
@Setter
public class TemplateDto {

    private String number;
    private String startDate;
    private String legalEntityName;

    // Прочие поля, нужные для печати договора
}
