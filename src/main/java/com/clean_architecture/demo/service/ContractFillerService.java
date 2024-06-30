package com.clean_architecture.demo.service;

import com.clean_architecture.demo.entity.BaseContract;
import org.springframework.stereotype.Service;

/**
 * Сервис для заполнения вложенных сущностей для
 * сущности Contract
 */
@Service
public class ContractFillerService {

    /**
     * Заполняет договор данными, необходимыми для печати
     */
    public void fillContractToTemplate(BaseContract contract, String lang) {
        // Ходит в разные DAO и заполняет вложенные сущности
    }
}
