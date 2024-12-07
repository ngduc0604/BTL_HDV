package com.duc.manager.entity;


import jakarta.persistence.*;



@Entity
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_id;
    private Role role;
    private String UserName;
    private String PassWord;

    @OneToOne(mappedBy = "account")
    private Customers customer;


}
