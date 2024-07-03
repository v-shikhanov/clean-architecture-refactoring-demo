package com.clean_architecture.demo.print.domain.usecase.tag.impl.client;

import com.clean_architecture.demo.print.domain.constant.VatType;
import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.entity.nested.LegalEntity;
import com.clean_architecture.demo.print.domain.usecase.tag.TagUseCase;
import com.clean_architecture.demo.print.domain.util.ExtractUtil;
import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Является ли клиент плательщиком НДС
 */
@Component
public class IsClientPaysVat implements TagUseCase {

    @Nonnull
    @Override
    public String getName() {
        return "isClientPaysVat";
    }

    @Nonnull
    @Override
    public Boolean getValue(@Nonnull Contract contract) {
        var clientVatType = ExtractUtil.safeExtract(contract.getClientLegalEntity(), LegalEntity::getVatTypeCode);
        Objects.requireNonNull(clientVatType, "Vat code is null");
        return ObjectUtils.notEqual(VatType.WITHOUT_VAT.getCode(), clientVatType);
    }
}
