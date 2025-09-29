package steps;

import constants.Endpoint;
import io.cucumber.core.options.CurlOption;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.AuthorizationRequestProvider.requestWithAuth;
import static utils.AuthorizationRequestProvider.requestWithoutAuth;

public class TrelloApiActionSteps {

    private final ScenarioContext scenarioContext;

    public TrelloApiActionSteps(ScenarioContext scenarioContext) {

        this.scenarioContext = scenarioContext;
    }

    @Given("a request {with} authorization")
    public void aRequestWithAuthorization(boolean withAuth) {
        scenarioContext.setRequest(withAuth ? requestWithAuth() : requestWithoutAuth());
    }

    @And("the request has path params:")
    public void theRequestHasPathParams(DataTable dataTable) {
        Map<String, String> pathParams = new HashMap<>();
        List<Map<String, String>> rows = dataTable.asMaps();
        for (Map<String, String> row : rows) {
            pathParams.put(row.get("name"), row.get("value"));
        }
        scenarioContext.setRequest(scenarioContext.getRequest().pathParams(pathParams));
    }

    @And("the request has query params:")
    public void theRequestHasQueryParams(DataTable dataTable) {
        Map<String, String> queryParams = new HashMap<>();
        List<Map<String, String>> rows = dataTable.asMaps();
        for (Map<String, String> row : rows) {
            queryParams.put(row.get("name"), row.get("value"));
        }
        queryParams.entrySet().removeIf(e -> e.getValue() == null);
        scenarioContext.setRequest(scenarioContext.getRequest().queryParams(queryParams));
    }

    @When("the '{}' request is sent to '{endpoint}' endpoint")
    public void theRequestIsSentToEndpoint(CurlOption.HttpMethod method, Endpoint endpoint) {
        String url = endpoint.getUrl();
        switch (method) {
            case GET -> scenarioContext.setResponse(scenarioContext.getRequest().get(url));
            case POST -> scenarioContext.setResponse(scenarioContext.getRequest().post(url));
            case PUT -> scenarioContext.setResponse(scenarioContext.getRequest().put(url));
            case DELETE -> scenarioContext.setResponse(scenarioContext.getRequest().delete(url));
            default -> throw new RuntimeException();
        }
    }

    @And("the request has body params:")
    public void theRequestHasBodyParams(DataTable dataTable) {
        scenarioContext.setRequest(scenarioContext.getRequest().body(dataTable.asMap()));
    }

    @And("the request has headers:")
    public void theRequestHasHeaders(DataTable dataTable) {
        scenarioContext.setRequest(scenarioContext.getRequest().headers(dataTable.asMap()));
    }

    @And("the board id from the response is remembered")
    public void theBoardIdFromTheResponseIsRemembered() {
        String createdBoardId = scenarioContext.getResponse().body().jsonPath().get("id");
        scenarioContext.setBoardId(createdBoardId);
    }

    @And("the card id from the response is remembered")
    public void theCardIdFromTheResponseIsRemembered() {
        String createdCardId = scenarioContext.getResponse().body().jsonPath().get("id");
        scenarioContext.setCardId(createdCardId);
    }
}
