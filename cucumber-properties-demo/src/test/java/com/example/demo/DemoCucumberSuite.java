package com.example.demo;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.*;

// 🏃 This class tells JUnit 5 Platform to run all Cucumber features in src/test/resources/features
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // loads features under resources/features
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.demo.stepdefs")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, summary")
//@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@Smoke")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@Other")
public class DemoCucumberSuite {}