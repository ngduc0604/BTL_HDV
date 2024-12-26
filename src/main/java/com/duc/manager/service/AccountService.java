package com.duc.manager.service;

import com.duc.manager.dto.response.LoginResponse;
import com.duc.manager.entity.Accounts;
import com.duc.manager.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public LoginResponse login(String username, String password) {
        Accounts account = accountRepository.findByUsername(username);
        if (account==null) {
            LoginResponse response = new LoginResponse();
            response.setMessage("No exist account");
            return response;
        }

        if (!password.equals(account.getPassword())) {
            LoginResponse response = new LoginResponse();
            response.setMessage("Password incorrect");
            return response;
        }

        LoginResponse response = new LoginResponse();
        response.setMessage("Login successful");
        response.setRole(account.getRole());
        response.setCustomers(account.getCustomer());
        return response;
    }
}
