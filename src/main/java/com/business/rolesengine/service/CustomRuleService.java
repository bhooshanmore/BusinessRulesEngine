package com.business.rolesengine.service;

import com.business.rolesengine.exception.InvalidRequest;
import com.business.rolesengine.model.Order;

public interface CustomRuleService {
    Order checksRules(Order order) throws InvalidRequest;
}
