package providers;

import arguments.holders.CardIdValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class CardIdValidationArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new CardIdValidationArgumentsHolder(
                        Map.of("id","invalid"),
                        "invalid id",
                        400
                ),
                new CardIdValidationArgumentsHolder(
                        Map.of("id","67eaba49f4ae8e9b01acf740"),
                        "The requested resource was not found.",
                        404
                )
        ).map(Arguments::of);
    }
}
