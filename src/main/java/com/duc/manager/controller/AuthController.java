package com.duc.manager.controller;

import com.duc.manager.dto.request.LoginRequest;
import com.duc.manager.dto.response.LoginResponse;
import com.duc.manager.entity.Accounts;
import com.duc.manager.service.AccountService;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = accountService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(response);
    }

}
