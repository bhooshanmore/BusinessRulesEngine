package com.business.rolesengine.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String requestId;
    private List<ProductDetails> productDetails = new ArrayList<>();

    public List<ProductDetails> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
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
                ", productDetails=" + productDetails +
                '}';
    }
}
