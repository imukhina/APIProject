package test.get;

import arguments.holders.AuthValidationArgumentsHolder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthCardValidationArgumentsProvider;
import providers.AuthValidationArgumentsProvider;
import test.BaseTest;

import static constants.CardsEndpoints.GET_CARD_URL;
import static constants.UrlParamValues.EXISTING_CARD_ID;
import static org.hamcrest.Matchers.lessThan;

public class GetCardsValidationTests extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthCardValidationArgumentsProvider.class)
    public void checkGetCardsWithInvalidAuth(AuthValidationArgumentsHolder validationArguments)
    {
        Response response = BaseTest.requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id",EXISTING_CARD_ID)
                .get(GET_CARD_URL);
        response
                .then()
                .statusCode(401)
                .log().body();
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @Test
    public void checkGetCardsWithInvalidId()
    {
        Response response = BaseTest.requestWithAuth()
                .pathParam("id","invalid")
                .get(GET_CARD_URL);
        response
                .then()
                .statusCode(400)
                .log().body();
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void checkGetBoardsWithInvalidResourse()
    {
        Response response = BaseTest.requestWithAuth()
                .pathParam("id","77b33f10bce545854a096044")
                .get(GET_CARD_URL);
        response
                .then()
                .statusCode(404)
                .time(lessThan(1000L))
                .log().body();
        Assertions.assertEquals("The requested resource was not found.", response.body().asString());
    }
}
