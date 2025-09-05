package com.example.demo.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DemoStepDefs {
    private String scenarioType;

    @Given("a {string} scenario")
    public void aScenario(String arg0) {
        this.scenarioType = arg0;
        //System.out.println("[Step] Given a " + arg0 + " scenario");
        System.out.printf("[LOG][Step] Received scenario type : %s\n", arg0);
        assertNotNull(arg0);
    }


    @Then("the scenario was {string}")
    public void theScenarioWas(String expected) {
        System.out.printf("[LOG][Step] Scenario was verified as : %s\n", expected);
        assertEquals(expected, this.scenarioType);
        //System.out.println("[Step] Then the scenario was " + expected);
    }
}