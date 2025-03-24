package test.get;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import static constants.CardsEndpoints.*;
import static constants.UrlParamValues.EXISTING_CARD_ID;
import static org.hamcrest.Matchers.equalTo;

public class GetCardsTests extends BaseTest {

    @Test
    public void checkGetLists()
    {
        BaseTest.requestWithAuth()
                .log().all()
                .pathParam("id","67b33f0fef35108b71345e56")
                .get(GET_ALL_LISTS_URL)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void checkGetCards()
    {
        BaseTest.requestWithAuth()
                .log().all()
                .pathParam("id","67b33f1072bfba7883b51d70")
                .get(GET_ALL_CARDS_URL)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void checkGetCard()
    {
        BaseTest.requestWithAuth()
                .log().all()
                .pathParam("id",EXISTING_CARD_ID)
                    .queryParams("fields", "id,name")
                .get(GET_CARD_URL)
                .then()
                .statusCode(200)
                .body("name", equalTo("арча"))
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"))
                .log().body();
    }
}
