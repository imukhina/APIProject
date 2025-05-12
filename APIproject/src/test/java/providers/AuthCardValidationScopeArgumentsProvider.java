package providers;

import arguments.holders.AuthValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static constants.UrlParamValues.VALID_KEY;
import static constants.UrlParamValues.VALID_TOKEN;

public class AuthCardValidationScopeArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new AuthValidationArgumentsHolder(
                        Collections.emptyMap(),
                        "{\"message\":\"missing scopes\"}"
                ),
                new AuthValidationArgumentsHolder(
                        Map.of(
                                "key", VALID_KEY),
                        "{\"message\":\"missing scopes\"}"
                ),
                new AuthValidationArgumentsHolder(
                        Map.of(
                                "token", VALID_TOKEN),
                        "invalid key"
                )
        ).map(Arguments::of);
    }
}
