# Cucumber Spring Context Demo

This project demonstrates how to use [Cucumber](https://cucumber.io/) with [Spring Test Context](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#testcontext-framework) and [JUnit 5](https://junit.org/junit5/) for behavior-driven development (BDD) in Java.

## Project Structure

- **Language:** Java 17
- **Build Tool:** Maven
- **Testing Frameworks:** JUnit 5, Cucumber
- **Dependency Injection:** Spring

### Key Directories

- `src/test/java/com/example/demo/`  
  Java test sources, including step definitions and configuration.
- `src/test/resources/features/`  
  Gherkin feature files.

## Running Tests

To run all tests (including Cucumber scenarios and JUnit tests):

```sh
mvn clean test
```

## Cucumber + JUnit 5 Integration

This project uses the `cucumber-junit-platform-engine` dependency, which allows Cucumber scenarios to be executed on the JUnit 5 platform. The entry point is the empty class annotated with `@Cucumber`:

```java
// src/test/java/com/example/demo/CucumberTestRunner.java
@Cucumber
public class CucumberTestRunner {}
```

No additional runner configuration is needed for JUnit 5.

## Spring Context Integration

Spring's test context is enabled for Cucumber scenarios using:

- `@CucumberContextConfiguration`
- `@ContextConfiguration(classes = {SpringTestConfig.class})`

This ensures that step definitions can use Spring's dependency injection.

## Specifying Feature File Locations

By default, Cucumber looks for feature files in `src/test/resources/<package>`.  
In this project, feature files are placed in `src/test/resources/features/`.

**To specify this custom location**, the Maven Surefire plugin is configured in `pom.xml`:

```xml
<systemPropertyVariables>
    <cucumber.features>src/test/resources/features</cucumber.features>
</systemPropertyVariables>
```

This tells Cucumber (via the JUnit 5 engine) to look for `.feature` files in the `features` directory.

**Note:**  
This approach works because:
- You are using JUnit 5 with the `cucumber-junit-platform-engine` dependency.
- The feature path is passed as a system property, which is supported by the JUnit 5 Cucumber engine.
- No need for a custom runner or `@CucumberOptions` (which is for JUnit 4).

## Adding New Feature Files

1. Place new `.feature` files in `src/test/resources/features/`.
2. Implement corresponding step definitions in `src/test/java/com/example/demo/stepdefs/`.

## Example Feature

```gherkin
Feature: Shared context resets between scenarios

  Scenario: First scenario sets value and list
    Given the context value is "Hello"
    When I add "Apple" to the list
    When I add "Banana" to the list
    Then the context value should be "Hello"
    And the list should contain 2 items
```

## Example Step Definition

```java
@Given("the context value is {string}")
public void theContextValueIs(String value) {
    sharedContext.setValue(value);
}
```

## Useful Maven Commands

- Run all tests:  
  `mvn clean test`
- Run only Cucumber scenarios:  
  `mvn clean test -Dtest=CucumberTestRunner`
- Run a specific scenario by name:  
  `mvn clean test -Dtest=CucumberTestRunner -Dcucumber.filter.name="First scenario sets value and list"`
- Run a specific scenario by tag:  
  `mvn clean test -Dtest=CucumberTestRunner -Dcucumber.filter.tags="@myTag"`
- Run a specific feature file:  
  `mvn clean test -Dtest=CucumberTestRunner -Dcucumber.features="src/test/resources/features/myFeature.feature"`
- Run with detailed output:  
  `mvn clean test -Dtest=CucumberTestRunner -Dcucumber.plugin=pretty,html:target/cucumber-report.html`
- Run JUnit tests only (Does this work?):  
  `mvn clean test -Dtest='!CucumberTestRunner'`
  (Tech note: This may not work and you may see error in Mac as "zsh: event not found: CucumberTestRunner".)
  To workaround, you can run:  
  `mvn clean test -Dtest=\!CucumberTestRunner` or `mvn clean test -Dtest='^CucumberTestRunner'` or `mvn clean test -Dtest='*' -DfailIfNoTests=false -DexcludeTests=CucumberTestRunner` or `mvn clean test -Dtest='*' -DfailIfNoTests=false -DexcludeTests='CucumberTestRunner'`
  `mvn clean test -Dtest='!CucumberTestRunner'`
- Run JUnit tests in a specific directory:  
  `mvn clean test -Dtest=com/example/demo/*`
- Run JUnit tests in a specific package:  
  `mvn clean test -Dtest=com.example.demo.*`
- Run a specific JUnit test class:  
  `mvn clean test -Dtest=MyJUnitTest`
- Run a specific JUnit test method:  
  `mvn clean test -Dtest=MyJUnitTest#myTestMethod`
- Generate JUnit reports:  
  `mvn surefire-report:report`
- Generate Cucumber reports:  
  `mvn verify`
- Clean project:  
  `mvn clean`

## Dependencies

- `io.cucumber:cucumber-java`
- `io.cucumber:cucumber-spring`
- `io.cucumber:cucumber-junit-platform-engine`
- `org.springframework:spring-context`
- `org.springframework:spring-test`
- `org.junit.jupiter:junit-jupiter`

## Notes

- Step definitions can use Spring's `@Autowired` for dependency injection.
- Each scenario gets a fresh Spring context if using `@ScenarioScope`.
- The surefire plugin configuration in `pom.xml` is essential for Cucumber to find your feature files when using JUnit 5.

---

For more details, see the source code and comments in the respective files.
```
