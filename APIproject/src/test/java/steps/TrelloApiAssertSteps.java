package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

import static constants.UrlParamValues.AUTH_QUERY_PARAMS;
import static org.hamcrest.Matchers.equalTo;

public class TrelloApiAssertSteps {
    private RequestSpecification request;
    private Response response;

    private static RequestSpecification requestWithAuth() {
        return requestWithoutAuth()
                .queryParams(AUTH_QUERY_PARAMS)
                .header("Accept", "application/json");
    }

    private static RequestSpecification requestWithoutAuth() {
        RestAssured.baseURI = "https://api.trello.com/1";
        return RestAssured.given();
    }

    @Then("the response status code is {int}")
    public void theResponseStatusCodeIs(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @And("the response body is equal to {string}")
    public void theResponseBodyIsEqualTo(String expectedValue) {
        Assertions.assertEquals(expectedValue, response.body().asString());
    }

    @And("the response matches '{}' schema")
    public void theResponseMatchesSchema(String schemaName) {
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/" + schemaName));
    }

    @Then("body value has the following values by paths:")
    public void bodyValueByPathIsEqualTo(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (Map<String, String> row : rows) {
            response.then().body(row.get("path"), equalTo(row.get("expected_value")));
        }

    }
}
