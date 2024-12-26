package com.duc.manager.controller;

import com.duc.manager.dto.request.CustomerCreationRequest;
import com.duc.manager.entity.Customers;
import com.duc.manager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/createCustomer")
    Customers createCustomer(@RequestBody CustomerCreationRequest request){
        return customerService.createCustomer(request);
    }

    @GetMapping("/getCustomer")
    List<Customers> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("getCustomer/{Id}")
    Customers getCustomer(@PathVariable("Id") int Id){
        return customerService.getCustomer(Id);
    }

    @GetMapping("getNumberOfCustomer")
    public ResponseEntity<Map<String,Long>> getNumberOfCustomer(){
        long result= customerService.getNumberOfCustomer();
        Map<String, Long> response = new HashMap<>();
        response.put("NumberOfCustomer", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("classifyCustomersByTotalSpent")
    public List<Map<String, Object>> classifyCustomersByTotalSpent(){
        return customerService.classifyCustomersByTotalSpent();
    }


}

