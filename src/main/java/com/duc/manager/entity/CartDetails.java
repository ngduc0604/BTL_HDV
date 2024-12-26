package com.duc.manager.entity;

import jakarta.persistence.*;

@Entity
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cd_id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Carts cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    private int quantity;

    private Double totalPrice;

    public Carts getCart() {
        return cart;
    }

    public void setCart(Carts cart) {
        this.cart = cart;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
