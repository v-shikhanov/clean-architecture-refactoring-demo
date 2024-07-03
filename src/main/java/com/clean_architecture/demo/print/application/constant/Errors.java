package com.clean_architecture.demo.print.application.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Errors {

    PRINT_TEMPLATE_NOT_FOUND("print_template_not_found");

    private final String code;
}
