package com.example.demo.Dto;

import java.time.LocalDate;

public class MeterReadingRequest {
    private String accountNumber;
    private LocalDate readingDate;
    private int readingValue;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
}
