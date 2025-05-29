package test.get;

import arguments.holders.AuthValidationArgumentsHolder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthValidationArgumentsProvider;
import test.BaseTest;
import static constants.BoardsEndpoints.GET_BOARD_URL;
import static constants.UrlParamValues.EXISTING_BOARD_ID;
import static constants.UrlParamValues.INVALID_BOARD_ID;

public class GetBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkGetBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments)
    {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id",EXISTING_BOARD_ID)
                .get(GET_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @Test
    public void checkGetBoardWithInvalidId()
    {
        Response response = requestWithAuth()
                .pathParam("id","invalid")
                .get(GET_BOARD_URL);
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void checkGetBoardWithInvalidResource()
    {
        Response response = requestWithAuth()
                .pathParam("id", INVALID_BOARD_ID)
                .get(GET_BOARD_URL);
        response
                .then()
                .statusCode(404);
        Assertions.assertEquals("The requested resource was not found.", response.body().asString());
    }
}
