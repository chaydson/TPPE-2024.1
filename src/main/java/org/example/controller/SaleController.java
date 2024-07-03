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

    Boolean insertingProduct = true;
    List<Product> itens = new ArrayList<>();
    Integer count = 1;

    public void createSale() {
        System.out.println("Creating Sale...");
        System.out.println("Enter the sale date in the format dd/MM/yyyy");
        scanner.nextLine();
        String date = scanner.nextLine();
        System.out.println("Enter customer CPF:");
        String cpf = scanner.nextLine();
        while (insertingProduct) {
            System.out.println("Enter the " + count + "º Product Code:");
            count++;
            Integer product = scanner.nextInt();
            for (Product p : productController.getProducts()) {
                if (p.getCode().equals(product)) {
                    System.out.println("Item added successfully: " + p.getDescription());
                    itens.add(p);
                }
            }
            System.out.println("Do you want to add another product? (y/n)");
            scanner.nextLine();
            String answer = scanner.nextLine();
            if (answer.equals("n")) { insertingProduct = false; }
        }

        System.out.println("Enter Payment Method:");
        String paymentMethod = scanner.nextLine();
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

        Double shipping = calculateShipping(customer.getAddress().isCapital(), customer.getAddress().getRegion());

        Sale sale = new Sale(date, customer, itens, paymentMethod, shipping, discount, icms, municipalTax);

        sales.add(sale);
        System.out.println("Sale created successfully");
    }

    public double calculateShipping(boolean isCapital, String region) {
        return switch (region) {
            case "Distrito Federal" -> isCapital ? 5.0 : 0.0;
            case "Centro-oeste", "Sul" -> isCapital ? 10.0 : 13.0;
            case "Nordeste" -> isCapital ? 15.0 : 18.0;
            case "Norte" -> isCapital ? 20.0 : 15.0;
            case "Sudeste" -> isCapital ? 7.0 : 10.0;
            default -> 0.0;
        };
    }
}
