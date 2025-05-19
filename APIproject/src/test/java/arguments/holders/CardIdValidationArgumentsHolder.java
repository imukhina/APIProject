package arguments.holders;

import java.util.Map;

public class CardIdValidationArgumentsHolder {
    private final Map<String, String> pathParams;

    private final String errorMessage;

    private final Integer statusCode;

    public CardIdValidationArgumentsHolder(Map<String, String> pathParams, String errorMessage, Integer statusCode) {
        this.pathParams = pathParams;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public Map<String, String> getPathParams(){
        return pathParams;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public Integer getStatusCode(){
        return statusCode;
    }

}
