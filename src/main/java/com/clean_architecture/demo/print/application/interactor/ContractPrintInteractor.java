package com.clean_architecture.demo.print.application.interactor;


import com.clean_architecture.demo.print.application.constant.Errors;
import com.clean_architecture.demo.print.application.port.in.PrintContractInPort;
import com.clean_architecture.demo.print.application.port.out.ContractOutPort;
import com.clean_architecture.demo.print.application.port.out.PrintOutPort;
import com.clean_architecture.demo.print.application.value.PrintResult;
import com.clean_architecture.demo.print.domain.entity.Contract;
import com.clean_architecture.demo.print.domain.usecase.tag.TagUseCase;
import com.clean_architecture.demo.print.domain.usecase.template.TemplateUseCase;
import com.clean_architecture.demo.print.domain.validation.Validator;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис слоя приложения, оркестирует процесс печати договора:
 * <ul>
 *     <li>Получает договор</li>
 *     <li>Получает юзкейс шаблона печати</li>
 *     <li>Вызывает валидаторы </li>
 *     <li>Вызывает юзкейсы тэгов печати</li>
 *     <li>Работает с инфраструктурным слоем через адаптер, печатая договор</li>
 * </ul>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ContractPrintInteractor implements PrintContractInPort {

    private final List<TemplateUseCase> templateUseCases;
    private final List<Validator> validators;
    private final List<TagUseCase> tagUseCases;

    private final ContractOutPort contractPort;
    private final PrintOutPort printPort;

    @Override
    @Nonnull
    public PrintResult printById(@Nonnull Long id) {
        var contract = contractPort.get(id);
        var templateUseCase = getTemplateUseCase(contract);
        validate(contract, templateUseCase);
        var tags = getTags(templateUseCase, contract);
        var content = printPort.printTemplateWithTagsToFile(templateUseCase.getType(), tags);
        return PrintResult.getInstance(contract, content);
    }

    @Nonnull
    protected TemplateUseCase getTemplateUseCase(@Nonnull Contract contract) {
        return templateUseCases.stream()
                .filter(useCase -> useCase.isMatched(contract))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(Errors.PRINT_TEMPLATE_NOT_FOUND.getCode()));
    }

    protected void validate(@Nonnull Contract contract, @Nonnull TemplateUseCase template) {
        validators.forEach(validator -> validator.validate(contract, template));
    }

    @Nonnull
    protected Map<String, Object> getTags(@Nonnull TemplateUseCase templateUseCase, @Nonnull Contract contract) {
        var tagsToGet = templateUseCase.getTemplateTags();
        return tagUseCases.stream()
                .filter(tagUseCase -> tagsToGet.contains(tagUseCase.getClass()))
                .collect(Collectors.toMap(TagUseCase::getName, tagUseCase -> tagUseCase.getValue(contract)));
    }
}
