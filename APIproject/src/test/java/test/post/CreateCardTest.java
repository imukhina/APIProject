package test.post;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;
import java.time.LocalDateTime;
import java.util.Map;
import static constants.CardsEndpoints.*;
import static constants.UrlParamValues.EXISTING_LIST_ID;


public class CreateCardTest extends BaseTest {

    private String createdCardId;

    @Test
    public void checkCreateCard()
    {
        String cardName = "FirstCard"+ LocalDateTime.now();
        Response response = BaseTest.requestWithAuth()
                .body(Map.of(
                        "idList", EXISTING_LIST_ID,
                        "name", cardName))
                .contentType(ContentType.JSON)
                .post(CREATE_CARD_URL);
        createdCardId = response.jsonPath().get("id");
        response
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(cardName));
        requestWithAuth()
                .pathParam("id", EXISTING_LIST_ID)
                .get(GET_ALL_CARDS_URL)
                .then()
                .statusCode(200)
                .body("name",Matchers.hasItem(cardName));
    }

    @AfterEach
    public void deleteCreateCard(){
        BaseTest.requestWithAuth()
                .pathParam("id", createdCardId)
                .delete(DELETE_CARD_URL)
                .then()
                .statusCode(200);
    }
}
