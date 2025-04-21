package test.post;

import arguments.holders.AuthValidationArgumentsHolder;
import arguments.holders.CardBodyArgumentsHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthCardValidationArgumentsProvider;
import providers.CardNameValidationArgumentsProvider;
import test.BaseTest;
import java.util.Map;
import static constants.CardsEndpoints.CREATE_CARD_URL;
import static constants.UrlParamValues.EXISTING_LIST_ID;

public class CreateCardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthCardValidationArgumentsProvider.class)
    public void checkCreateCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments)
    {
        Response response = BaseTest.requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .body(Map.of(
                        "idList", EXISTING_LIST_ID,
                        "name", "cardName123"))
                .contentType(ContentType.JSON)
                .post(CREATE_CARD_URL);
        response
                .then()
                .statusCode(401)
                .log().body();
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(CardNameValidationArgumentsProvider.class)
    public void checkCreateCardWithInvalidName(CardBodyArgumentsHolder bodyParams)
    {
        Response response = BaseTest.requestWithoutAuth()
                .body(bodyParams)
                .contentType(ContentType.JSON)
                .post(CREATE_CARD_URL);
        response
                .then()
                .statusCode(400)
                .log().body();
        Assertions.assertEquals(bodyParams.getErrorMessage(),response.body().asString());
    }
}

