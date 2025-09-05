package com.example.demo.stepdefs;

import com.example.demo.context.SharedContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerificationSteps {

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
}
