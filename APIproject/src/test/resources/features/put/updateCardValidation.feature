Feature: Update Card Validation
  I want to validate update a card

  Scenario Outline: Check Update Card With Invalid Auth
    Given a request without authorization
    And the request has path params:
      | name | value                    |
      | id   | 67b33f101a822796158523d5 |
    And the request has body params:
      | name | cardName1 |
    And the request has headers:
      | Content-Type | application/json |
    And the request has query params:
      | key   | <key>   |
      | token | <token> |
    When the 'PUT' request is sent to '/cards/{id}' endpoint
    Then the response status code is 401
    And the response body is equal to '{"message":"missing scopes"}'

    Examples:
      | key                              | token                                                                        |
      |                                  |                                                                              |
      | 3a4a0cd0002ce19114a124b7861eaeae |                                                                              |
      |                                  | ATTA98a6b05484cb89c3777111af6cf67f9a5f46e24fbccee3c82512431e5646912e75406C2B |

  Scenario Outline: Check Update Card With Invalid Id
    Given a request with authorization
    And the request has path params:
      | name | value      |
      | id   | <id_value> |
    When the 'PUT' request is sent to '/cards/{id}' endpoint
    Then the response status code is <status_code>
    And the response body is equal to '<error_message>'

    Examples:
      | id_value                 | status_code | error_message                         |
      | invalid                  | 400         | invalid id                            |
      | 77b33f0fef35108b71345e56 | 404         | The requested resource was not found. |
