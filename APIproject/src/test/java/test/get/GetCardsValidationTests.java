package test.get;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import static constants.CardsEndpoints.GET_CARD_URL;
import static constants.UrlParamValues.EXISTING_CARD_ID;
import static org.hamcrest.Matchers.lessThan;

public class GetCardsValidationTests extends BaseTest {

    @Test
    public void checkGetCardsWithInvalidAuth()
    {
        Response response = BaseTest.requestWithoutAuth()
                .pathParam("id",EXISTING_CARD_ID)
                .get(GET_CARD_URL);
        response
                .then()
                .statusCode(401)
                .time(lessThan(1000L))
                .log().body();
        Assertions.assertEquals("unauthorized card permission requested", response.body().asString());
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
                .time(lessThan(1000L))
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
