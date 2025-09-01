Feature: Get Board Validation
  I want to validate board test

  Scenario Outline: Check Get Board With Invalid Auth
    Given a request without authorization
    And the request has path params:
      | name   | value   |
      | fields | id,name |
    And the request has query params:
      | key   | <key>   |
      | token | <token> |
    When the 'GET' request is sent to '/boards/{id}' endpoint
    Then the response status code is 401
    And the response body is equal to '<error_message>'

    Examples:
      | key                              | token                                                                        | error_message                     |
      |                                  |                                                                              | unauthorized permission requested |
      | 3a4a0cd0002ce19114a124b7861eaeae |                                                                              | unauthorized permission requested |
      |                                  | ATTA98a6b05484cb89c3777111af6cf67f9a5f46e24fbccee3c82512431e5646912e75406C2B | invalid key                       |