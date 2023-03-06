package org.task;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.hasItem;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RoutesTest {
    @Test
    public void getFruits() {
        given()
                .when()
                .get("/fruits")
                .then()
                .statusCode(200)
                ;
    }
    @Test
    public void getLastFruit(){
        given()
            .when()
            .get("/getLastFruits")
            .then()
            .statusCode(200)
            .body("name",is("banana"))
            ;
    }
    @Test
    public void addFruit(){
        
        given()
            .when()
            .body("{\"id\": 3, \"name\": \"banana\"}")
            .post("/addFruits")
            .then()
            .statusCode(200)
            .body("name",is("banana"))
            ;
    }
}
