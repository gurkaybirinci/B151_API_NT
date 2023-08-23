package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonPlaceHolderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
	 	    Kullanıcı URL'e bir GET request gönderir
	    And
	        Accept type “application/json” olmalı
	    Then
	        HTTP Status Code 200 olmalı
	    And
	        Response format "application/json" olmalı
	    And
	        Listede 200 tane eleman olmalı
	    And
	        title içeriklerinden en az birinin değeri "quis eius est sint explicabo" olmalı
	    And
	        userIds değerleri içerisinde 2, 7, ve 9 bulunmalı
     */

    @Test
    public void test01() {
        // 1) Set the URL
        spec.pathParam("first", "todos"); // spec: Tekrarlı işlemlerin konulduğu RequestSpecification objesidir.

        // 2) Set the expected data

        // 3) Send the request and get the response
        Response response = given()
                .spec(spec)
                .when()
                .get("/{first}");

        response.prettyPrint();

        // 4) Do assertion
        response
                .then()
                .contentType("application/json")
                .body("id", hasSize(200))
                .body("title", hasItem("quis eius est sint explicabo"))
                .body("userId", hasItems(2, 7, 9));

    }
}
