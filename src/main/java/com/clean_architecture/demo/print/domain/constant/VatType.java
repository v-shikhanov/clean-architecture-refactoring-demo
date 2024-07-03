package com.clean_architecture.demo.print.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Типы обложения НДС
 */
@Getter
@RequiredArgsConstructor
public enum VatType {
    WITHOUT_VAT(0),
    VAT_20(20);

    private final Integer code;
}
