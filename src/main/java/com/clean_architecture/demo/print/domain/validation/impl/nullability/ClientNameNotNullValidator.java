package com.clean_architecture.demo.print.domain.validation.impl.nullability;

import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.entity.nested.Counterparty;
import com.clean_architecture.demo.print.domain.usecase.tag.impl.client.ClientName;
import com.clean_architecture.demo.print.domain.util.ExtractUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.clean_architecture.demo.print.domain.constant.NullValidationErrors.CLIENT_NAME_IS_NULL;

@Component
public class ClientNameNotNullValidator extends FieldNotNullValidator<String> {

    public ClientNameNotNullValidator() {
        super(List.of(ClientName.class), CLIENT_NAME_IS_NULL);
    }

    @Override
    @Nullable
    protected String getFieldValue(@Nonnull Contract contract) {
        return ExtractUtil.safeExtract(contract.getClientCounterparty(), Counterparty::getName);
    }
}
