package test.delete;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardIdValidationArgumentsHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthCardValidationScopeArgumentsProvider;
import providers.CardIdValidationArgumentProvider;
import test.BaseTest;
import static constants.CardsEndpoints.DELETE_CARD_URL;
import static constants.UrlParamValues.EXISTING_CARD_ID;

public class DeleteCardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthCardValidationScopeArgumentsProvider.class)
    public void checkDeleteCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments)
    {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id", EXISTING_CARD_ID)
                .contentType(ContentType.JSON)
                .delete(DELETE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentProvider.class)
    public void checkDeleteCardWithInvalidId(CardIdValidationArgumentsHolder validationArguments)
    {
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .delete(DELETE_CARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }
}
