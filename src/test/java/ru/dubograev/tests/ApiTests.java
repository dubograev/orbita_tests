package ru.dubograev.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class ApiTests {
    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://orbita.center/api";
    }

    @Test
    @DisplayName("Amount of Vacancies")
    public void amountOfVacancies() {
        when()
                .get("data")
                .then()
                .statusCode(200)
                .log().body()
                .body("data",hasSize(14));
    }

    @Test
    @DisplayName("Vacancies Categories")
    public void vacanciesCategories() {
        when()
                .get("data")
                .then()
                .statusCode(200)
                .log().body()
                .body("data.category", hasItems("РАЗРАБОТКА", "ТЕСТИРОВАНИЕ", "АНАЛИТИКА", "ДИЗАЙН", "БЭК-ОФИС"));
    }

    @Test
    @DisplayName("Development Category Size")
    public void developmentSize() {
        when()
                .get("data")
                .then()
                .statusCode(200)
                .log().body()
                .body("data.findAll { it.category == 'РАЗРАБОТКА' }", hasSize(6));
    }
}
