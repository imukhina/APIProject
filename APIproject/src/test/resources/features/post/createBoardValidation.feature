Feature: Create Board Validation
  I want to validate a creation of one board

  Scenario Outline: Check Create Board With Invalid Auth
    Given a request without authorization
    And the request has query params:
      | key   | <key>   |
      | token | <token> |
    And the request has body params:
      | name | fdhgshsfhsftghf |
    And the request has headers:
      | Content-Type | application/json |
    When the 'POST' request is sent to '/boards/' endpoint
    Then the response status code is 401
    And the response body is equal to '{"message":"missing scopes"}'

    Examples:
      | key                              | token                                                                        |
      |                                  |                                                                              |
      | 3a4a0cd0002ce19114a124b7861eaeae |                                                                              |
      |                                  | ATTA98a6b05484cb89c3777111af6cf67f9a5f46e24fbccee3c82512431e5646912e75406C2B |

  Scenario Outline: Check Create Board With Invalid Name
    Given a request with authorization
    And the request has body params:
      | <body_key> | <body_value> |
    And the request has headers:
      | Content-Type | application/json |
    When the 'POST' request is sent to '/boards/' endpoint
    Then the response status code is <status_code>
    And the response body is equal to '<error_message>'

    Examples:
      | body_key | body_value | status_code | error_message                                        |
      | name     |            | 400         | {"message":"invalid value for name","error":"ERROR"} |
      | ''       | ''         | 400         | {"message":"invalid value for name","error":"ERROR"}               |
