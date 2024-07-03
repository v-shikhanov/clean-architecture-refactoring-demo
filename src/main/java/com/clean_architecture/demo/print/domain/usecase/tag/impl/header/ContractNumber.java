package com.clean_architecture.demo.print.domain.usecase.tag.impl.header;

import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.usecase.tag.TagUseCase;
import com.clean_architecture.demo.print.domain.util.StringUnderscoreUtil;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

/**
 * Номер договора
 */
@Component
public class ContractNumber implements TagUseCase {

    @Nonnull
    @Override
    public String getName() {
        return "number";
    }

    @Nonnull
    @Override
    public String getValue(@Nonnull Contract contract) {
        return StringUnderscoreUtil.valueOrUnderscore(contract.getNumber());
    }
}
