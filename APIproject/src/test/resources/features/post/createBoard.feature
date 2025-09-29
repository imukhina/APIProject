Feature: Create Board
  I want to create one board

  @deleteBoard
  Scenario: Check Create Board
    Given a request with authorization
    And the request has body params:
      | name | fdhgshsfhsftghf |
    And the request has headers:
      | Content-Type | application/json |
    When the 'POST' request is sent to 'CREATE_A_BOARD' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value  |
      | name | fdhgshsfhsftghf |
    And the board id from the response is remembered
    Given a request with authorization
    And the request has path params:
      | name | value         |
      | id   | iraanischenko |
    When the 'GET' request is sent to 'GET_ALL_BOARDS' endpoint
    Then the response status code is 200
    And body value has one of the following values by paths:
      | path | expected_value  |
      | name | fdhgshsfhsftghf |
