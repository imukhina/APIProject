Feature: Delete a Board
  I want to delete my board

  @createBoard
  Scenario: Delete Board
    Given a request with authorization
    And the request has path params:
      | name | value   |
      | id   | created_board_id |
    When the 'DELETE' request is sent to 'DELETE_A_BOARD' endpoint
    Then the response status code is 200
    Given a request with authorization
    And the request has path params:
      | name | value         |
      | id   | iraanischenko |
    And the request has query params:
      | name   | value   |
      | fields | name,id |
    When the 'GET' request is sent to 'GET_ALL_BOARDS' endpoint
    Then the response status code is 200
    And the response body doesn't have item:
      | id   | created_board_id   |

