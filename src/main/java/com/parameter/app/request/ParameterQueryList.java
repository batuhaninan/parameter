package com.parameter.app.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParameterQueryList {
    private List<ParameterQuery> parameters;

    public static ParameterQueryList fromList(List<ParameterQuery> parameters) {
        return ParameterQueryList.builder()
                .parameters(parameters)
                .build();
    }
}
