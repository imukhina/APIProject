package steps;

import io.cucumber.core.options.CurlOption;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.UrlParamValues.AUTH_QUERY_PARAMS;
import static constants.UrlParamValues.USER_NAME;

public class TrelloApiSteps {

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

    @Given("a request with authorization")
    public void aRequestWithAuthorization() {
        request = requestWithAuth();
    }

    @And("the request has path params:")
    public void theRequestHasPathParams(DataTable dataTable) {
        Map<String, String> pathParams = new HashMap<>();
        List<Map<String, String>> rows = dataTable.asMaps();
        for (Map<String, String> row : rows) {
            pathParams.put(row.get("name"), row.get("value"));
        }
        request = request.pathParams(pathParams);
    }

    @When("the '{}' request is sent to {string} endpoint")
    public void theRequestIsSentToEndpoint(CurlOption.HttpMethod method, String endpoint) {
        switch (method) {
            case GET -> response = request.get(endpoint);
            case POST -> response = request.post(endpoint);
            case PUT -> response = request.put(endpoint);
            case DELETE -> response = request.delete(endpoint);
            default -> throw new RuntimeException();
        }
    }

    @Then("the response status code is {int}")
    public void theResponseStatusCodeIs(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }
}
