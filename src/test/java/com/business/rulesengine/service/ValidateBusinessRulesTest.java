package com.business.rulesengine.service;


import com.business.rulesengine.common.RulesMessages;
import com.business.rulesengine.model.Order;
import com.business.rulesengine.model.PaymentDetails;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ValidateBusinessRulesTest {

    @Test
    public void whenPaymentForPhysicalProduct() {
        Order physical_product = dummyRequest("physical product", "");
        assertThat(
                new ValidateBusinessRules().init().access(physical_product.getPaymentDetails().get(0)),
                is(RulesMessages.PACKING_SLIP_FOR_SHIPPING)
        );
    }


    @Test
    public void whenPaymentForBook() {
        Order physical_product = dummyRequest("book", "");
        assertThat(
                new ValidateBusinessRules().init().access(physical_product.getPaymentDetails().get(0)),
                is(RulesMessages.DUPLICATE_PARKING_SLIP_FOR_THE_ROYALTY_DEPARTMENT)
        );
    }

    @Test
    public void whenPaymentForMembership() {
        Order physical_product = dummyRequest("membership", "");
        assertThat(
                new ValidateBusinessRules().init().access(physical_product.getPaymentDetails().get(0)),
                is(RulesMessages.ACTIVATE_THAT_MEMBERSHIP)
        );
    }


    @Test
    public void whenPaymentForUpgradeMembership() {
        Order physical_product = dummyRequest("upgrade membership", "");
        assertThat(
                new ValidateBusinessRules().init().access(physical_product.getPaymentDetails().get(0)),
                is(RulesMessages.APPLY_THE_UPGRADE)
        );
    }


    @Test
    public void whenPaymentForVideoAndTypeIsLearningToSki() {
        Order physical_product = dummyRequest("video", "Learning to Ski");
        assertThat(
                new ValidateBusinessRules().init().access(physical_product.getPaymentDetails().get(0)),
                is(RulesMessages.FIRST_AID)
        );
    }


    private Order dummyRequest(String type, String name) {
        String uuid = UUID.randomUUID().toString();
        Order orderDetails = new Order();
        orderDetails.setRequestId(uuid);
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setName("xyz");
        paymentDetails.setType(type);
        if (name != null && !name.equalsIgnoreCase("")) {
            paymentDetails.setName(name);
        }
        orderDetails.setPaymentDetails(Arrays.asList(paymentDetails));
        return orderDetails;
    }

}
