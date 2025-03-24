package test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

import static constants.UrlParamValues.VALID_KEY;
import static constants.UrlParamValues.VALID_TOKEN;

public class BaseTest {

    @BeforeAll
    public static void setBaseUrl(){
        RestAssured.baseURI="https://api.trello.com/1";
    }

    protected static RequestSpecification requestWithAuth(){
        return RestAssured.given()
                .queryParams(Map.of("key",VALID_KEY,
                        "token",VALID_TOKEN))
                .header("Accept","application/json");
    }

    protected static RequestSpecification requestWithoutAuth(){
        return RestAssured.given();
    }
}
