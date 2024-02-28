package org.example;

import org.example.entity.Bill;
import org.example.entity.Customer;
import org.example.service.SystemService;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Ödev-2-cohorts
 *
 */
public class App
{
    public static void main( String[] args ) {

        List<Customer> customers = new ArrayList<>();

        // Müşteriler oluşturuldu ve faturaları eklendi
        Customer customer1 = new Customer("Customer1", "Sector1");
        customer1.addBill(new Bill(1000, Month.JUNE));
        customer1.addBill(new Bill(1500, Month.JULY));

        Customer customer2 = new Customer("Customer2", "Sector2");
        customer2.addBill(new Bill(2000, Month.JUNE));
        customer2.addBill(new Bill(2500, Month.JULY));

        Customer customer3 = new Customer("Customer3", "Sector1");
        customer3.addBill(new Bill(3000, Month.JUNE));
        customer3.addBill(new Bill(3500, Month.JULY));

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

        SystemService systemService = new SystemService();

        // Tüm müşterileri listeleme
        systemService.listAllCustomers(customers);

        // Yeni müşteri oluşturma
        Customer newCustomer = new Customer("NewCustomer", "NewSector");
        systemService.createNewCustomer(customers, newCustomer);

        // İçerisinde 'C' harfi olan müşterileri listeleme
        List<Customer> filteredCustomers = systemService.filterCustomersByName(customers, "C");
        filteredCustomers.forEach(customer -> System.out.println(customer.getName()));

        // Haziran ayında kayıt olan müşterilerin faturalarının toplam tutarını listeleme
        double totalAmountInJune = systemService.calculateTotalAmountInMonth(customers, Month.JUNE);
        System.out.println("Haziran ayında kayıt olan müşterilerin toplam fatura tutarı: " + totalAmountInJune);

        // Sistemdeki tüm faturaları listeleme
        List<Bill> allInvoices = systemService.getAllInvoices(customers);
        allInvoices.forEach(invoice -> System.out.println("Amount: " + invoice.getAmount() + ", Month: " + invoice.getMonth()));

        // Sistemdeki 1500TL üstündeki faturaları listeleme
        List<Bill> invoicesOver1500 = systemService.filterInvoicesOverAmount(allInvoices, 1500);
        invoicesOver1500.forEach(invoice -> System.out.println("Amount over 1500: " + invoice.getAmount()));

        // Sistemdeki 1500TL üstündeki faturaları ortalamasını hesaplama
        double averageAmountOver1500 = systemService.calculateAverageAmount(invoicesOver1500);
        System.out.println("Average amount over 1500: " + averageAmountOver1500);

        // Sistemdeki 500TL altındaki faturalara sahip müşterilerin isimlerini listeleme
        List<String> customersWithInvoicesUnder500 = systemService.getCustomersWithInvoicesUnderAmount(customers, 500);
        customersWithInvoicesUnder500.forEach(customer -> System.out.println("Customer with invoice under 500: " + customer));

        // Haziran ayını faturalarının ortalaması 750 altı olan firmaların hangi sektörde olduğunu listeleme
        List<String> sectorsWithAverageUnder750InJune = systemService.getCustomerSectorsWithAverageUnderAmount(customers, Month.JUNE, 750);
        sectorsWithAverageUnder750InJune.forEach(System.out::println);
    }
    }

