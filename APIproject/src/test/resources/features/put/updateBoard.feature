Feature: Update Board
  I want to update a board

  Scenario: Check Update Board
    #update board
    Given a request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 67eaba49f4ae8e9b01acf741 |
    And the request has body params:
      | name | boardName |
    And the request has headers:
      | Content-Type | application/json |
    When the 'PUT' request is sent to '/boards/{id}' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value |
      | name | boardName      |
    #check board name updated
    Given a request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 67eaba49f4ae8e9b01acf741 |
    When the 'GET' request is sent to '/boards/{id}' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value |
      | name | boardName      |