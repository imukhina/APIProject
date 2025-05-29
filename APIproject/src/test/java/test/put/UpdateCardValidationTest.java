package test.put;

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
import java.util.Map;
import static constants.CardsEndpoints.UPDATE_CARD_URL;
import static constants.UrlParamValues.CARD_ID_TO_UPDATE;

public class UpdateCardValidationTest  extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthCardValidationScopeArgumentsProvider.class)
    public void checkUpdateCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments)
    {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id", CARD_ID_TO_UPDATE)
                .body(Map.of("name", "cardName1"))
                .contentType(ContentType.JSON)
                .put(UPDATE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(CardIdValidationArgumentProvider.class)
    public void checkUpdateCardWithInvalidId(CardIdValidationArgumentsHolder validationArguments)
    {
        Response response = requestWithAuth()
                .pathParams(validationArguments.getPathParams())
                .put(UPDATE_CARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }
}
