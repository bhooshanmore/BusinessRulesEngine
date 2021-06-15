package com.demo.customrolesengine.service;

import com.demo.customrolesengine.exception.InvalidRequest;
import com.demo.customrolesengine.model.Order;

public interface CustomRuleService {
    public void checksRules(Order order) throws InvalidRequest;
}
