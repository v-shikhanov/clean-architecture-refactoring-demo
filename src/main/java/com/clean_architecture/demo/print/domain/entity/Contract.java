package com.clean_architecture.demo.print.domain.entity;

import com.clean_architecture.demo.print.domain.constant.ContractType;
import com.clean_architecture.demo.print.domain.entity.nested.Counterparty;
import com.clean_architecture.demo.print.domain.entity.nested.LegalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Доменное представление договора для печати
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    private Long id;

    private String number;

    private LocalDate startDate;

    private ContractType contractType;

    /**
     * Юр лицо по договору со стороны нашей компании
     */
    private LegalEntity companyLegalEntity;

    /**
     * Контрагент юридического лица по договору со стороны нашей компании
     */
    private Counterparty companyLegalEntityCounterparty;

    /**
     * Юр лицо по договору со стороны клиента
     */
    private LegalEntity clientLegalEntity;

    /**
     * Контрагент с которым заключен договор
     */
    private Counterparty clientCounterparty;

    // Прочие поля и вложенные сущности
}
