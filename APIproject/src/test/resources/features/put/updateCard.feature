Feature: Update Card
  I want to update a card

  Scenario: Check Update Card
    #update card
    Given a request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 67b33f101a822796158523d5 |
    And the request has body params:
      | name | cardName |
    And the request has headers:
      | Content-Type | application/json |
    When the 'PUT' request is sent to '/cards/{id}' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value |
      | name | cardName       |
    #check card name updated
    Given a request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 67b33f101a822796158523d5 |
    When the 'GET' request is sent to '/cards/{id}' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value |
      | name | cardName       |