package com.example.demo;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // runs features under src/test/resources/features
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty")
public class CucumberTestSuite {}