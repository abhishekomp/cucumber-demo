Feature: Shared context resets between scenarios

  Scenario: First scenario sets value and list
    Given the context value is "Hello"
    When I add "Apple" to the list
    When I add "Banana" to the list
    Then the context value should be "Hello"
    And the list should contain 2 items

  Scenario: Second scenario starts fresh
    Given the context value is "World"
    When I add "Cherry" to the list
    Then the context value should be "World"
    And the list should contain 1 items
    And the only item in the context list should be "Cherry"

    # This scenario demonstrates interaction with a shared commons module (the multi-module project setup)
    # It also uses the Spring Dependency Injection to inject a Shared Context bean that each scenario gets a fresh instance of and can use to share state between steps in the same scenario
    Scenario: Third scenario verifies interaction with commons module
      Given I hava a map with key "key1" and value "value1"
      When I serialize the map to JSON
      Then the JSON should be '{"key1":"value1"}'
