package com.clean_architecture.demo.print.domain.usecase.template.impl;

import com.clean_architecture.demo.print.domain.constant.ContractType;
import com.clean_architecture.demo.print.domain.constant.Country;
import com.clean_architecture.demo.print.domain.constant.TemplateType;
import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.entity.nested.Counterparty;
import com.clean_architecture.demo.print.domain.entity.nested.LegalEntity;
import com.clean_architecture.demo.print.domain.usecase.tag.TagUseCase;
import com.clean_architecture.demo.print.domain.usecase.tag.impl.client.ClientName;
import com.clean_architecture.demo.print.domain.usecase.tag.impl.client.IsSelfEmployed;
import com.clean_architecture.demo.print.domain.usecase.tag.impl.company.CompanyLegalEntityFullName;
import com.clean_architecture.demo.print.domain.usecase.tag.impl.header.ContractNumber;
import com.clean_architecture.demo.print.domain.usecase.template.TemplateUseCase;
import com.clean_architecture.demo.print.domain.util.ExtractUtil;
import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Шаблон печати договора интернет-магазина с самозанятым РФ.
 */
@Component
@Primary
@Order(2)
public class RusSelfEmployedInternetStoreTemplateUseCase implements TemplateUseCase {

    private static final Set<Class<? extends TagUseCase>> TAGS = Set.of(
            ContractNumber.class, ClientName.class, IsSelfEmployed.class,
            CompanyLegalEntityFullName.class);


    @Nonnull
    @Override
    public Set<Class<? extends TagUseCase>> getTemplateTags() {
        return TAGS;
    }

    @Nonnull
    @Override
    public TemplateType getType() {
        return TemplateType.RUS_SELF_EMPLOYED_INTERNET_STORE;
    }

    @Override
    public boolean isMatched(@Nonnull Contract contract) {
        return isInternetStore(contract) && isRussianLegalEntity(contract) && isRussianClient(contract) &&
                isNotSelfEmployed(contract);
    }

    private boolean isInternetStore(@Nonnull Contract contract) {
        return ContractType.INTERNET_STORE.equals(contract.getContractType());
    }

    private static boolean isRussianLegalEntity(@Nonnull Contract contract) {
        var legalEntityCountry =
                ExtractUtil.safeExtract(contract.getCompanyLegalEntity(), LegalEntity::getCountryIsoCode);
        return Country.RUSSIA.getIso().equals(legalEntityCountry);
    }

    private boolean isRussianClient(@Nonnull Contract contract) {
        var clientCountry = ExtractUtil.safeExtract(contract.getClientCounterparty(), Counterparty::getCountryIsoCode);
        return Country.RUSSIA.getIso().equals(clientCountry);
    }

    private boolean isNotSelfEmployed(@Nonnull Contract contract) {
        var isSelfEmployed = ExtractUtil.safeExtract(contract.getClientCounterparty(), Counterparty::getSelfEmployed);
        return BooleanUtils.isNotTrue(isSelfEmployed);
    }
}
