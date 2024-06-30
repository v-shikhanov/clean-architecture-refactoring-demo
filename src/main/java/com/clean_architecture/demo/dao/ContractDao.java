package com.clean_architecture.demo.dao;

import com.clean_architecture.demo.entity.BaseContract;
import org.springframework.stereotype.Repository;

/**
 * Компонент, возвращающий из базы данных базовую
 * сущность договора, заполняет только основные поля
 */
@Repository
public class ContractDao {
    public BaseContract get(Long contractId) {
        // Какая-то логика по хождению в БД здесь
        return new BaseContract();
    }
}
