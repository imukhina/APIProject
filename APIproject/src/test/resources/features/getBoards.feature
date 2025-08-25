Feature: Get Boards
  I want to access all my boards

  Scenario: Check Get Boards
    Given a request with authorization
    And the request has path param
    When the request is sent to getBoards endpoint
    Then the response status code is 200