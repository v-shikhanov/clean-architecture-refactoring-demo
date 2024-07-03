package com.clean_architecture.demo.print.domain.entity.nested;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Представление контрагента для печати
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Counterparty {

    private UUID uuid;

    private String name;

    private String countryIsoCode;

    private Boolean selfEmployed;

    // Прочие поля и сущности
}
