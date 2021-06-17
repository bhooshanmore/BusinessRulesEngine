package com.business.rulesengine.service;

import com.business.rulesengine.exception.InvalidRequest;
import com.business.rulesengine.model.Order;
import com.business.rulesengine.model.PaymentDetails;
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
