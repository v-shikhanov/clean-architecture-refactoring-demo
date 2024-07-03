package com.clean_architecture.demo.print.application.port.out;

import com.clean_architecture.demo.print.domain.entity.Contract;
import jakarta.annotation.Nonnull;

/**
 * Возвращает договор, по его идентификатору
 */
public interface ContractOutPort {

    @Nonnull
    Contract get(@Nonnull Long id);
}
