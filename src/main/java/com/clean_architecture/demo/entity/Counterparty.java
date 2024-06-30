package com.clean_architecture.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность контрагента
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Counterparty {

    /**
     * ID контрагента
     */
    private Long id;

    /**
     * Название контрагент
     */
    private String name;

    // Различные вложенные сущности
}
