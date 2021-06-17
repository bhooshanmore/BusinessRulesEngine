package com.business.rulesengine.service;

import com.business.rulesengine.exception.InvalidRequest;
import com.business.rulesengine.model.Order;

public interface CustomRuleService {
    Order checksRules(Order order) throws InvalidRequest;
}
