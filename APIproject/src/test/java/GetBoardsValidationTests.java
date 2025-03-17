import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.lessThan;

public class GetBoardsValidationTests extends BaseTest{
    @Test
    public void CheckGetBoardsWithInvalidAuth()
    {
        Response response = requestWithoutAuth()
                .pathParam("id","67b33f0fef35108b71345e56")
                .get("/boards/{id}");
        response
                .then()
                .statusCode(401)
                .time(lessThan(1000L))
                .log().body();
        Assertions.assertEquals("unauthorized permission requested", response.body().asString());
    }
    @Test
    public void CheckGetBoardsWithInvalidID()
    {
        Response response = requestWithAuth()
                .pathParam("id","invalid")
                .get("/boards/{id}");
        response
                .then()
                .statusCode(400)
                .time(lessThan(1000L))
                .log().body();
        Assertions.assertEquals("invalid id", response.body().asString());
    }
    @Test
    public void CheckGetBoardsWithInvalidResours()
    {
        Response response = requestWithAuth()
                .pathParam("id","67b33f0fef35108b71345e56")
                .get("/boards/{id}");
        response
                .then()
                .statusCode(404)
                .time(lessThan(1000L))
                .log().body();
        Assertions.assertEquals("The requested resource was not found.", response.body().asString());
    }

}
