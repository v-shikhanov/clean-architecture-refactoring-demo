package com.clean_architecture.demo.service;

import com.clean_architecture.demo.entity.BaseContract;
import com.clean_architecture.demo.entity.Counterparty;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с контрагентами
 */
@Service
public class CounterpartyService {
    public Counterparty getByContract(BaseContract contract, String lang) {
        // Какая-то логика по получению контрагента
        return new Counterparty();
    }
}
