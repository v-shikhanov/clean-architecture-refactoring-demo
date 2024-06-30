package com.clean_architecture.demo.converter;

import com.clean_architecture.demo.dto.TemplateDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TemplateDtoToMapConverter {

    private final ObjectMapper mapper;

    @Nullable
    public Map<String, Object> toMap(@Nonnull TemplateDto templateDto) {
        return fromJSONToMapObj(toJSON(Objects.requireNonNull(templateDto)));
    }

    @Nullable
    private String toJSON(@Nonnull Object object) {
        try {
            return mapper.writeValueAsString(Objects.requireNonNull(object));
        } catch (IOException e) {
            return null;
        }
    }

    @Nullable
    private Map<String, Object> fromJSONToMapObj(@Nullable String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        final TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
        };
        try {
            return mapper.readValue(json, typeRef);
        } catch (IOException e) {
            return null;
        }
    }
}
