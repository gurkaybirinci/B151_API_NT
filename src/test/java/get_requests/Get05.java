package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get05 extends HerokuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            Status code 200 olmalı
	  	And
            Data içerisinde firstname değeri "Sally", lastname değeri "Brown" olan biri olmalı
     */

    @Test
    public void get05() {
        spec.pathParam("first", "booking")
                .queryParams("firstname", "Sally", "lastname", "Brown");

        Response response = given()
                .spec(spec)
                .when()
                .get("/{first}");
        response.prettyPrint();
        response
                .then()
                .statusCode(200);
    }
}
