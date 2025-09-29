Feature: Create Card Validation
  I want to validate a creation of one card

  Scenario Outline: Check Create Card With Invalid Auth
    Given a request without authorization
    And the request has query params:
      | key   | <key>   |
      | token | <token> |
    And the request has body params:
      | idList | 67b33f1072bfba7883b51d70 |
      | name   | cardName123              |
    And the request has headers:
      | Content-Type | application/json |
    When the 'POST' request is sent to 'CREATE_A_CARD' endpoint
    Then the response status code is 401
    And the response body is equal to '{"message":"missing scopes"}'

    Examples:
      | key                              | token                                                                        |
      |                                  |                                                                              |
      | 3a4a0cd0002ce19114a124b7861eaeae |                                                                              |
      |                                  | ATTA98a6b05484cb89c3777111af6cf67f9a5f46e24fbccee3c82512431e5646912e75406C2B |

  Scenario Outline: Check Create Card With Invalid Id List
    Given a request with authorization
    And the request has body params:
      | idList | <idList_value> |
      | name   | <name_value>   |
    And the request has headers:
      | Content-Type | application/json |
    When the 'POST' request is sent to 'CREATE_A_CARD' endpoint
    Then the response status code is 400
    And the response body is equal to 'invalid value for idList'

    Examples:
      | idList                   | name        |
      | 17b33f1072bfba7883b51d70 | dgdfgdfgdfg |
      |                          | jkhkhjhkj   |
      | !@%&^&(*)(_+_            | jkhkhjhkj   |
