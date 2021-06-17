package com.business.rulesengine.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String requestId;
    private List<PaymentDetails> paymentDetails = new ArrayList<>();

    public List<PaymentDetails> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(List<PaymentDetails> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "requestId='" + requestId + '\'' +
                ", PaymentDetails=" + paymentDetails +
                '}';
    }
}
