package com.clean_architecture.demo.print.domain.usecase.tag.impl.company;

import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.entity.nested.LegalEntity;
import com.clean_architecture.demo.print.domain.usecase.tag.TagUseCase;
import com.clean_architecture.demo.print.domain.util.StringUnderscoreUtil;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class CompanyLegalEntityFullName implements TagUseCase {

    @Nonnull
    @Override
    public String getName() {
        return "companyLegalEntityFullName";
    }

    @Nonnull
    @Override
    public String getValue(@Nonnull Contract contract) {
        return StringUnderscoreUtil.extractOrUnderscore(contract.getCompanyLegalEntity(), LegalEntity::getFullName);
    }
}
