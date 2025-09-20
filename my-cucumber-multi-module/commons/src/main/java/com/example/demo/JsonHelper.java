package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonHelper {
    private static final Logger log = LoggerFactory.getLogger(JsonHelper.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String mapToJson(Object obj) {
        try {
            String json = objectMapper.writeValueAsString(obj);
            log.info("Serialized object to JSON: {}", json);
            return json;
        } catch (Exception e) {
            log.error("Serialization failed", e);
            return null;
        }
    }
}