package test.get;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import static constants.BoardsEndpoints.GET_BOARD_URL;
import static constants.UrlParamValues.EXISTING_BOARD_ID;
import static org.hamcrest.Matchers.lessThan;

public class GetBoardsValidationTests extends BaseTest {

    @Test
    public void checkGetBoardsWithInvalidAuth()
    {
        Response response = BaseTest.requestWithoutAuth()
                .pathParam("id",EXISTING_BOARD_ID)
                .get(GET_BOARD_URL);
        response
                .then()
                .statusCode(401)
                .time(lessThan(1000L))
                .log().body();
        Assertions.assertEquals("unauthorized permission requested", response.body().asString());
    }

    @Test
    public void checkGetBoardsWithInvalidId()
    {
        Response response = BaseTest.requestWithAuth()
                .pathParam("id","invalid")
                .get(GET_BOARD_URL);
        response
                .then()
                .statusCode(400)
                .time(lessThan(1000L))
                .log().body();
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void checkGetBoardsWithInvalidResourse()
    {
        Response response = BaseTest.requestWithAuth()
                .pathParam("id","77b33f0fef35108b71345e56")
                .get(GET_BOARD_URL);
        response
                .then()
                .statusCode(404)
                .time(lessThan(1000L))
                .log().body();
        Assertions.assertEquals("The requested resource was not found.", response.body().asString());
    }
}
