package com.example.demo;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
// Path relative to classpath root (src/test/resources):
@SelectClasspathResource("features")    // points to src/test/resources/features
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.demo.stepdefs, com.example.demo.context, com.example.demo.config")
//@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.demo.stepdefs")
//@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class RunCucumberTestSuite {}