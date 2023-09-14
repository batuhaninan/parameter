package com.parameter.app.service;

import com.parameter.app.dto.ParameterDTO;
import com.parameter.app.entity.Parameter;
import com.parameter.app.repository.ParameterRepository;
import com.parameter.app.request.ParameterQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParameterService {
    private final ParameterRepository parameterRepository;

    public ParameterDTO create(ParameterDTO parameterDTO) {
        return parameterRepository.save(parameterDTO.toModel()).toDTO();
    }

    public List<ParameterDTO> createAll(List<ParameterDTO> parameterList) {
        var parametersToSave = parameterList.stream()
                .map(ParameterDTO::toModel)
                .collect(Collectors.toList());

        return parameterRepository.saveAll(parametersToSave)
                .stream()
                .map(Parameter::toDTO)
                .collect(Collectors.toList());
    }

    public ParameterDTO findByKeyAndType(String key, String type) {
        return parameterRepository.findParameterByKeyAndType(key, type)
                .map(Parameter::toDTO)
                .orElse(ParameterDTO.builder().build());
    }

    public List<ParameterDTO> findByType(String type) {
        return parameterRepository.findParametersByType(type)
                .stream()
                .map(Parameter::toDTO)
                .collect(Collectors.toList());
    }

    public List<ParameterDTO> findAll() {
        return parameterRepository.findAll()
                .stream()
                .map(Parameter::toDTO)
                .collect(Collectors.toList());
    }

    public void delete(String key, String type) {
        var parameter = parameterRepository.findParameterByKeyAndType(key, type).orElseThrow();
        parameterRepository.delete(parameter);
    }

    public List<ParameterQuery> deleteAll(List<ParameterQuery> queries) {
        var faultList = new ArrayList<ParameterQuery>();

        for (ParameterQuery query : queries) {
            try {
                var parameter = parameterRepository.findParameterByKeyAndType(query.getKey(), query.getType());
                parameterRepository.delete(parameter.orElseThrow());
            } catch (Exception e) {
                faultList.add(query);
            }
        }

        return faultList;
    }

    public void deleteAllByType(String type) {
        var parameters = parameterRepository.findParametersByType(type);
        if (parameters.isEmpty()) throw new RuntimeException();
        parameterRepository.deleteAll(parameters);
    }

    public ParameterDTO update(ParameterDTO parameterDTO) {
        var parameter = parameterRepository.findParameterByKeyAndType(parameterDTO.getKey(), parameterDTO.getType()).orElseThrow();
        return parameterRepository.save(parameter.copy(parameterDTO)).toDTO();
    }
}
