package test.delete;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.BoardIdValidationArgumentsHolder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthValidationArgumentsProvider;
import providers.BoardIdValidationArgumentsProvider;
import test.BaseTest;
import static constants.BoardsEndpoints.*;
import static constants.UrlParamValues.EXISTING_BOARD_ID;

public class DeleteBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkDeleteBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments)
    {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id", EXISTING_BOARD_ID)
                .delete(DELETE_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)
    public void checkDeleteBoardWithInvalidId(BoardIdValidationArgumentsHolder validationArguments)
    {
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .delete(DELETE_BOARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }
}
