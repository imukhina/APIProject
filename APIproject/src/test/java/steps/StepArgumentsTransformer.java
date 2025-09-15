package steps;

import io.cucumber.java.ParameterType;

public class StepArgumentsTransformer {

    @ParameterType("(with|without)")
    public boolean with(String string){
        return "with".equals(string);
    }
}
