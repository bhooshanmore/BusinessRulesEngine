package com.demo.customrolesengine.service;

import com.demo.customrolesengine.comon.RulesMessages;
import com.demo.customrolesengine.model.ProductDetails;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;

/**
 * Dispatch pattern for diapason key.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class DispatchDiapason {
    /**
     * Dispatch.
     */
    private final LinkedHashMap<Function<ProductDetails, Boolean>, Function<ProductDetails, String >> dispatch = new LinkedHashMap<>();

    /**
     * Load initial handlers.
     * @return current object.
     */
    public DispatchDiapason init() {
        this.dispatch.put(
                productDetails -> productDetails.getType().equalsIgnoreCase("physical product"),
                productDetails -> RulesMessages.PACKING_SLIP_FOR_SHIPPING + RulesMessages.COMMISSION_PAYMENT_TO_THE_AGENT //RulesMessages.COMMISSION_PAYMENT_TO_THE_AGENT
        );
        this.dispatch.put(
                productDetails -> productDetails.getType().equalsIgnoreCase("book"),
                productDetails -> RulesMessages.DUPLICATE_PARKING_SLIP_FOR_THE_ROYALTY_DEPARTMENT + RulesMessages.COMMISSION_PAYMENT_TO_THE_AGENT
        );
        return this;
    }

    /**
     * Load handler and predict.
     * @param predict Predict.
     * @param handle Handle.
     */
    public void load(Function<ProductDetails, Boolean> predict, Function<ProductDetails, String > handle) {
        this.dispatch.put(predict, handle);
    }

    /**
     * Chech access for person by age.
     * @param person Person
     * @return true if access are allowed
     */
    public String access(ProductDetails person) {
        for (Function<ProductDetails, Boolean> predict : this.dispatch.keySet()) {
            if (predict.apply(person)) {
                return this.dispatch.get(predict).apply(person);
            }
        }
        throw new IllegalStateException("Could not found a handle for person");
    }
}
