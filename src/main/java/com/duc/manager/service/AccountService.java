package com.duc.manager.service;

import com.duc.manager.dto.request.AccountCreationRequest;
import com.duc.manager.dto.response.ChangePasswordResponse;
import com.duc.manager.dto.response.CreateAccResponse;
import com.duc.manager.dto.response.LoginResponse;
import com.duc.manager.dto.response.ResisterResponse;
import com.duc.manager.entity.Accounts;
import com.duc.manager.entity.Carts;
import com.duc.manager.entity.Customers;
import com.duc.manager.entity.Role;
import com.duc.manager.repository.AccountRepository;
import com.duc.manager.repository.CartDetailsRepository;
import com.duc.manager.repository.CartRepositoy;
import com.duc.manager.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepositoy cartRepositoy;



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

    @Transactional
    public Accounts createAccount(String password, String username, String address, String email, String name, String phone) {
        if (accountRepository.existsByUsername(username)) { throw new IllegalArgumentException("Username already exists!"); }

        Accounts account = new Accounts();
        account.setRole(Role.User);
        account.setPassword(password);
        account.setUsername(username);
        Customers customer = new Customers();
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setName(name);
        customer.setPhone(phone);
        customer.setAccount(account);
        account.setCustomer(customer);
        Carts cart = new Carts();
        cart.setCustomers(customer);
        customer.setCart(cart);
        accountRepository.save(account);
        customerRepository.save(customer);
        cartRepositoy.save(cart);
        return account;
    }

    @Transactional
    public Accounts createAdminAccount(String password, String username, String address, String email, String name, String phone) {
        if (accountRepository.existsByUsername(username)) { throw new IllegalArgumentException("Username already exists!"); }

        Accounts account = new Accounts();
        account.setRole(Role.Admin);
        account.setPassword(password);
        account.setUsername(username);
        Customers customer = new Customers();
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setName(name);
        customer.setPhone(phone);
        customer.setAccount(account);
        account.setCustomer(customer);
        Carts cart = new Carts();
        cart.setCustomers(customer);
        customer.setCart(cart);
        accountRepository.save(account);
        customerRepository.save(customer);
        cartRepositoy.save(cart);
        return account;
    }
    public Accounts getAccount(int id){
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
    public ChangePasswordResponse updatePassword(int id, String password, String newPassword){
        int account_id=accountRepository.getAccountIdByCustomerId(id);
        ChangePasswordResponse response = new ChangePasswordResponse();
        Accounts account=getAccount(account_id);
        if(!password.equals(account.getPassword())){
            response.setMessage("Mat khau hien tai khong chinh xac");
        }else if(password.equals(newPassword)){
            response.setMessage("Mat khau khong duoc trung voi mat khau cu");
        }else{
            account.setPassword(newPassword);
            response.setMessage("Doi mat khau thanh cong");
            accountRepository.save(account);
        }
        return response;
    }
}





