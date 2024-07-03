package com.clean_architecture.demo.print.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Country {

    RUSSIA("RU"),
    BELARUS("BY"),
    KAZAKHSTAN("KZ"),
    // Прочие страны
    ;

    private final String iso;
}
