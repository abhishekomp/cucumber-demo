package com.example.demo.stepdefs;

import com.example.demo.context.SharedContext;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerificationSteps {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private SharedContext sharedContext;

    @Then("the context value should be {string}")
    public void theContextValueShouldBe(String expected) {
        //System.out.printf("[LOG][SharedContext] Context value is %s in the Context instance %d%n", sharedContext.getValue(), System.identityHashCode(sharedContext));
        assertEquals(expected, sharedContext.getValue());
    }

    @Then("the list should contain {int} items")
    public void theListShouldContainItems(Integer expectedSize) {
        //System.out.printf("[LOG][SharedContext] Context list size is %d in the Context instance %d%n", sharedContext.getItems().size(), System.identityHashCode(sharedContext));
        assertEquals(expectedSize, sharedContext.getItems().size());
    }

    @And("the only item in the context list should be {string}")
    public void theOnlyItemInTheContextListShouldBe(String arg0) {
        if (sharedContext.getItems().size() != 1 || !sharedContext.getItems().get(0).equals(arg0)) {
            throw new AssertionError("List does not contain the expectedList single item: " + arg0);
        }
        // Assert the content of the list
        List<String> expectedList = List.of(arg0);
        List<String> actualList = sharedContext.getItems();
        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList, sharedContext.getItems());
        assertTrue(actualList.containsAll(expectedList) && expectedList.containsAll(actualList));
        //System.out.printf("[LOG][VerificationSteps] Verified that the list in Context instance with uuid %s contains only: %s%n", sharedContext.getInstanceId(), expectedList);
        System.out.printf("[LOG][VerificationSteps][%s] Verified that the list in Context instance contains only: %s%n", sharedContext.getInstanceId(), expectedList);
    }

/*    @Given("I print all Cucumber properties")
    public void iPrintAllCucumberProperties() {
        System.out.println("Cucumber Properties:");
        System.getProperties().forEach((key, value) -> {
            if (key.toString().toLowerCase().contains("cucumber")) {
                System.out.printf("%s: %s%n", key, value);
            }
        });
    }*/

    @Given("I print all Cucumber properties")
    public void iPrintAllCucumberProperties() {
        System.out.println("Cucumber properties:");
        System.getProperties().forEach((k, v) -> {
            if (k.toString().startsWith("cucumber.")) System.out.println(k + "=" + v);
        });
    }

    @Then("I should see the properties printed in the console")
    public void iShouldSeeThePropertiesPrintedInTheConsole() {
        // This step is just a placeholder to indicate that the properties should be visible in the console output.
        // No action needed here.
        System.out.println("Check the console output for printed Cucumber properties.");
    }

/*    @Then("the Cucumber properties should be loaded correctly")
    public void theCucumberPropertiesShouldBeLoadedCorrectly() throws Exception {
        Properties expectedProps = new Properties();
        expectedProps.setProperty("cucumber.glue", "com.example.demo.stepdefs, com.example.demo.config");
        expectedProps.setProperty("cucumber.features", "classpath:features");
        expectedProps.setProperty("cucumber.plugin", "pretty");
        //expectedProps.setProperty("cucumber.filter.tags", "@onlyfoo");
        expectedProps.setProperty("cucumber.publish.quiet", "true");
        expectedProps.setProperty("cucumber.color.enabled", "true");

        Properties loadedProps = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("cucumber.properties")) {
            if (in != null) {
                loadedProps.load(in);
            } else {
                throw new AssertionError("cucumber.properties file not found.");
            }
        }

        for (String key : expectedProps.stringPropertyNames()) {
            assertEquals(expectedProps.getProperty(key), loadedProps.getProperty(key), "Property mismatch for key: " + key);
        }

        System.out.println("All Cucumber properties are loaded correctly.");
    }*/

    @Then("I should see the expected Cucumber properties")
    public void iShouldSeeTheExpectedCucumberProperties() {
        // This step is just a placeholder to indicate that the properties should be verified.
        // Actual verification is done in the previous step.
        System.out.println("Cucumber properties verification is done in the previous step.");
    }
}
