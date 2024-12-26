package com.duc.manager.controller;

import com.duc.manager.dto.request.AccountCreationRequest;
import com.duc.manager.dto.request.LoginRequest;
import com.duc.manager.dto.response.CreateAccResponse;
import com.duc.manager.dto.response.LoginResponse;
import com.duc.manager.entity.Accounts;
import com.duc.manager.entity.Carts;
import com.duc.manager.entity.Customers;
import com.duc.manager.repository.CartRepositoy;
import com.duc.manager.service.AccountService;
import com.duc.manager.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CartService cartService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = accountService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody AccountCreationRequest request) {
        try {
            Accounts account = accountService.createAccount(
                    request.getPassword(),
                    request.getUsername(),
                    request.getAddress(),
                    request.getEmail(),
                    request.getName(),
                    request.getPhone()
            );
            return ResponseEntity.ok(account);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }

}



