package com.clean_architecture.demo.print.domain.usecase.tag.impl.client;

import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.entity.nested.Counterparty;
import com.clean_architecture.demo.print.domain.usecase.tag.TagUseCase;
import com.clean_architecture.demo.print.domain.util.ExtractUtil;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

/**
 * Является ли контрагент самозанятым
 */
@Component
public class IsSelfEmployed implements TagUseCase {

    @Nonnull
    @Override
    public String getName() {
        return "isSelfEmployed";
    }

    @Nonnull
    @Override
    public Boolean getValue(@Nonnull Contract contract) {
        return ExtractUtil.getOrDefault(contract.getClientCounterparty(), Counterparty::getSelfEmployed, false);
    }
}
