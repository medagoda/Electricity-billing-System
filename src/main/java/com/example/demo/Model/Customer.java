package com.example.demo.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    private String accountNumber;

    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<MeterReader> meaterReadings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Customer(String accountNumber, String name, List<MeterReader> meaterReadings) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.meaterReadings = meaterReadings;
    }

    public Customer() {

    }
}

