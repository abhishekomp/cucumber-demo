# DataTable Demo

This project demonstrates how to use Cucumber's `DataTable` feature in Java for various data-driven testing scenarios. It covers different ways to map Gherkin tables to Java collections and objects, including:

- Lists (`asList`)
- Maps (`asMap`)
- List of Maps (`asMaps`)
- Mapping to POJOs
- Scenario Outlines with DataTables

## Project Structure

- `src/test/resources/features/datatable.feature`: Example feature file with scenarios using DataTables.
- `src/test/java/com/example/stepdefs/DataTableStepDefs.java`: Step definitions showing how to process DataTables in different ways.
- `src/test/java/com/example/model/Book.java`: POJO used for mapping book details from DataTables.
- `src/test/java/com/example/runner/CucumberRunner.java`: JUnit 5 runner for executing Cucumber scenarios.
- `pom.xml`: Maven configuration with dependencies for Cucumber and JUnit 5.

## Prerequisites

- Java 17+
- Maven 3.6+

## Running the Tests

To run all Cucumber scenarios:

```sh
mvn clean test
```

You should see output from the step definitions in the console, including logs for each DataTable processing example.

## Key Concepts Demonstrated

- **DataTable.asList**: Convert a single-column table to a `List<String>`.
- **DataTable.asMap**: Convert a two-column table to a `Map<String, String>`.
- **DataTable.asMaps**: Convert a table with headers to a `List<Map<String, String>>`.
- **DataTable.asList(POJO.class)**: Map table rows to a list of POJOs.
- **Filtering DataTable rows**: How to filter out empty rows when processing scenario outlines.
- **Scenario Outlines**: Using DataTables with parameterized scenarios.

## Customizing

You can add more scenarios to `datatable.feature` or extend the step definitions to cover additional DataTable use cases.

## License

MIT License (add your own license if needed)
```
