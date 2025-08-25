package test.get;

import io.qameta.allure.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.BaseTest;
import static constants.CardsEndpoints.*;
import static constants.UrlParamValues.*;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.Matchers.equalTo;

public class GetCardTest extends BaseTest {

    @Test
    @Epic("Trello API")
    @Feature("Работа с досками")
    @Story("Получение списков на доске")
    @Description("Проверка получения списков")
    @Link(name = "Trello API Docs", url = "https://developer.atlassian.com/cloud/trello/rest/api-group-boards/")
    public void checkGetLists()
    {
        requestWithAuth()
                .pathParam("id", EXISTING_BOARD_ID)
                .get(GET_ALL_LISTS_URL)
                .then()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("GET /cards")
    public void checkGetCards() {
        Allure.parameter("listId", EXISTING_LIST_ID);

        var resp = step("Делаем запрос и проверяем статус", () ->
                requestWithAuth()
                        .pathParam("id", EXISTING_LIST_ID)
                        .queryParams("fields", "id,name")
                        .get(GET_ALL_CARDS_URL)
                        .then()
                        .statusCode(200)
                        .extract().response()
        );

        Allure.addAttachment("cards.json", "application/json", resp.prettyPrint(), ".json");
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
