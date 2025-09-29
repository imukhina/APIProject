Feature: Get Boards
  I want to access all my boards

  Background: a request with authorization
    Given a request with authorization

  Scenario: Check Get Boards
    And the request has path params:
      | name | value         |
      | id   | iraanischenko |
    When the 'GET' request is sent to 'GET_ALL_BOARDS' endpoint
    Then the response status code is 200

  Scenario: Check Get Board
    And the request has path params:
      | name | value                    |
      | id   | 67b33f0fef35108b71345e56 |
    And the request has query params:
      | name   | value   |
      | fields | id,name |
    When the 'GET' request is sent to 'GET_A_BOARD' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value |
      | name | проспро        |
    And the response matches 'get_board.json' schema

