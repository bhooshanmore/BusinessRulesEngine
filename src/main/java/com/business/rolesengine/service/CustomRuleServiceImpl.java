package com.business.rolesengine.service;

import com.business.rolesengine.exception.InvalidRequest;
import com.business.rolesengine.model.Order;
import com.business.rolesengine.model.ProductDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomRuleServiceImpl implements CustomRuleService {

    private final ValidateBusinessRules validateBusinessRules;

    public CustomRuleServiceImpl(ValidateBusinessRules validateBusinessRules) {
        this.validateBusinessRules = validateBusinessRules;
    }

    @Override
    public void checksRules(Order order) throws InvalidRequest {
        for (ProductDetails productDetails : order.getProductDetails()) {
            productDetails.setActions(Collections.singletonList(validateBusinessRules.init().access(productDetails)));
        }
    }
}
