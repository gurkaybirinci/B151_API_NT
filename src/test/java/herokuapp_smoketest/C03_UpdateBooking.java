package herokuapp_smoketest;
import static herokuapp_smoketest.C01_CreateBooking.id;
import static io.restassured.RestAssured.given;
import static utils.ObjectMapperUtils.convertJsonToJava;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

public class C03_UpdateBooking extends HerokuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/:id
        And
            {
                "firstname" : "James",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Breakfast"
            }
        When
            Kullanıcı PUT request gönderir
        Then
            Status Code = 200
        And
            Body:
                {
                    "firstname" : "James",
                    "lastname" : "Brown",
                    "totalprice" : 111,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2018-01-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : "Breakfast"
                }
     */

    @Test
    public void updateBooking() {
        //Set the URL
        spec.pathParams("first", "booking", "second", id);

        // Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("James", "Brown", 111, true, bookingDates, "Breakfast");

        // Send the request and get the response
        Response response = given(spec).body(expectedData).when().put("{first}/{second}");

        // Do assertion
        BookingPojo actualData = convertJsonToJava(response.asString(), BookingPojo.class);
    }
}








