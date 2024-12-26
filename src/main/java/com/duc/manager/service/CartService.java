package com.duc.manager.service;

import com.duc.manager.entity.Carts;
import com.duc.manager.repository.CartRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    @Autowired
    CartRepositoy cartRepositoy;

    public Carts findCartByCustomerId(int customerId){
        return cartRepositoy.findCartByCustomerId(customerId);
    }
}
