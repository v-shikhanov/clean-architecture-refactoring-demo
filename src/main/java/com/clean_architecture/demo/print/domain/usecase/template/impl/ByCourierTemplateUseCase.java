package com.clean_architecture.demo.print.domain.usecase.template.impl;

import com.clean_architecture.demo.print.domain.constant.ContractType;
import com.clean_architecture.demo.print.domain.constant.Country;
import com.clean_architecture.demo.print.domain.constant.TemplateType;
import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.entity.nested.Counterparty;
import com.clean_architecture.demo.print.domain.entity.nested.LegalEntity;
import com.clean_architecture.demo.print.domain.usecase.tag.TagUseCase;
import com.clean_architecture.demo.print.domain.usecase.tag.impl.client.ClientName;
import com.clean_architecture.demo.print.domain.usecase.tag.impl.company.CompanyLegalEntityFullName;
import com.clean_architecture.demo.print.domain.usecase.tag.impl.header.ContractNumber;
import com.clean_architecture.demo.print.domain.usecase.template.TemplateUseCase;
import com.clean_architecture.demo.print.domain.util.ExtractUtil;
import jakarta.annotation.Nonnull;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Шаблон печати договора курьерских услуг Беларуси.
 */
@Component
@Primary
@Order(3)
public class ByCourierTemplateUseCase implements TemplateUseCase {

    private static final Set<Class<? extends TagUseCase>> TAGS = Set.of(
            ContractNumber.class, ClientName.class, CompanyLegalEntityFullName.class);


    @Nonnull
    @Override
    public Set<Class<? extends TagUseCase>> getTemplateTags() {
        return TAGS;
    }

    @Nonnull
    @Override
    public TemplateType getType() {
        return TemplateType.BY_COURIER;
    }

    @Override
    public boolean isMatched(@Nonnull Contract contract) {
        return isInternetStore(contract) && isRussianLegalEntity(contract) && isRussianClient(contract);
    }

    private boolean isInternetStore(@Nonnull Contract contract) {
        return ContractType.COURIER.equals(contract.getContractType());
    }

    private static boolean isRussianLegalEntity(@Nonnull Contract contract) {
        var legalEntityCountry =
                ExtractUtil.safeExtract(contract.getCompanyLegalEntity(), LegalEntity::getCountryIsoCode);
        return Country.BELARUS.getIso().equals(legalEntityCountry);
    }

    private boolean isRussianClient(@Nonnull Contract contract) {
        var clientCountry = ExtractUtil.safeExtract(contract.getClientCounterparty(), Counterparty::getCountryIsoCode);
        return Country.BELARUS.getIso().equals(clientCountry);
    }
}
