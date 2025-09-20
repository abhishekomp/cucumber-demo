package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SanityTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    void testJUnitWorks() {
        System.out.println("JUnit 5 runs!");
        logger.info("Message from Logger - JUnit 5 runs!");
        assert true;
    }
}