package com.duc.manager.dto.request;

import com.duc.manager.entity.Customers;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


import java.time.LocalDate;
import java.util.Date;

public class OrderCreationRequest {

    private String status;
    private int customer_id;
    private double totalMoney;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {

        this.totalMoney = totalMoney;
    }
}
