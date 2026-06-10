package com.matera.bootcamp26.controller;

import com.matera.bootcamp26.Bootcamp26Application;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.annotation.PostConstruct;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        classes = Bootcamp26Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
class ContaControllerTest {

    @LocalServerPort
    public int serverPort;

    @PostConstruct
    public void initRestAssured() {
        RestAssured.port = serverPort;
        RestAssured.urlEncodingEnabled = false;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deveRetornaErroAoPassarIDInvalidoNaAPIDeBuscaPorId() {
        RestAssured
                .given()
                    .accept(ContentType.JSON)
                .when()
                    .get("/api/v1/contas/565")
                .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body("status", Matchers.equalTo(400))
                    .body("title", Matchers.equalTo("Conta inválida"))
                    .body("detail", Matchers.equalTo("Conta não encontrada"))
                    .body("instance", Matchers.equalTo("/api/v1/contas/565"));

    }

}