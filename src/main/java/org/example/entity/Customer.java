package org.example.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Customer {

    private String name;
    private String sector;
    private List<Bill> Bills;

    public Customer(String name, String sector) {
        this.name = name;
        this.sector = sector;
        this.Bills = new ArrayList<>();
    }

    public void addBill(Bill bill) {
        Bills.add(bill);
    }
}
