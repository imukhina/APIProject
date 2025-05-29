package test.put;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import test.BaseTest;
import java.time.LocalDateTime;
import java.util.Map;
import static constants.CardsEndpoints.GET_CARD_URL;
import static constants.CardsEndpoints.UPDATE_CARD_URL;
import static constants.UrlParamValues.CARD_ID_TO_UPDATE;

public class UpdateCardTest extends BaseTest {

    @Test
    public void checkUpdateCard()
    {
        String cardName = "FirstCard" + LocalDateTime.now();
        Response response = requestWithAuth()
                .pathParam("id", CARD_ID_TO_UPDATE)
                .body(Map.of("name", cardName))
                .contentType(ContentType.JSON)
                .put(UPDATE_CARD_URL);
        response
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(cardName));
        requestWithAuth()
                .pathParam("id", CARD_ID_TO_UPDATE)
                .get(GET_CARD_URL)
                .then()
                .body("name",Matchers.equalTo(cardName));
    }
}