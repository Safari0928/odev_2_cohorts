package org.example.entity;

import lombok.Data;

import java.time.Month;

@Data
public class Bill {
    private double amount;
    private Month month;

    public Bill(double amount, Month month) {
        this.amount = amount;
        this.month = month;
    }
}
