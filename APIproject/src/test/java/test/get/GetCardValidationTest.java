package test.get;

import arguments.holders.AuthValidationArgumentsHolder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthCardValidationArgumentsProvider;
import test.BaseTest;
import static constants.CardsEndpoints.GET_CARD_URL;
import static constants.UrlParamValues.EXISTING_CARD_ID;
import static constants.UrlParamValues.INVALID_CARD_ID;

public class GetCardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthCardValidationArgumentsProvider.class)
    public void checkGetCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments)
    {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id", EXISTING_CARD_ID)
                .get(GET_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @Test
    public void checkGetCardWithInvalidId()
    {
        Response response = requestWithAuth()
                .pathParam("id","invalid")
                .get(GET_CARD_URL);
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void checkGetCardWithInvalidResource()
    {
        Response response = BaseTest.requestWithAuth()
                .pathParam("id", INVALID_CARD_ID)
                .get(GET_CARD_URL);
        response
                .then()
                .statusCode(404);
        Assertions.assertEquals("The requested resource was not found.", response.body().asString());
    }
}
