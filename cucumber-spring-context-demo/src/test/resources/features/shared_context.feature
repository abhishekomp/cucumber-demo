Feature: Shared context resets between scenarios

  # This scenario sets a value and adds items to a list in the shared context (take a look at the SharedContext class).
  # The purpose is to demonstrate that changes made in this scenario do not affect the next scenario.
  # The context is expected to be reset between scenarios, so the second scenario should start with
  # the default values defined in the SharedContext class.
  Scenario: First scenario sets value and list
    Given the context value is "Hello"
    When I add "Apple" to the list
    When I add "Banana" to the list
    Then the context value should be "Hello"
    And the list should contain 2 items

  # The objective here is to show that the context (SharedContext class instance) is reset between scenarios.
  # When this scenario runs, it should not see the changes made to the SharedContext instance in the first scenario.
  # Therefore, the context value should be "World" (the default) and the list should only contain "Cherry".
  Scenario: Second scenario starts fresh
    Given the context value is "World"
    When I add "Cherry" to the list
    Then the context value should be "World"
    And the list should contain 1 items
    And the only item in the context list should be "Cherry"
