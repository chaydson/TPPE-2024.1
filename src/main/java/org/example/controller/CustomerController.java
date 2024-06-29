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
