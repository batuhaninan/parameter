package com.parameter.app.controller;

import com.parameter.app.entity.Parameter;
import com.parameter.app.repository.ParameterRepository;
import com.parameter.app.testcontainers.EnableTestcontainers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableTestcontainers
class ParameterControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    ParameterRepository parameterRepository;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        parameterRepository.deleteAll();
    }

    @Test
    void shouldGetAllEmptyCustomers() {
        String response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/parameter/")
                .asString();
        System.out.println(response);
        Assertions.assertEquals(response, "[]");
    }

    @Test
    void shouldGetAllCustomers() {
        List<Parameter> parameters = List.of(
                Parameter.builder().key("A").value("1").build(),
                Parameter.builder().key("B").value("2").build()
        );

        var l = parameterRepository.findAll();
        parameterRepository.saveAll(parameters);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/parameter/")
                .then()
                .statusCode(200)
                .body(".", hasSize(2));
    }

    @Test
    void shouldGetAllCustomersString() {
        List<Parameter> parameters = List.of(
                Parameter.builder().key("A").value("1").build(),
                Parameter.builder().key("B").value("2").build()
        );

        var l = parameterRepository.findAll();
        parameterRepository.saveAll(parameters);

        String response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/parameter/")
                .asString();

        System.out.println(response);
    }



}