package com.clean_architecture.demo.entity;

import com.clean_architecture.demo.constant.ContractType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Базовый класс-сущность договор
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseContract {

    /**
     * ID договора
     */
    private Long id;

    /**
     * Номер договора
     */
    private String number;

    /**
     * Тип договора
     */
    private ContractType type;

    /**
     * Контрагент по договору
     */
    private Counterparty counterparty;

    // Различные вложенные сущности
}
