package com.clean_architecture.demo.print.domain.entity.nested;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Представление сущности юридического лица при печати
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LegalEntity {

    private UUID uuid;

    private String fullName;

    private String countryIsoCode;

    private Integer vatTypeCode;
}
