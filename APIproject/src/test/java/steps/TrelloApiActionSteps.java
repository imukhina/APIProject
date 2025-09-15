package steps;

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

    public TrelloApiActionSteps(ScenarioContext scenarioContext){

        this.scenarioContext = scenarioContext;

    }

    @Given("a request {with} authorization")
    public void aRequestWithAuthorization(boolean withAuth) {
        scenarioContext.setRequest(withAuth ? requestWithAuth():requestWithoutAuth());
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

    @When("the '{}' request is sent to {string} endpoint")
    public void theRequestIsSentToEndpoint(CurlOption.HttpMethod method, String endpoint) {
        switch (method) {
            case GET -> scenarioContext.setResponse(scenarioContext.getRequest().get(endpoint));
            case POST -> scenarioContext.setResponse(scenarioContext.getRequest().post(endpoint));
            case PUT -> scenarioContext.setResponse(scenarioContext.getRequest().put(endpoint));
            case DELETE -> scenarioContext.setResponse(scenarioContext.getRequest().delete(endpoint));
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
}
