package com.business.rulesengine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class PaymentDetails {

    private String name;
    private String type;
    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private List<String> rule = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getRule() {
        return rule;
    }

    public void setRule(List<String> rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", rule=" + rule +
                '}';
    }
}
