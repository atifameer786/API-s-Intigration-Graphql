package org.task;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class GraphqlTests {

    @Test
    public void getAllOrders() {
        String requestBody = "{\"query\":" + "\"" + "{" + " getAllOrders  {" + "id" + " name" + "}" + "}" + "\"" + "}";
        given()
                .body(requestBody)
                .post("/graphql/")
                .then()
                .contentType(ContentType.JSON)
                // .body("data.size()", is(7))
                .statusCode(200);
    }

    @Test
    public void getById() {
        String requestBody = "{\"query\":" + "\"" + "{" + " getById(Id:1)  {" + "id" + " name" + "}" + "}" + "\"" + "}";
        given()
                .body(requestBody)
                .post("/graphql/")
                .then()
                .contentType(ContentType.JSON)
                // .body("data.size()", is(1))
                // .body("data.id", is(1))
                // .body("data.name", is("virag"))
                .statusCode(200);
    }

    @Test
    public void getByName() {
        // String requestBody = "{\"query\":" + "\"" + "{" + " getByName(Id:1)  {" + "id" + " name" + "}" + "}" + "\""
        //         + "}";
        String request="{\"query\":\"{getByName(Id:1)  {id name } } \"}";
        given()
                .body(request)
                .post("/graphql/")
                .then()
                .contentType(ContentType.JSON)
                // .body("data.size()", is(1))
                // .body("data.id", is(1))
                // .body("data.name", is("virag"))
                .statusCode(200);
    }

    @Test
    public void addOrder() {

        String requestBody = "mutation m1 addOrder (info:{id:10,name:\"arjun\"} ){id name}}";
        given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .post("/graphql/")
                .then()
                // .body("data.size()", is(1))
                // .body("data.id", is(1))
                // .body("data.name", is("virag"))
                .statusCode(200);
    }

    @Test
    public void deleteById() {

        String requestBody = "{\"query\":" + "\"" + "mutation deleteById { " + "deleteById" +
                "(id: 3)" + "{" + "name " + "id" + "}" + "}" + "\"" + "}";

        given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .post("/graphql/")
                .then()
                .contentType(ContentType.JSON)
                .body("data.deleteById.name", is("arjun"))
                .body("data.deleteById.id", is(3))
                .statusCode(200);

    }
    @Test
    public void updateName(){
    //     String requestBody = "{\"query\":" + "\"" + "mutation updateName { "  +
    //     "(id: 3)" +"(info: " +"{" +"name: \"anupam\" " +
    //     "}" +")" + "{" + "name " + "id" + "}" + "}" + "\"" + "}";
        String request="mutation m2{updateName(id:2,info:{name:\"virag\" id:2}){id name}}";

        given()
        .body(request)
        .contentType(ContentType.JSON)
        .put("/graphql/")
        .then()
        .statusCode(200)
        .body("name",is("virag"))
        .body("id",is(2))
        ;


    }
    
}
