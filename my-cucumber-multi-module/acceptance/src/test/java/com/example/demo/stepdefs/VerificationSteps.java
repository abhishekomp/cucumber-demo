package com.example.demo.stepdefs;

import com.example.demo.JsonHelper;
import com.example.demo.context.SharedContext;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerificationSteps {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @Given("I hava a map with key {string} and value {string}")
    public void iHavaAMapWithKeyAndValue(String key, String value) {
        // Store the map in the shared context
        sharedContext.storeMap(key, value);
        logger.info("[LOG][VerificationSteps] Stored map with key: {} and value: {}", key, value);
        // Store the map in the shared context as a string representation
        //sharedContext.setValue(map.toString());
    }

    @When("I serialize the map to JSON")
    public void iSerializeTheMapToJSON() {
        // Ask JsonHelper in commons module to serialize the map to JSON and store the result in a string in the shared context
        String json = JsonHelper.mapToJson(sharedContext.getMap());
        // Store the JSON string in the shared context
        sharedContext.setValue(json);
        logger.info("[LOG][VerificationSteps] Serialized map to JSON: {}", json);
    }

    @Then("the JSON should be {string}")
    public void theJSONShouldBe(String jsonString) {
        // Fetch the JSON string from the shared context
        String actualJson = sharedContext.getValue();
        // Compare the actual JSON with the expected JSON
        assertEquals(jsonString, actualJson);
        logger.info("[LOG][VerificationSteps] Verified JSON string: {}", actualJson);
    }
}
