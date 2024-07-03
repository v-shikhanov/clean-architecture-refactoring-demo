package com.clean_architecture.demo.print.domain.validation.impl.nullability;

import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.entity.nested.LegalEntity;
import com.clean_architecture.demo.print.domain.usecase.tag.impl.company.CompanyLegalEntityFullName;
import com.clean_architecture.demo.print.domain.util.ExtractUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.clean_architecture.demo.print.domain.constant.NullValidationErrors.COMPANY_LEGAL_ENTITY_NAME_IS_NULL;

@Component
public class LegalEntityNameNotNullValidator extends FieldNotNullValidator<String> {

    public LegalEntityNameNotNullValidator() {
        super(List.of(CompanyLegalEntityFullName.class), COMPANY_LEGAL_ENTITY_NAME_IS_NULL);
    }

    @Override
    @Nullable
    protected String getFieldValue(@Nonnull Contract contract) {
        return ExtractUtil.safeExtract(contract.getCompanyLegalEntity(), LegalEntity::getFullName);
    }
}
