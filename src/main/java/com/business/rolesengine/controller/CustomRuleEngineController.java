package com.business.rolesengine.controller;

import com.business.rolesengine.exception.InvalidRequest;
import com.business.rolesengine.model.Order;
import com.business.rolesengine.service.CustomRuleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("${api.baseUrl}")
public class CustomRuleEngineController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRuleEngineController.class);
    private final CustomRuleServiceImpl customRuleService;

    public CustomRuleEngineController(CustomRuleServiceImpl customRuleService) {
        this.customRuleService = customRuleService;
    }

    @PostMapping(value = "validator", produces = "application/json")
    public ResponseEntity<?> validateBusinessRules(@RequestBody Order orderDetails) {
        try {
            orderDetails.setRequestId(UUID.randomUUID().toString());
            customRuleService.checksRules(orderDetails);

            return new ResponseEntity<>(orderDetails, HttpStatus.OK);
        } catch (InvalidRequest e) {
            LOGGER.error("Error in validateBusinessRules() ");
            return new ResponseEntity<>("Invalid Request :" + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
