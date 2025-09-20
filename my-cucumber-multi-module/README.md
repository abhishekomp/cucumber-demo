# Cucumber Spring Context Multi Module Demo (JUnit 5 with @Suite)

This project demonstrates how to use [Cucumber](https://cucumber.io/) with [Spring Test Context](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html) and [JUnit 5](https://junit.org/junit5/) (Jupiter) using the modern `@Suite` runner from the JUnit Platform Suite API.

## Project Structure

- **Step Definitions:** Java classes in `src/test/java/com/example/demo/stepdefs/` using Cucumber and Spring.
- **Spring Test Configuration:** In `src/test/java/com/example/demo/config/`.
- **Feature Files:** Gherkin scenarios in `src/test/resources/features/`.
- **Test Suite Runner:** Uses JUnit 5's `@Suite` in `RunCucumberTestSuite.java`.

## Key Technologies

- **Java 17**
- **Maven**
- **Cucumber 7.x**
- **Spring 5.x**
- **JUnit 5 (Jupiter)**
- **JUnit Platform Suite API** (for `@Suite` runner)

## Running the Tests

To run all Cucumber scenarios and JUnit tests:

```sh
mvn test
```

This will execute all tests matching the patterns in the Maven Surefire Plugin configuration.

## How Feature Files Are Specified

- Place your `.feature` files under `src/test/resources/features/`.
- The test suite runner (`RunCucumberTestSuite.java`) uses:

  ```java
  @SelectClasspathResource("features")
  ```

  This tells Cucumber to look for features in the `features` directory on the test classpath.

- You do **not** need to specify the feature path in the Maven Surefire Plugin or as a JVM property, because the runner and Cucumber JUnit Platform Engine handle this via annotations.

## JUnit 5 and @Suite

- This project uses the JUnit Platform Suite API (`org.junit.platform.suite.api`) to run Cucumber scenarios.
- The `@Suite` annotation (and related annotations like `@IncludeEngines`, `@SelectClasspathResource`, `@ConfigurationParameter`) are available via the `junit-platform-suite` dependency:

  ```xml
  <dependency>
      <groupId>org.junit.platform</groupId>
      <artifactId>junit-platform-suite</artifactId>
      <version>1.10.1</version>
      <scope>test</scope>
  </dependency>
  ```

- The `@Suite` approach is the modern, recommended way to run Cucumber with JUnit 5, replacing the older JUnit 4 runners.

## Example Test Suite Runner

```java
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // Looks for feature files in src/test/resources/features
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.demo.stepdefs, com.example.demo.context, com.example.demo.config")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class RunCucumberTestSuite {}
```

- `@IncludeEngines("cucumber")`: Runs Cucumber scenarios.
- `@SelectClasspathResource("features")`: Looks for feature files in `src/test/resources/features`.
- `@ConfigurationParameter`: Sets Cucumber options (glue packages, plugins, etc).

## Spring Integration

- Step definition classes can use `@Autowired` to inject Spring beans.
- The `SharedContext` bean is annotated with `@Component` and `@ScenarioScope` for scenario isolation.
- Spring context is configured via `CucumberSpringConfiguration` and `SpringTestConfig`.

## Maven Surefire Plugin

- Configured to run all test classes matching `*Test.java`, `*Tests.java`, `*TestCase.java`, `*TestRunner.java`, `CucumberRunner.java`, and `RunCucumberTestSuite.java`.
- No need to specify feature paths in the plugin configuration due to the use of the JUnit Platform Suite runner.

## Adding New Features

1. Add new `.feature` files to `src/test/resources/features/`.
2. Implement step definitions in `src/test/java/com/example/demo/stepdefs/`.
3. Glue code is automatically picked up if it is in the specified packages.

## Dependencies

All required dependencies are specified in `pom.xml` under the `<dependencies>` section.

## Summary

- Uses JUnit 5's `@Suite` runner for Cucumber.
- Feature files are discovered via classpath resource selection.
- Spring beans and context are fully supported.
- No need for legacy JUnit 4 runners or manual feature path configuration.

---

For more details, see the source files and comments in the codebase.
```