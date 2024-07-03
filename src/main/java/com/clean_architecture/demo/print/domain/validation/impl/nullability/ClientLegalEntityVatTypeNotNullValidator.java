package com.clean_architecture.demo.print.domain.validation.impl.nullability;

import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.entity.nested.LegalEntity;
import com.clean_architecture.demo.print.domain.usecase.tag.impl.client.IsClientPaysVat;
import com.clean_architecture.demo.print.domain.util.ExtractUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.clean_architecture.demo.print.domain.constant.NullValidationErrors.CLIENT_LEGAL_ENTITY_VAT_TYPE_IS_NULL;

@Component
public class ClientLegalEntityVatTypeNotNullValidator extends FieldNotNullValidator<Integer> {

    public ClientLegalEntityVatTypeNotNullValidator() {
        super(List.of(IsClientPaysVat.class), CLIENT_LEGAL_ENTITY_VAT_TYPE_IS_NULL);
    }

    @Override
    @Nullable
    protected Integer getFieldValue(@Nonnull Contract contract) {
        return ExtractUtil.safeExtract(contract.getClientLegalEntity(), LegalEntity::getVatTypeCode);
    }
}
