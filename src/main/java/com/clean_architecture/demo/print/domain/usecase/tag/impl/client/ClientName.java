package com.clean_architecture.demo.print.domain.usecase.tag.impl.client;

import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.entity.nested.Counterparty;
import com.clean_architecture.demo.print.domain.usecase.tag.TagUseCase;
import com.clean_architecture.demo.print.domain.util.StringUnderscoreUtil;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

/**
 * Название контрагента,
 * заключившего договор со стороны клиента
 */
@Component
public class ClientName implements TagUseCase {

    @Nonnull
    @Override
    public String getName() {
        return "clientName";
    }

    @Nonnull
    @Override
    public String getValue(@Nonnull Contract contract) {
        return StringUnderscoreUtil.extractOrUnderscore(contract.getClientCounterparty(), Counterparty::getName);
    }
}
