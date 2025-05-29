package test.post;

import arguments.holders.AuthValidationArgumentsHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthValidationArgumentsProvider;
import providers.BoardNameArgumentsProvider;
import test.BaseTest;
import java.util.Map;
import static constants.BoardsEndpoints.CREATE_BOARD_URL;

public class CreateBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthValidationArgumentsProvider.class)
    public void checkCreateBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments)
    {
        Response response = requestWithoutAuth()
                .queryParams(validationArguments.getAuthParams())
                .body(Map.of("name", "boardName1"))
                .contentType(ContentType.JSON)
                .post(CREATE_BOARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(BoardNameArgumentsProvider.class)
    public void checkCreateBoardWithInvalidName(Map validationArguments)
    {
        Response response = requestWithoutAuth()
                .body(validationArguments)
                .contentType(ContentType.JSON)
                .post(CREATE_BOARD_URL);
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("{\"message\":\"invalid value for name\",\"error\":\"ERROR\"}", response.body().asString());
    }
}
