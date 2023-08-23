package practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get02_TumContactListesiniGetirme {
    @Test
    public void get02() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGI5MTc4ZGM0ZjcyYjAwMTM3ZTAzN2UiLCJpYXQiOjE2OTE4NTMzMTN9.WF6Vldr5OoNL92R2344yxMrdqdJU9Usx8e3QO6N_I-A";
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath = "/contacts";

        Response response = given()
                .auth()
                .oauth2(token)
                .when()
                .get();
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("firstName", hasSize(7))
                .body("_id", hasItem("64d7abf936c2810013fe7bdc"));

    }
}
