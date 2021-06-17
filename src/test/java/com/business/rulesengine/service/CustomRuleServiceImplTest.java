package com.business.rulesengine.service;

import com.business.rulesengine.exception.InvalidRequest;
import com.business.rulesengine.model.Order;
import com.business.rulesengine.model.PaymentDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.UUID;

import static com.business.rulesengine.common.RulesMessages.*;

@RunWith(SpringRunner.class)
public class CustomRuleServiceImplTest {

    private ValidateBusinessRules validateBusinessRules = new ValidateBusinessRules();
    private CustomRuleServiceImpl customRuleService;

    @Before
    public void setUp() {
        customRuleService = new CustomRuleServiceImpl(validateBusinessRules);

    }


    @Test
    public void checksRules_verify_physical_product_Test() throws InvalidRequest {
        Order physical_product = dummyRequest("physical product", "");
        Order order = customRuleService.checksRules(physical_product);
        Assert.assertEquals(PACKING_SLIP_FOR_SHIPPING, order.getPaymentDetails().get(0).getRule().get(0));
    }


    @Test
    public void checksRules_verify_book_Test() throws InvalidRequest {
        Order physical_product = dummyRequest("book", "");
        Order order = customRuleService.checksRules(physical_product);
        Assert.assertEquals(DUPLICATE_PARKING_SLIP_FOR_THE_ROYALTY_DEPARTMENT, order.getPaymentDetails().get(0).getRule().get(0));
    }


    @Test
    public void checksRules_verify_membership_Test() throws InvalidRequest {
        Order physical_product = dummyRequest("membership", "");
        Order order = customRuleService.checksRules(physical_product);
        Assert.assertEquals(ACTIVATE_THAT_MEMBERSHIP, order.getPaymentDetails().get(0).getRule().get(0));
    }

    @Test
    public void checksRules_verify_upgrade_membership_Test() throws InvalidRequest {
        Order physical_product = dummyRequest("upgrade membership", "");
        Order order = customRuleService.checksRules(physical_product);
        Assert.assertEquals(APPLY_THE_UPGRADE, order.getPaymentDetails().get(0).getRule().get(0));
    }

    @Test
    public void checksRules_verify_upgrade_And_membership_Test() throws InvalidRequest {
        Order physical_product = dummyRequest("membership", "upgrade");

        Order order = customRuleService.checksRules(physical_product);
        Assert.assertEquals(ACTIVATION_AND_UPGRADE, order.getPaymentDetails().get(0).getRule().get(0));
    }


    @Test
    public void checksRules_verify_video_And_LearningToSki_Test() throws InvalidRequest {
        Order physical_product = dummyRequest("video", "Learning to Ski");

        Order order = customRuleService.checksRules(physical_product);
        Assert.assertEquals(FIRST_AID, order.getPaymentDetails().get(0).getRule().get(0));
    }

    @Test
    public void checksRules_verify_PAYMENT_IS_NOT_FOUND() throws InvalidRequest {
        Order physical_product = dummyRequest("noPayment", "");

        Order order = customRuleService.checksRules(physical_product);
        Assert.assertEquals(PAYMENT_IS_NOT_FOUND, order.getPaymentDetails().get(0).getRule().get(0));
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
