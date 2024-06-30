package com.clean_architecture.demo.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Возможные типы договоров
 */
@Getter
@RequiredArgsConstructor
public enum ContractType {

    INTERNET_STORE_CONTRACT("internet_store_contract")

    // Прочие типы договоров
    ;

    private final String name;
}
