package org.example.controller;

import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductController {
    private static final Scanner scanner = new Scanner(System.in);
    private final List<Product> products;

    public ProductController() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void createProduct() {
        System.out.println("\nCreating Product...");
        System.out.print("Enter customer description for product: ");
        String description = scanner.nextLine();
        System.out.print("Enter product value: ");
        Double value = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter customer unit: ");
        String unit = scanner.nextLine();

        Product product = new Product(description, value, unit);

        products.add(product);
        System.out.println("Product created successfully");
    }
}
