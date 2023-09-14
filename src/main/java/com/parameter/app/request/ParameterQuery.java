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
public class ParameterQuery {
    private String key;
    private String type;
}
