package com.clean_architecture.demo.print.domain.validation.impl.nullability;

import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.usecase.tag.impl.header.ContractNumber;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.clean_architecture.demo.print.domain.constant.NullValidationErrors.NUMBER_IS_NULL;

/**
 * Номер договора должен быть заполнен
 */
@Component
public class NumberNotNullValidator extends FieldNotNullValidator<String> {

    public NumberNotNullValidator() {
        super(List.of(ContractNumber.class), NUMBER_IS_NULL);
    }

    @Override
    @Nullable
    protected String getFieldValue(@Nonnull Contract contract) {
        return contract.getNumber();
    }
}
