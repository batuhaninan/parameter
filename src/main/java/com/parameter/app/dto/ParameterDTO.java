package com.parameter.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.parameter.app.entity.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParameterDTO {
    private String key;
    private String value;
    private String type;
    private String description;

    public Parameter toModel() {
        return Parameter.builder()
                .key(key)
                .value(value)
                .type(type)
                .description(description)
                .build();
    }
}
