Feature: Get Card
  I want to access all my cards

  Scenario: Check Get Lists
    Given a request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 67b33f0fef35108b71345e56 |
    When the 'GET' request is sent to 'GET_ALL_LISTS' endpoint
    Then the response status code is 200

  Scenario: Check Get Cards
    Given a request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 67b33f1072bfba7883b51d70 |
    And the request has query params:
      | name   | value   |
      | fields | id,name |
    When the 'GET' request is sent to 'GET_ALL_CARDS' endpoint
    Then the response status code is 200

  Scenario: Check Get Card
    Given a request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 67b33f10bce545854a096044 |
    And the request has query params:
      | name   | value   |
      | fields | id,name |
    When the 'GET' request is sent to 'GET_A_CARD' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value |
      | name | арча           |
    And the response matches 'get_card.json' schema