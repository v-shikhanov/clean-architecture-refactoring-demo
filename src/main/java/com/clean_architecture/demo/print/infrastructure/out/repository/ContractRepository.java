package com.clean_architecture.demo.print.infrastructure.out.repository;

import com.clean_architecture.demo.print.domain.entity.Contract;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Repository;

/**
 * Компонент, возвращающий объекты договора из базы данных
 */
@Repository
public class ContractRepository {

    @Nullable
    public Contract get(@Nonnull Long id) {

        // Какая-то логика, извлекающая договор и дозаполняющая его вложенные сущности из БД
        return new Contract();
    }
}
