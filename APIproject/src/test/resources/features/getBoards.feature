Feature: Get Boards
  I want to access all my boards

  Scenario: Check Get Boards
    Given a request with authorization
    And the request has path params:
      | name | value         |
      | id   | iraanischenko |
    When the 'GET' request is sent to '/members/{id}/boards' endpoint
    Then the response status code is 200

  Scenario: Check Get Board
    Given a request with authorization
    And the request has path param
    And the request has query param
    When the request is sent to getBoard endpoint
    Then the response status code is 200
    And body value has the following values by paths
    And the response matches get_board.json schema

