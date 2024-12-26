package com.duc.manager.controller;

import com.duc.manager.dto.request.OrderDetailCreationRequest;
import com.duc.manager.entity.OrderDetails;
import com.duc.manager.repository.OrderDetailsRepository;
import com.duc.manager.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/createOrderDetail")
    public OrderDetails createOrderDetails(@RequestBody OrderDetailCreationRequest request){
        return orderDetailService.createOrderDetails(request);
    }


}
