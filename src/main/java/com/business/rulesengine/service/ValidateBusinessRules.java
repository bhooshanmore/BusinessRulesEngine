package com.business.rulesengine.service;

import com.business.rulesengine.common.RulesMessages;
import com.business.rulesengine.model.PaymentDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.function.Function;

import static com.business.rulesengine.common.RulesMessages.PAYMENT_IS_NOT_FOUND;

/**
 * This Dispatch pattern where we are adding all rules as many we required
 * and never gives cyclometric complexity or any sonar warning or issue
 */

@Component
public class ValidateBusinessRules {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateBusinessRules.class);
    private final LinkedHashMap<Function<PaymentDetails, Boolean>, Function<PaymentDetails, String>> dispatch = new LinkedHashMap<>();

    public ValidateBusinessRules() {
        // Default constructor
    }

    /**
     * @param paymentDetails Check access for product by type.
     * @return String
     */
    public String access(PaymentDetails paymentDetails) {
        for (Function<PaymentDetails, Boolean> predict : this.dispatch.keySet()) {
            if (Boolean.TRUE.equals(predict.apply(paymentDetails))) {
                return this.dispatch.get(predict).apply(paymentDetails);
            }
        }
        return PAYMENT_IS_NOT_FOUND;
    }

    /**
     * Here we can add as many as rule you want
     * Load initial handlers.
     *
     * @return current object.
     */
    public ValidateBusinessRules init() {
        this.dispatch.put(
                paymentDetails -> paymentDetails.getType().trim().equalsIgnoreCase("membership") && paymentDetails.getName().equalsIgnoreCase("upgrade"),
                paymentDetails -> RulesMessages.ACTIVATION_AND_UPGRADE
        );
        this.dispatch.put(
                paymentDetails -> paymentDetails.getType().trim().equalsIgnoreCase("video") && paymentDetails.getName().equalsIgnoreCase("Learning to Ski"),
                paymentDetails -> RulesMessages.FIRST_AID
        );
        this.dispatch.put(
                paymentDetails -> paymentDetails.getType().trim().equalsIgnoreCase("physical product"),
                paymentDetails -> RulesMessages.PACKING_SLIP_FOR_SHIPPING
        );
        this.dispatch.put(
                paymentDetails -> paymentDetails.getType().trim().equalsIgnoreCase("book"),
                paymentDetails -> RulesMessages.DUPLICATE_PARKING_SLIP_FOR_THE_ROYALTY_DEPARTMENT
        );
        this.dispatch.put(
                paymentDetails -> paymentDetails.getType().trim().equalsIgnoreCase("membership"),
                paymentDetails -> RulesMessages.ACTIVATE_THAT_MEMBERSHIP
        );
        this.dispatch.put(
                paymentDetails -> paymentDetails.getType().trim().equalsIgnoreCase("upgrade membership"),
                paymentDetails -> RulesMessages.APPLY_THE_UPGRADE
        );
        return this;
    }
}
