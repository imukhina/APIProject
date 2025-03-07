import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class TreloRestAssuredTest {

    @Test
    public void CheckGetBoards()
    {
        RestAssured.given()
                .log().all()
                .baseUri("https://api.trello.com")
                .pathParam("id","iraanischenko")
                .queryParams(Map.of("key","3a4a0cd0002ce19114a124b7861eaeae",
                        "token","ATTA98a6b05484cb89c3777111af6cf67f9a5f46e24fbccee3c82512431e5646912e75406C2B"))
                .header("Accept","application/json")
                .get("/1/members/{id}/boards")
                .then()
                .statusCode(200)
                .log().body();

    }
    @Test
    public void CheckGetBoard()
    {
        RestAssured.given()
                .log().all()
                .baseUri("https://api.trello.com")
                .pathParam("id","67b33f0fef35108b71345e56")
                .queryParams(Map.of("key","3a4a0cd0002ce19114a124b7861eaeae",
                        "token","ATTA98a6b05484cb89c3777111af6cf67f9a5f46e24fbccee3c82512431e5646912e75406C2B"))
                .header("Accept","application/json")
                .get("/1/boards/{id}")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("проспро"))
                .log().body();

    }

}
