package org.task;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.event.TransactionalEventListener;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.is;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

@QuarkusTest
public class QuarkusApiTests {
    @Test
    public void getAllOrders(){
        given()
        .when()
        .get("/get/getAllOrders")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            ;
    }
    @Test
    @Transactional
    public void addOrder(){
        given()
        .body("{\"id\": 7, \"name\": \"chotu\"}")
        .header("Content-Type", MediaType.APPLICATION_JSON)
        .when().post("/get/addOrder")
        .then()
            .statusCode(200)
            .assertThat().body("id",is(7))
            .assertThat().body("name",is("chotu"))
            ;
    }
    @Test
    public void byId(){
        given()
        .pathParam("id", 1)
        .when()
            .get("/get/byId/{id}")
            .then()
            .statusCode(200)
            .body("name",is("virag"))
            .body("id",is(1))
        ;
    }
    @Test
    public void byNameVarified(){
        given()
        .body("{\"name\":\"virag\"}")
        .header("Content-Type", MediaType.APPLICATION_JSON)
        .when()
            .post("/get/byName")
            .then()
            .statusCode(200)
            // .assertThat().body("id",is(1))
            // .assertThat().body("name",is("virag"))
        ;
    }@Test
    public void byNameException(){
        given()
        .body("{\"name\":\"pushpendra\"}")
        .header("Content-Type", MediaType.APPLICATION_JSON)
        .when()
            .post("/get/byName")
            .then()
            .statusCode(404)
        ;
    }
    @Test
    public void deleteId(){
        given()
        .pathParam("id", 6)
        .when()
            .delete("/get/deleteId/{id}")
            .then()
            .statusCode(200)
            // .body("name",is("anurag"))
            .body("id",is(6))
        ;
    }
    @Test
    public void updateName(){

        given()
        .body("{\"name\":\"anurag+virag\"}")
        .contentType(ContentType.JSON)
        .pathParam("id", 6)
        .when()
            .put("/get/updateName/{id}")
            .then()
            .statusCode(200)
            .body("id",is(6))
            .body("name",is("anurag+virag"))
        ;
    }
}