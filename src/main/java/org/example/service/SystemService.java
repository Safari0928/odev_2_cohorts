package org.example.service;

import org.example.entity.Bill;
import org.example.entity.Customer;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class SystemService {

    public void listAllCustomers(List<Customer> customers) {
        customers.forEach(customer -> System.out.println(customer.getName()));
    }

    public void createNewCustomer(List<Customer> customers, Customer newCustomer) {
        customers.add(newCustomer);
    }

    public List<Customer> filterCustomersByName(List<Customer> customers, String keyword) {
        return customers.stream()
                .filter(customer -> customer.getName().contains(keyword))
                .collect(Collectors.toList());
    }

    public double calculateTotalAmountInMonth(List<Customer> customers, Month month) {
        return customers.stream()
                .filter(customer -> customer.getBills().stream()
                        .anyMatch(bill -> bill.getMonth() == month))
                .mapToDouble(customer -> customer.getBills().stream()
                        .filter(bill -> bill.getMonth() == month)
                        .mapToDouble(Bill::getAmount)
                        .sum())
                .sum();
    }

    public List<Bill> getAllInvoices(List<Customer> customers) {
        return customers.stream()
                .flatMap(customer -> customer.getBills().stream())
                .collect(Collectors.toList());
    }

    public List<Bill> filterInvoicesOverAmount(List<Bill> invoices, double amount) {
        return invoices.stream()
                .filter(invoice -> invoice.getAmount() > amount)
                .collect(Collectors.toList());
    }

    public double calculateAverageAmount(List<Bill> invoices) {
        return invoices.stream()
                .mapToDouble(Bill::getAmount)
                .average()
                .orElse(0.0);
    }

    public List<String> getCustomersWithInvoicesUnderAmount(List<Customer> customers, double amount) {
        return customers.stream()
                .filter(customer -> customer.getBills().stream()
                        .anyMatch(bill -> bill.getAmount() < amount))
                .map(Customer::getName)
                .collect(Collectors.toList());
    }

    public List<String> getCustomerSectorsWithAverageUnderAmount(List<Customer> customers, Month month, double amount) {
        return customers.stream()
                .filter(customer -> customer.getBills().stream()
                        .filter(bill -> bill.getMonth() == month)
                        .mapToDouble(Bill::getAmount)
                        .average()
                        .orElse(0.0) < amount)
                .map(Customer::getSector)
                .collect(Collectors.toList());
    }

}
