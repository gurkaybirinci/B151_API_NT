package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerokuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/23
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
     {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
}
     */

    @Test
    public void get06(){
        spec.pathParams("first", "booking", "second", 24);
        Response response = given().when().spec(spec).get("{first}/{second}");
        response.prettyPrint();

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Josh"),
                        "lastname", equalTo("Allen"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("super bowls"));

//        // 2.YOL
        JsonPath json = response.jsonPath();
        assertEquals("Josh", json.getString("firstname"));
        assertEquals("Allen", json.getString("lastname"));
        assertEquals(111, json.getInt("totalprice"));
        assertTrue(json.getBoolean("depositpaid"));
        assertEquals("2018-01-01", json.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", json.getString("bookingdates.checkout"));
        assertEquals("super bowls", json.getString("additionalneeds"));

        // 3.YOL (Soft Assertion)
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(json.getString("firstname"), "Josh");
        softAssert.assertEquals(json.getString("lastname"), "Allen");
        softAssert.assertEquals(json.getInt("totalprice"), 111);
        softAssert.assertTrue(json.getBoolean("depositpaid"));
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2018-01-01");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2019-01-01");
        softAssert.assertEquals(json.getString("additionalneeds"), "super bowls");

        softAssert.assertAll();

    }
}

