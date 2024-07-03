package com.clean_architecture.demo.print.domain.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NullValidationErrors {

    public static final String NUMBER_IS_NULL = "print_number_is_null";
    public static final String CLIENT_NAME_IS_NULL = "print_client_name_is_null";
    public static final String COMPANY_LEGAL_ENTITY_NAME_IS_NULL = "print_legal_entity_name_is_null";
    public static final String CLIENT_LEGAL_ENTITY_VAT_TYPE_IS_NULL = "print_client_legal_entity_vat_type_is_null";
}
