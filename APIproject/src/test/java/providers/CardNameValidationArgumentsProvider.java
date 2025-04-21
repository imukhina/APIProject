package providers;

import arguments.holders.CardBodyArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

import static constants.UrlParamValues.EXISTING_LIST_ID;

public class CardNameValidationArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new CardBodyArgumentsHolder(
                        Map.of(
                                "name", 1.20,
                                "idList", EXISTING_LIST_ID
                        ),
                        "invalid value for idList"
                ),
                new CardBodyArgumentsHolder(
                        Map.of(
                                "name", "new card"
                        ),
                        "invalid value for idList"
                ),
                new CardBodyArgumentsHolder(
                        Map.of(
                                "name", "new card",
                                "idList", "new list"
                        ),
                        "invalid value for idList"
                )
        ).map(Arguments::of);
    }

}
