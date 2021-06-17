# Business Custom Rule Engine


## Introduction:

This is demo project for custom Business rule engine using spring boot framework without using Ready available Drool engine

### Requirements:
1. JDK 1.8 
2. Maven 3.0 
3. Intellij/Eclipse IDE

### REST End point details:
http://localhost:8081/businessruleengine/validator

Currently, running on 8081 port if want to change port number than please change application.property file under resources folder

### Request payload :
```json
{
  "paymentDetails": [
    {
      "name": "test book",
      "type": "book"
    },
    {
      "name": "test1",
      "type": "physical product"
    },
    {
      "name": "test2",
      "type": "physical product2"
    }
  ]
}
```

### Expected Response
```json
{
   "requestId": "01d94916-db54-4914-a4f1-af075991e060",
   "paymentDetails":    [
            {
         "name": "test book",
         "type": "book",
         "rule": ["create duplicate parking slip for the royalty department"]
      },
            {
         "name": "test1",
         "type": "physical product",
         "rule": ["Generate a packing slip for shipping"]
      },
            {
         "name": "test2",
         "type": "physical product2",
         "rule": ["Payment is not found"]
      }
   ]
}
```
