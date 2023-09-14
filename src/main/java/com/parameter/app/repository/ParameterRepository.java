package com.parameter.app.repository;

import com.parameter.app.entity.Parameter;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ParameterRepository extends ListCrudRepository<Parameter, Long> {
    Optional<Parameter> findParameterByKeyAndType(String key, String type);
    Optional<Parameter> findParameterByType(String type);
    List<Parameter> findParametersByType(String type);
}
