package com.duc.manager.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.duc.manager.entity.Carts;
import com.duc.manager.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/getCartByCusId/{customer_id}")
    Carts find(@PathVariable("customer_id") int customer_id){
        return cartService.findCartByCustomerId(customer_id);
    }



}
