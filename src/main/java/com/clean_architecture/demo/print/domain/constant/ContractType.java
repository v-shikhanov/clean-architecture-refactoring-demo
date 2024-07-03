package com.clean_architecture.demo.print.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Константы для Типов договора
 */
@Getter
@RequiredArgsConstructor
public enum ContractType {

    /**
     * Договор оказания курьерских услуг
     */
    COURIER(1),

    /**
     * Договор с интернет-магазином
     */
    INTERNET_STORE(2)

    // Прочие типы
    ;

    private final Integer id;
}
