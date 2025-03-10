import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;

public class GetCardsTests extends BaseTest{
    @Test
    public void CheckGetLists()
    {
        requestWithAuth()
                .log().all()
                .pathParam("id","67b33f0fef35108b71345e56")
                .get("/boards/{id}/lists")
                .then()
                .statusCode(200)
                .log().body();
    }
    @Test
    public void CheckGetCards()
    {
        requestWithAuth()
                .log().all()
                .pathParam("id","67b33f1072bfba7883b51d70")
                .get("/lists/{id}/cards")
                .then()
                .statusCode(200)
                .log().body();
    }
    @Test
    public void CheckGetCard()
    {
        requestWithAuth()
                .log().all()
                .pathParam("id","67b33f10bce545854a096044")
                .get("/cards/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("арча"))
                .log().body();
    }
}
