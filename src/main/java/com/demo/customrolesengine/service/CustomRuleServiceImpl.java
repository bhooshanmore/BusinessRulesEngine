package com.demo.customrolesengine.service;

import com.demo.customrolesengine.exception.InvalidRequest;
import com.demo.customrolesengine.model.Order;
import com.demo.customrolesengine.model.ProductDetails;
import org.springframework.stereotype.Service;

@Service
public class CustomRuleServiceImpl implements CustomRuleService {

    DispatchDiapason dispatchDiapason = new DispatchDiapason();

    @Override
    public void checksRules(Order order) throws InvalidRequest {
        for (ProductDetails productDetails : order.getProductDetails()) {
            dispatchDiapason.access(productDetails);
        }
    }
}
