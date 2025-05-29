package test.put;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import test.BaseTest;
import java.time.LocalDateTime;
import java.util.Map;
import static constants.BoardsEndpoints.*;
import static constants.UrlParamValues.*;

public class UpdateBoardTest extends BaseTest {

    @Test
    public void checkUpdateBoard()
    {
        String boardName = "FirstBoard"+ LocalDateTime.now();
        Response response = requestWithAuth()
                .pathParam("id", BOARD_ID_TO_UPDATE)
                .body(Map.of("name", boardName))
                .contentType(ContentType.JSON)
                .put(UPDATE_BOARD_URL);
        response
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(boardName));
        requestWithAuth()
                .pathParam("id", BOARD_ID_TO_UPDATE)
                .get(GET_BOARD_URL)
                .then()
                .body("name",Matchers.equalTo(boardName));
    }
}
