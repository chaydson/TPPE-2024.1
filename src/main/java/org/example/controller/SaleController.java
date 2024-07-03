package org.example.controller;

import org.example.model.Customer;
import org.example.model.Product;
import org.example.model.Sale;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaleController {
    private static final Scanner scanner = new Scanner(System.in);
    private final List<Sale> sales;

    public static final CustomerController customerController = new CustomerController();
    public static final ProductController productController = new ProductController();

    public SaleController() { this.sales = new ArrayList<>(); }

    public List<Sale> getSales() { return this.sales; }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Boolean insertingProduct = true;
    List<Product> itens = new ArrayList<>();
    Integer count = 1;

    public void createSale() {
        System.out.println("Creating Sale...");
        System.out.println("Enter the sale date in the format dd/MM/yyyy");
        String date = scanner.nextLine();
        System.out.println("Enter customer CPF:");
        String cpf = scanner.nextLine();
        while (insertingProduct) {
            System.out.println("Enter the " + count + "º Product name:");
            String product = scanner.nextLine();
            for (Product p : productController.getProducts()) {
                if (p.getDescription().equals(product)) {
                    itens.add(p);
                }
            }
        }

        System.out.println("Enter Payment Method:");
        String paymentMethod = scanner.nextLine();
        System.out.println("Enter Shipping Price:");
        Double shipping = scanner.nextDouble();
        System.out.println("Enter Discount:");
        Integer discount = scanner.nextInt();
        System.out.println("Enter ICMS:");
        Double icms = scanner.nextDouble();
        System.out.println("Enter Municipal Tax:");
        Double municipalTax = scanner.nextDouble();

        Customer customer =  new Customer();
        for (Customer c : customerController.getCustomers()) {
            if (c.getCpf().equals(cpf)) {
                customer = c;
            }
        }

        Sale sale = new Sale(date, customer, itens, paymentMethod, shipping, discount, icms, municipalTax);

        sales.add(sale);
        System.out.println("Sale created successfully");
    }
}
