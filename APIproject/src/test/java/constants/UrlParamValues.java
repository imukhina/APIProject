package constants;

import java.util.Map;

public class UrlParamValues {

    public static final String VALID_KEY = "3a4a0cd0002ce19114a124b7861eaeae";
    public static final String VALID_TOKEN = "ATTA98a6b05484cb89c3777111af6cf67f9a5f46e24fbccee3c82512431e5646912e75406C2B";

    public static final String EXISTING_BOARD_ID = "67b33f0fef35108b71345e56";
    public static final String BOARD_ID_TO_UPDATE = "67eaba49f4ae8e9b01acf741";

    public static final String EXISTING_CARD_ID = "67b33f10bce545854a096044";
    public static final String CARD_ID_TO_UPDATE = "67b33f101a822796158523d5";

    public static final String USER_NAME = "iraanischenko";
    public static final String EXISTING_LIST_ID = "67b33f1072bfba7883b51d70";

    public static final String INVALID_BOARD_ID = "77b33f0fef35108b71345e56";
    public static final String INVALID_CARD_ID = "77b33f10bce545854a096044";

    public static final Map<String, String> AUTH_QUERY_PARAMS = Map.of(
            "key", VALID_KEY,
            "token", VALID_TOKEN
    );
}
