Feature: Example demonstrates usage of cucumber and junit-platform properties

  @Smoke
  Scenario: A smoke scenario
    Given a "smoke" scenario
    Then the scenario was "smoke"

  @Other
  Scenario: A non-smoke scenario
    Given a "non-smoke" scenario
    Then the scenario was "non-smoke"
