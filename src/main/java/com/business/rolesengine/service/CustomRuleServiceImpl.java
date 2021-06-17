package com.business.rolesengine.service;

import com.business.rolesengine.exception.InvalidRequest;
import com.business.rolesengine.model.Order;
import com.business.rolesengine.model.PaymentDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomRuleServiceImpl implements CustomRuleService {

    private final ValidateBusinessRules validateBusinessRules;

    public CustomRuleServiceImpl(ValidateBusinessRules validateBusinessRules) {
        this.validateBusinessRules = validateBusinessRules;
    }

    @Override
    public Order checksRules(Order order) throws InvalidRequest {
        for (PaymentDetails paymentDetails : order.getPaymentDetails()) {
            paymentDetails.setRule(Collections.singletonList(validateBusinessRules.init().access(paymentDetails)));
        }
        return order;
    }
}
