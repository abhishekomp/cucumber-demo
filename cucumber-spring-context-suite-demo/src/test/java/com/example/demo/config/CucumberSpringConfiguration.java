package com.example.demo.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = {SpringTestConfig.class})
public class CucumberSpringConfiguration {
    // This tells Cucumber to use Spring’s test context
}
