package test.delete;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;
import java.util.Map;
import static constants.CardsEndpoints.*;
import static constants.UrlParamValues.EXISTING_LIST_ID;

public class DeleteCardTest extends BaseTest {

    private String cardNameId;

    @BeforeEach
    public void createCard(){

        cardNameId = requestWithAuth()
                .body(Map.of(
                        "idList", EXISTING_LIST_ID,
                        "name", "cardName"))
                .contentType(ContentType.JSON)
                .post(CREATE_CARD_URL)
                .body().jsonPath().get("id");
    }

    @Test
    public void checkDeleteCard(){
        requestWithAuth()
                .pathParam("id", cardNameId)
                .delete(DELETE_CARD_URL)
                .then()
                .statusCode(200);
        requestWithAuth()
                .pathParam("id", EXISTING_LIST_ID)
                .queryParams("fields", "name,id")
                .get(GET_ALL_CARDS_URL)
                .then()
                .statusCode(200)
                .body("id", Matchers.not(Matchers.hasItem(cardNameId)));
    }
}
