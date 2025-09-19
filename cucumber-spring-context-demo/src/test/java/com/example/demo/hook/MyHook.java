package com.example.demo.hook;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MyHook class
 *
 * @author : kjss920
 * @since : 2025-09-19, Friday
 **/
public class MyHook {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        logger.info("[HOOK][HOOK] Before step for scenario: {}", scenario.getName());
        //System.out.println("[HOOK][HOOK][HOOK][HOOK] Before each step, scenario on line: " + scenario.getLine());
    }

}
