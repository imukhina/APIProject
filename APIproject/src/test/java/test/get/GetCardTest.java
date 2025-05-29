package test.get;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import test.BaseTest;
import static constants.CardsEndpoints.*;
import static constants.UrlParamValues.*;
import static org.hamcrest.Matchers.equalTo;

public class GetCardTest extends BaseTest {

    @Test
    public void checkGetLists()
    {
        requestWithAuth()
                .pathParam("id", EXISTING_BOARD_ID)
                .get(GET_ALL_LISTS_URL)
                .then()
                .statusCode(200);
    }

    @Test
    public void checkGetCards()
    {
        requestWithAuth()
                .pathParam("id", EXISTING_LIST_ID)
                .queryParams("fields","id,name")
                .get(GET_ALL_CARDS_URL)
                .then()
                .statusCode(200);
    }

    @Test
    public void checkGetCard()
    {
            requestWithAuth()
                    .pathParam("id", EXISTING_CARD_ID)
                    .queryParams("fields", "id,name")
                    .get(GET_CARD_URL)
                    .then()
                    .statusCode(200)
                    .body("name", equalTo("арча"))
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"));
    }
}
