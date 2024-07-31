package org.example.util;

import org.example.model.Product;

import java.util.List;
import java.util.Scanner;

public class ProductCreator {
    private final Scanner  scanner;
    private final List<Product> products;

    public ProductCreator(Scanner scanner, List<Product> products) {
        this.scanner = scanner;
        this.products = products;
    }

    public void createProduct() {
        System.out.println("\nCreating Product...");
        String description = promptDescription();
        Double value = promptValue();
        String unit = promptUnit();

        Product product = new Product(description, value, unit);

        products.add(product);
        System.out.println("Product created successfully");
    }

    private String promptDescription() {
        System.out.print("Enter description for product: ");
        return scanner.nextLine();
    }

    private Double promptValue() {
        System.out.print("Enter product value: ");
        Double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    private String promptUnit() {
        System.out.print("Enter product unit: ");
        return scanner.nextLine();
    }
}