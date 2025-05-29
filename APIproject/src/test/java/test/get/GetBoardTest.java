package test.get;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import test.BaseTest;
import static constants.BoardsEndpoints.GET_ALL_BOARDS_URL;
import static constants.BoardsEndpoints.GET_BOARD_URL;
import static constants.UrlParamValues.EXISTING_BOARD_ID;
import static constants.UrlParamValues.USER_NAME;
import static org.hamcrest.Matchers.equalTo;

public class GetBoardTest extends BaseTest {

    @Test
    public void checkGetBoards()
    {
            requestWithAuth()
                    .pathParam("id", USER_NAME)
                    .get(GET_ALL_BOARDS_URL)
                    .then()
                    .statusCode(200);
    }

    @Test
    public void checkGetBoard()
    {
           requestWithAuth()
                   .pathParam("id", EXISTING_BOARD_ID)
                   .queryParams("fields", "id,name")
                   .get(GET_BOARD_URL)
                   .then()
                   .statusCode(200)
                   .body("name", equalTo("проспро"))
                   .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"));
    }
}
