package practice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get01_IDileContactGetirme {
    @Test
    public void get02() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGI5MTc4ZGM0ZjcyYjAwMTM3ZTAzN2UiLCJpYXQiOjE2OTE4NTMzMTN9.WF6Vldr5OoNL92R2344yxMrdqdJU9Usx8e3QO6N_I-A";
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath = "/contacts/64d7ad5436c2810013fe7be2";

        Response response = given()
                .auth()
                .oauth2(token)
                .when()
                .get();
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Doe"))
                .body("birthdate", equalTo("1970-01-01"))
                .body("email", equalTo("jdoe@fake.com"))
                .body("phone", equalTo("8005555555"))
                .body("street1", equalTo("1 Main St."))
                .body("street2", equalTo("Apartment A"))
                .body("city", equalTo("Anytown"))
                .body("stateProvince", equalTo("KS"))
                .body("postalCode", equalTo("12345"))
                .body("country", equalTo("USA"))
                .body("owner", equalTo("64b9178dc4f72b00137e037e"))
                .body("__v", equalTo(0))
                .body("firstName", equalToIgnoringCase("john"))
                .body("email", not(equalTo("jane@fake.com")))
                .body("email", containsString("@fake.com"))
                .body("city", startsWith("Any"))
                .body("country", endsWith("USA"))
                .body("stateProvince", anyOf(equalTo("KS"), equalTo("CA")))
                .body("firstName", allOf(equalTo("John"), equalToIgnoringCase("JOHN")))
                .body("__v", greaterThan(-1));
    }
}
