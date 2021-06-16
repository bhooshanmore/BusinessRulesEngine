package com.business.rolesengine.service;

import com.business.rolesengine.common.RulesMessages;
import com.business.rolesengine.model.ProductDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.function.Function;

/**
 * This Dispatch pattern where we are adding all rules as many we required
 * and never gives cyclometric complexity or any sonar warning or issue
 */

@Component
public class ValidateBusinessRules {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateBusinessRules.class);
    private final LinkedHashMap<Function<ProductDetails, Boolean>, Function<ProductDetails, String>> dispatch = new LinkedHashMap<>();

    public ValidateBusinessRules() {
    }

    /**
     * @param productDetails Check access for product by type.
     * @return String
     */
    public String access(ProductDetails productDetails) {

        for (Function<ProductDetails, Boolean> predict : this.dispatch.keySet()) {
            if (predict.apply(productDetails)) {
                return this.dispatch.get(predict).apply(productDetails);
            }
        }
        LOGGER.error("Error om access() ");
        return "Payment is not found";
    }

    /**
     * Here we can add as many as rule you want
     * Load initial handlers.
     *
     * @return current object.
     */
    public ValidateBusinessRules init() {
        this.dispatch.put(
                productDetails -> productDetails.getType().equalsIgnoreCase("physical product"),
                productDetails -> RulesMessages.PACKING_SLIP_FOR_SHIPPING
        );
        this.dispatch.put(
                productDetails -> productDetails.getType().equalsIgnoreCase("book"),
                productDetails -> RulesMessages.DUPLICATE_PARKING_SLIP_FOR_THE_ROYALTY_DEPARTMENT
        );
        this.dispatch.put(
                productDetails -> productDetails.getType().equalsIgnoreCase("membership"),
                productDetails -> RulesMessages.ACTIVATE_THAT_MEMBERSHIP
        );
        this.dispatch.put(
                productDetails -> productDetails.getType().equalsIgnoreCase("upgrade membership"),
                productDetails -> RulesMessages.APPLY_THE_UPGRADE
        );
        this.dispatch.put(
                productDetails -> productDetails.getType().equalsIgnoreCase("membership") && productDetails.getType().equalsIgnoreCase("upgrade"),
                productDetails -> RulesMessages.ACTIVATION_AND_UPGRADE
        );
        this.dispatch.put(
                productDetails -> productDetails.getType().equalsIgnoreCase("video ") && productDetails.getName().equalsIgnoreCase("Learning to Ski"),
                productDetails -> RulesMessages.FIRST_AID
        );
        return this;
    }


}
