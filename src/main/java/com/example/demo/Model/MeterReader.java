package com.example.demo.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class MeterReader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate readingDate;
    private int readingValue;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(LocalDate readingDate) {
        this.readingDate = readingDate;
    }

    public int getReadingValue() {
        return readingValue;
    }

    public void setReadingValue(int readingValue) {
        this.readingValue = readingValue;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
