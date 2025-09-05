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
