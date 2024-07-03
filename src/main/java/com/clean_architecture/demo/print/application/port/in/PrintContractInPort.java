package com.clean_architecture.demo.print.application.port.in;

import com.clean_architecture.demo.print.application.value.PrintResult;
import jakarta.annotation.Nonnull;

/**
 * Порт, возвращающий результат печати по переданному идентификатору договора
 */
public interface PrintContractInPort {

    /**
     * Возвращает договор по идентификатору, бросает исключение,
     * если договор не найден
     */
    @Nonnull
    PrintResult printById(@Nonnull Long id);
}
