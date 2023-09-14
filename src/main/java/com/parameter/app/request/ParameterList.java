package com.parameter.app.request;

import com.parameter.app.dto.ParameterDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParameterList {
    private List<ParameterDTO> parameters;

    public static ParameterList fromList(List<ParameterDTO> parameters) {
        return ParameterList.builder()
                .parameters(parameters)
                .build();
    }
}
