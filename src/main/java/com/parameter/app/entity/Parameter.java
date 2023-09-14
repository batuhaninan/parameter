package com.parameter.app.entity;

import com.parameter.app.dto.ParameterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parameter {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "__KEY")
    private String key;

    @Column(name = "__VALUE")
    private String value;

    @Column(name = "__TYPE")
    private String type;

    @Column(name = "__DESCRIPTION")
    private String description;

    public ParameterDTO toDTO() {
        return ParameterDTO.builder()
                .key(key)
                .value(value)
                .type(type)
                .description(description)
                .build();
    }

    public Parameter copy(ParameterDTO parameterDTO) {
        if (parameterDTO.getKey() != null) {
            this.key = parameterDTO.getKey();
        }

        if (parameterDTO.getValue() != null) {
            this.value = parameterDTO.getValue();
        }

        if (parameterDTO.getDescription() != null) {
            this.description = parameterDTO.getDescription();
        }

        if (parameterDTO.getType() != null) {
            this.type = parameterDTO.getType();
        }

        return this;
    }
}
