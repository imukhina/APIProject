package steps;

import com.sun.net.httpserver.Request;
import io.cucumber.core.options.CurlOption;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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

    @And("the request has path param")
    public void theRequestHasPathParam(){
        request = request.pathParam("id", USER_NAME);
    }

    @When("the request is sent to getBoards endpoint")
    public void theRequestIsSentToEndpoint(){
        response = request.get("/members/{id}/boards");
    }

    @Then("the response status code is {int}")
    public void theResponseStatusCodeIs(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }
}
