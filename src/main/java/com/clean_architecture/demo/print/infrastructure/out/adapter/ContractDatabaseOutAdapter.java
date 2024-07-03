package com.clean_architecture.demo.print.infrastructure.out.adapter;

import com.clean_architecture.demo.print.application.port.out.ContractOutPort;
import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.infrastructure.out.repository.ContractRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Получение договора из базы данных
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ContractDatabaseOutAdapter implements ContractOutPort {

    private final ContractRepository contractRepository;

    @Nonnull
    @Override
    public Contract get(@Nonnull Long id) {
        var contract = contractRepository.get(id);
        Objects.requireNonNull(contract, "Contract not found");
        return contract;
    }
}
