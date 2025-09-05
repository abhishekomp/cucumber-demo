package com.example.demo.stepdefs;

import com.example.demo.context.SharedContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Step definitions that interact with the SharedContext.
 * Demonstrates setting and verifying values in the shared context.
 * Get rid of the message: Autowired members must be defined in valid Spring bean (@Component|@Service|...)
 * by adding the annotation @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") on top of the class.
 * This will hide the warning in IntelliJ IDEA, but it does not fix the underlying Spring bean configuration. For proper Spring injection, annotate your step class with @Component.
 */
//@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class ContextSteps {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private SharedContext sharedContext;

    @Given("the context value is {string}")
    public void theContextValueIs(String value) {
        //System.out.printf("[LOG][SharedContext] Current value in context is %s%n", sharedContext.getValue());
        //System.out.printf("[LOG][SharedContext] Set %s in the Context instance %d%n", value, System.identityHashCode(sharedContext));
        sharedContext.setValue(value);
    }

    @When("I add {string} to the list")
    public void iAddToTheList(String item) {
        //System.out.printf("[LOG][SharedContext] Current list in context is %s%n", sharedContext.getItems());
        //System.out.printf("[LOG][SharedContext] Adding %s to the list in the Context instance %d%n", item, System.identityHashCode(sharedContext));
        sharedContext.addItem(item);
    }


}
