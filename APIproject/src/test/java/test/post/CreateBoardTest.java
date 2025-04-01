package test.post;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

import static constants.BoardsEndpoints.*;
import static constants.UrlParamValues.EXISTING_BOARD_ID;
import static org.hamcrest.Matchers.equalTo;

public class CreateBoardTest extends BaseTest {

private String createBoardId;

    @Test
    public void checkCreateBoard()
    {
        String boardName = "FirstBoard"+ LocalDateTime.now();
        Response response = BaseTest.requestWithAuth()
                .log().all()
                .body(Map.of("name", boardName))
                .contentType(ContentType.JSON)
                .post(CREATE_BOARD_URL);
        createBoardId = response.jsonPath().get("id");
        response
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(boardName));
    }

@AfterEach
    public void deleteCreateBoard(){
        BaseTest.requestWithAuth()
                .pathParam("id", createBoardId)
                .delete(DELETE_BOARD_URL)
                .then()
                .statusCode(200);
    }
}
