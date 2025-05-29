package test.post;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;
import java.time.LocalDateTime;
import java.util.Map;
import static constants.BoardsEndpoints.*;
import static constants.UrlParamValues.USER_NAME;

public class CreateBoardTest extends BaseTest {

private String createBoardId;

    @Test
    public void checkCreateBoard()
    {
        String boardName = "FirstBoard"+ LocalDateTime.now();
        Response response = requestWithAuth()
                .body(Map.of("name", boardName))
                .contentType(ContentType.JSON)
                .post(CREATE_BOARD_URL);
        createBoardId = response.jsonPath().get("id");
        response
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(boardName));
        requestWithAuth()
                .pathParam("id", USER_NAME)
                .get(GET_ALL_BOARDS_URL)
                .then()
                .statusCode(200)
                .body("name",Matchers.hasItem(boardName));
    }

    @AfterEach
        public void deleteCreateBoard(){
            requestWithAuth()
                .pathParam("id", createBoardId)
                .delete(DELETE_BOARD_URL)
                .then()
                .statusCode(200);
    }
}
