package com.duc.manager.controller;

import com.duc.manager.dto.request.CartDetailCreation;
import com.duc.manager.dto.request.CartDetailsRequest;
import com.duc.manager.dto.request.ProductUpdateRequest;
import com.duc.manager.entity.CartDetails;
import com.duc.manager.entity.Products;
import com.duc.manager.service.CartDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CartDetailsController {
    @Autowired
    CartDetailsService cartDetailsService;

    @GetMapping("/getCartId/{customerId}")
    public List<Map<String,Object>> findCartDetailsByCustomerId(@PathVariable int customerId){
        return cartDetailsService.findCartDetailsByCustomerId(customerId);
    }

    @PutMapping("/updateCart/{Id}")
    CartDetails updateAmount(@RequestBody CartDetailsRequest request, @PathVariable int Id){
        return cartDetailsService.updateQuantity(Id,request);
    }

    @PostMapping("addToCart")
    public CartDetails addToCart(@RequestBody CartDetailCreation request){
        return cartDetailsService.createCartDetail(request);
    }

    @DeleteMapping("deleleCartById/{id}")
    public void delete(@PathVariable int id){
        cartDetailsService.deleteCartDetails(id);
    }

}
