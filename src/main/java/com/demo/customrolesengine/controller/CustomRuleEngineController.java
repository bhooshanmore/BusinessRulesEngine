package com.demo.customrolesengine.controller;

import com.demo.customrolesengine.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.baseUrl}")
public class CustomRuleEngineController {

    @PostMapping(value = "validate-rule" ,produces = "application/json")
    public ResponseEntity<?> validateRules(@RequestBody Order orderDetails) {
        System.out.println("orderDetails >> "+orderDetails);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
}
