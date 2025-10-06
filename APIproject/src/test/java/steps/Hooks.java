package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.LocalDateTime;
import java.util.Map;

import static constants.BoardsEndpoints.CREATE_BOARD_URL;
import static constants.BoardsEndpoints.DELETE_BOARD_URL;
import static constants.CardsEndpoints.DELETE_CARD_URL;
import static utils.AuthorizationRequestProvider.requestWithAuth;

public class Hooks {

    private final ScenarioContext scenarioContext;

    public Hooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Before("@createBoard")
    public void createBoard() {
        String boardId = requestWithAuth()
                .body(Map.of("name", "FirstBoard"))
                .contentType(ContentType.JSON)
                .post(CREATE_BOARD_URL)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().get("id");
        scenarioContext.setBoardId(boardId);
    }

    @After("@deleteBoard")
    public void deleteBoard() {
        requestWithAuth()
                .pathParam("id", scenarioContext.getBoardId())
                .delete(DELETE_BOARD_URL)
                .then()
                .statusCode(200);
    }

    @After("@deleteCard")
    public void deleteCard() {
        requestWithAuth()
                .pathParam("id", scenarioContext.getCardId())
                .delete(DELETE_CARD_URL)
                .then()
                .statusCode(200);
    }
}
