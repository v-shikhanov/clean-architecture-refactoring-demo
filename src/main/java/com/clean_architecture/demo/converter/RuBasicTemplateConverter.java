package com.clean_architecture.demo.converter;

import com.clean_architecture.demo.dto.TemplateDto;
import com.clean_architecture.demo.entity.BaseContract;
import com.clean_architecture.demo.entity.Counterparty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RuBasicTemplateConverter extends TemplateConverter {

    // Вспомогательные компоненты

    @Override
    public void setSpecificFields(
            BaseContract contract,
            Counterparty counterparty,
            String lang,
            TemplateDto templateDto,
            String printTemplateCode) {
        // Вызовы вспомогательных компонентов, доставание значений из вложенных сущностей
    }

    @Override
    public void setSpecificFieldsForInternetStore(
            BaseContract contract,
            Counterparty counterparty,
            String lang,
            TemplateDto templateDto,
            String printTemplateCode) {
        // Вызовы вспомогательных компонентов
    }
}
