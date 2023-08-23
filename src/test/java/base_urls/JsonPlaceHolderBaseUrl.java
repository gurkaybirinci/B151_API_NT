package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {
    // setUp metodu ile tekrarlı yapılacak işlemleri topluyoruz.
    // Test öncesi çalışacak şekilde @Before anotasyonu ekliyoruz.
    protected RequestSpecification spec;
    @Before
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setContentType(ContentType.JSON)
                .build();
    }
}
