package com.plantier.projetocarona.interfaces.incoming

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PassengerAPITestIT {

    @LocalServerPort
    private var port: Int = 0

    @BeforeEach
    fun setup() {
        RestAssured.port = port
    }

    @Test
    fun testCreatePassenger() {
        val createPassengerJSON = "{" +
                "\"name\": \"Lina Manchinha Tester Passenger\"" +
                "}".trimIndent()
        given()
            .contentType(ContentType.JSON)
            .body(createPassengerJSON)
            .post("/passengers")
            .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo("Lina Manchinha Tester Passenger"))
    }
}