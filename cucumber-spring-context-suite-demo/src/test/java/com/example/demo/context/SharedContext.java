package com.example.demo.context;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@ScenarioScope
public class SharedContext {

    private final String instanceId = UUID.randomUUID().toString(); // unique per scenario
    private String value;
    private final List<String> items = new ArrayList<>();

    public String getInstanceId() {
        return instanceId;
    }

    public String getValue() {
        System.out.println("[LOG][SharedContext][" + instanceId + "] Current value in context is " + value);
        return value;
    }

    public void setValue(String value) {
        System.out.println("[LOG][SharedContext][" + instanceId + "] Set " + value + " in the Context");
        this.value = value;
    }

    public List<String> getItems() {
        System.out.println("[LOG][SharedContext][" + instanceId + "] Current list in context is " + items);
        return items;
    }

    public void addItem(String item) {
        System.out.println("[LOG][SharedContext][" + instanceId + "] Adding " + item + " to the list");
        items.add(item);
    }
}
