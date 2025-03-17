import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GetBoardsTests extends BaseTest{

    @Test
    public void CheckGetBoards()
    {
            requestWithAuth()
                .log().all()
                .pathParam("id","iraanischenko")
                .get("/members/{id}/boards")
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .log().body();

    }
    @Test
    public void CheckGetBoard()
    {
            requestWithAuth()
                .log().all()
                .pathParam("id","67b33f0fef35108b71345e56")
                    .queryParams("fields", "id,name")
                .get("/boards/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("проспро"))
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"))
                .log().body();
    }
}
