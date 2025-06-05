package com.duc.manager.service;

import com.duc.manager.dto.request.CustomerCreationRequest;
import com.duc.manager.dto.request.CustomerUpdateRequest;
import com.duc.manager.dto.request.ProductUpdateRequest;
import com.duc.manager.entity.Customers;
import com.duc.manager.entity.Products;
import com.duc.manager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository CustomerRepository;

    public Customers createCustomer(CustomerCreationRequest request){
        Customers customer= new Customers();

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());

        return CustomerRepository.save(customer);
    }

    public List<Customers> getCustomers(){
        return CustomerRepository.findAll();
    }

    public Customers getCustomer(int Id){
        return CustomerRepository.findById(Id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public long getNumberOfCustomer(){
        return CustomerRepository.count();
    }

    public List<Map<String, Object>> classifyCustomersByTotalSpent(){
        return CustomerRepository.classifyCustomersByTotalSpent();
    }
    public Customers updateCustomer(int Id, CustomerUpdateRequest request){
        Customers customer= getCustomer(Id);
        customer.setName(request.getName());
        customer.setAddress(request.getAddress());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        return CustomerRepository.save(customer);
    }
}
