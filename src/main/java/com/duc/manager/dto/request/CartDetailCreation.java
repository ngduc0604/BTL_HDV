package com.duc.manager.dto.request;

import com.duc.manager.entity.Carts;
import com.duc.manager.entity.Customers;
import com.duc.manager.entity.Products;

public class CartDetailCreation {
    private int quantity;
    private int product_id;
    private int customers_id;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCustomers_id() {
        return customers_id;
    }

    public void setCustomers_id(int customers_id) {
        this.customers_id = customers_id;
    }
}
