package com.example.demo.context;

import io.cucumber.spring.ScenarioScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@ScenarioScope
public class SharedContext {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String instanceId = UUID.randomUUID().toString(); // unique per scenario
    private String value;
    private final List<String> items = new ArrayList<>();

    public String getInstanceId() {
        return instanceId;
    }

    public String getValue() {
        logger.info("[LOG][SharedContext][{}] Current value in context is {}", instanceId, value);
        //System.out.println("[LOG][SharedContext][" + instanceId + "] Current value in context is " + value);
        return value;
    }

    public void setValue(String value) {
        logger.info("[LOG][SharedContext][{}] Set {} in the Context", instanceId, value);
        //System.out.println("[LOG][SharedContext][" + instanceId + "] Set " + value + " in the Context");
        this.value = value;
    }

    public List<String> getItems() {
        logger.info("[LOG][SharedContext][{}] Current list in context is {}", instanceId, items);
        //System.out.println("[LOG][SharedContext][" + instanceId + "] Current list in context is " + items);
        return items;
    }

    public void addItem(String item) {
        logger.info("[LOG][SharedContext][{}] Adding {} to the list", instanceId, item);
        //System.out.println("[LOG][SharedContext][" + instanceId + "] Adding " + item + " to the list");
        items.add(item);
    }
}
