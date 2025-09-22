package steps;

import io.cucumber.java.After;

import static constants.BoardsEndpoints.DELETE_BOARD_URL;
import static utils.AuthorizationRequestProvider.requestWithAuth;

public class Hooks {

    private final ScenarioContext scenarioContext;

    public Hooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @After("@deleteBoard")
    public void deleteBoard() {
        requestWithAuth()
                .pathParam("id", scenarioContext.getBoardId())
                .delete(DELETE_BOARD_URL)
                .then()
                .statusCode(200);
    }
}
