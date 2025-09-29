Feature: Create Card
  I want to create one card

  @deleteCard
  Scenario: Check Create Card
    Given a request with authorization
    And the request has body params:
      | idList | 67b33f1072bfba7883b51d70 |
      | name   | dgdfgdfgdfg              |
    And the request has headers:
      | Content-Type | application/json |
    When the 'POST' request is sent to 'CREATE_A_CARD' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value |
      | name | dgdfgdfgdfg    |
    And the card id from the response is remembered
    Given a request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 67b33f1072bfba7883b51d70 |
    When the 'GET' request is sent to 'GET_ALL_CARDS' endpoint
    Then the response status code is 200
    And body value has one of the following values by paths:
      | path | expected_value |
      | name | dgdfgdfgdfg    |
