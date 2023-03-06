package org.task;

import org.junit.jupiter.api.Test;

import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CamelTests {
    @Test
    public void getAllInfo(){
        given()
        .when()
        .get("/info/all")
        .then()
        .statusCode(200)
        ;
    }
    @Test
    public void addInfo(){
        String requestBody="{\"id\": 9, \"name\": \"ritesh\"}";
        given()
        .when()
        .body(requestBody)
        .post("/info/add")
        .then()
        .statusCode(200)
        ;
    }
    @Test
    public void deleteInfo(){
        given()
        .when()
            .body("{\"id\":5}")
            .delete("/info/delete")
            .then()
            .statusCode(200)
        ;
    }

}
