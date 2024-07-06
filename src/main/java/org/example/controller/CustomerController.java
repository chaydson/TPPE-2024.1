package org.example.controller;

import org.example.model.Address;
import org.example.model.Customer;
import org.example.model.PrimeCustomer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private static final Scanner scanner = new Scanner(System.in);
    private final List<Customer> customers;

    public CustomerController() {
        this.customers = new ArrayList<>();
        Address address = new Address("Centro-oeste", true);
        Customer customer1 = new PrimeCustomer("Lucas", "08415758196", "61991890585", false, "lucas@gmail.com", address, 10);
        Customer customer2 = new Customer("Maria", "12345678901", "61991234567", false, "maria@gmail.com", address);
        Customer customer3 = new Customer("João", "23456789012", "61992345678", false, "joao@gmail.com", address);
        Customer customer4 = new Customer("Ana", "34567890123", "61993456789", false, "ana@gmail.com", address);
        Customer customer5 = new Customer("Pedro", "45678901234", "61994567890", false, "pedro@gmail.com", address);
        Customer customer6 = new Customer("Mariana", "56789012345", "61995678901", false, "mariana@gmail.com", address);
        Customer customer7 = new Customer("Carlos", "67890123456", "61996789012", false, "carlos@gmail.com", address);
        Customer customer8 = new Customer("Fernanda", "78901234567", "61997890123", false, "fernanda@gmail.com", address);
        Customer customer9 = new Customer("Paulo", "89012345678", "61998901234", false, "paulo@gmail.com", address);
        Customer customer10 = new Customer("Julia", "90123456789", "61999012345", false, "julia@gmail.com", address);
        Customer customer11 = new Customer("Rafael", "01234567890", "61999123456", false, "rafael@gmail.com", address);
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.add(customer5);
        customers.add(customer6);
        customers.add(customer7);
        customers.add(customer8);
        customers.add(customer9);
        customers.add(customer10);
        customers.add(customer11);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void createCustomer() {
        System.out.println("\nCreating Customer...");
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Enter customer phone: ");
        String phone = scanner.nextLine();
        System.out.print("Is customer prime (true/false): ");
        boolean isPrime = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline character after nextBoolean()
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter customer region: ");
        String region = scanner.nextLine();
        System.out.print("Is customer in capital (true/false): ");
        boolean capital = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline character after nextBoolean()

        Address address = new Address(region, capital);
        Customer customer;

        if (isPrime) {
            System.out.print("Enter customer cashback amount: ");
            double cashback = scanner.nextDouble();
            scanner.nextLine();
            customer = new PrimeCustomer(name, cpf, phone, false, email, address, cashback);
        } else {
            customer = new Customer(name, cpf, phone, false, email, address);
        }
        customers.add(customer);
        System.out.println("Customer created successfully: " + customer.getName());
    }
}
