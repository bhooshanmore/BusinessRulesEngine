package com.demo.customrolesengine.model;

import com.demo.customrolesengine.comon.ServiceEnum;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private ServiceEnum serviceId;
    private List<ProductDetails> productDetails = new ArrayList<>();

    public ServiceEnum getServiceId() {
        return serviceId;
    }

    public void setServiceId(ServiceEnum serviceId) {
        this.serviceId = serviceId;
    }

    public List<ProductDetails> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "serviceId=" + serviceId +
                ", productDetails=" + productDetails +
                '}';
    }
}
