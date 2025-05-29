package test.delete;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;
import java.util.Map;
import static constants.BoardsEndpoints.*;
import static constants.UrlParamValues.USER_NAME;

public class DeleteBoardTest extends BaseTest {

    private String createBoardId;

    @BeforeEach
    public void createBoard(){
        createBoardId = requestWithAuth()
                .body(Map.of("name", "New Board"))
                .contentType(ContentType.JSON)
                .post(CREATE_BOARD_URL)
                .body().jsonPath().get("id");
    }

    @Test
    public void checkDeleteBoard(){
        requestWithAuth()
                .pathParam("id", createBoardId)
                .delete(DELETE_BOARD_URL)
                .then()
                .statusCode(200);
        requestWithAuth()
                .pathParam("id", USER_NAME)
                .queryParams("fields", "name,id")
                .get(GET_ALL_BOARDS_URL)
                .then()
                .statusCode(200)
                .body("id", Matchers.not(Matchers.hasItem(createBoardId)));
    }
}
