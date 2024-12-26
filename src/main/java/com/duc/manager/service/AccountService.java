package com.duc.manager.service;

import com.duc.manager.dto.request.AccountCreationRequest;
import com.duc.manager.dto.response.CreateAccResponse;
import com.duc.manager.dto.response.LoginResponse;
import com.duc.manager.dto.response.ResisterResponse;
import com.duc.manager.entity.Accounts;
import com.duc.manager.entity.Role;
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

//    public ResisterResponse Resister(AccountCreationRequest request){
//        Accounts accounts= accountRepository.findByUsername(request.getUserName());
//        if(accounts!=null){
//            ResisterResponse response=new ResisterResponse();
//            response.setMessage("Account existed");
//            return response;
//        }
//        String username= request.getUserName();
//        String password= request.getPassword();
//        return null;
//    }

    public CreateAccResponse createAccount(String username, String password){
            Accounts account =new Accounts();
            account= accountRepository.findByUsername(username);
            if(account!=null){
            CreateAccResponse response=new CreateAccResponse();
            response.setMessage("Account existed");
            return response;
            }
            Accounts accounts = new Accounts();
            accounts.setUsername(username);
            accounts.setPassword(password);
            accounts.setRole(Role.User);
            accountRepository.save(accounts);
            CreateAccResponse response=new CreateAccResponse();
            response.setMessage("Account existed");
            System.out.println(username+" asdfasdfasdf");
            return response;
    }
}
