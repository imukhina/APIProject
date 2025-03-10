import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

public class BaseTest {
    @BeforeAll
    public static void setBaseUrl(){
        RestAssured.baseURI="https://api.trello.com/1";
    }
    protected static RequestSpecification requestWithAuth(){
        return RestAssured.given()
                .queryParams(Map.of("key","3a4a0cd0002ce19114a124b7861eaeae",
                        "token","ATTA98a6b05484cb89c3777111af6cf67f9a5f46e24fbccee3c82512431e5646912e75406C2B"))
                .header("Accept","application/json");
    }
}
