package com.clean_architecture.demo.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Регистрирует шаблоны печати во внешнем сервисе
 */
@Service
public class PrintTemplatesRegisterService {

    private final AtomicBoolean tempalatesRegistred = new AtomicBoolean(false);

    @PostConstruct
    void init() {
        // Какая-то логика по регистрации шаблонов печати
        tempalatesRegistred.set(true);
    }

    /**
     * Регистрирует шаблоны печати во внешнем сервисе
     */
    public boolean isDocReportReady() {
        return tempalatesRegistred.get();
    }
}
