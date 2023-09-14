package com.parameter.app.controller;

import com.parameter.app.dto.ParameterDTO;
import com.parameter.app.request.ParameterList;
import com.parameter.app.request.ParameterQueryList;
import com.parameter.app.service.ParameterService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parameter")
@RequiredArgsConstructor
@Resource
public class ParameterController {
    private final ParameterService parameterService;

    @PostMapping("/")
    private ResponseEntity<ParameterDTO> create(@RequestBody ParameterDTO request) {
        return ResponseEntity.ok(parameterService.create(request));
    }

    @PostMapping("/batch")
    private ResponseEntity<ParameterList> createAll(@RequestBody ParameterList request) {
        return ResponseEntity.ok(ParameterList.fromList(parameterService.createAll(request.getParameters())));
    }

    @GetMapping("/")
    private ResponseEntity<List<ParameterDTO>> findAll() {
        return ResponseEntity.ok(parameterService.findAll());
    }

    @GetMapping("/{type}")
    private ResponseEntity<ParameterList> findByKey(@PathVariable String type) {
        return ResponseEntity.ok(ParameterList.fromList(parameterService.findByType(type)));
    }

    @GetMapping("/{type}/{key}")
    private ResponseEntity<ParameterDTO> findByKey(@PathVariable String key, @PathVariable String type) {
        return ResponseEntity.ok(parameterService.findByKeyAndType(key, type));
    }

    @PutMapping("/")
    private ResponseEntity<ParameterDTO> update(@RequestBody ParameterDTO request) {
        return ResponseEntity.ok(parameterService.update(request));
    }

    @DeleteMapping("/")
    private ResponseEntity<ParameterQueryList> deleteAllByQuery(@RequestBody ParameterQueryList request) {
        return ResponseEntity.ok(
                ParameterQueryList
                    .fromList(
                            parameterService.deleteAll(request.getParameters())
                    )
        );
    }

    @DeleteMapping("/{type}/{key}")
    private ResponseEntity<Void> deleteByKey(@PathVariable String key, @PathVariable String type) {
        try {
            parameterService.delete(key, type);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{type}")
    private ResponseEntity<Void> deleteByKey(@PathVariable String type) {
        try {
            parameterService.deleteAllByType(type);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }
}
