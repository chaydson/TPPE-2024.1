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
        Product product1 = new Product("Tenis Nike Preto", 1599.99, "1 unidade");
        Product product2 = new Product("Camisa Adidas Branca", 199.99, "1 unidade");
        Product product3 = new Product("Calça Jeans Levi's Azul", 299.99, "1 unidade");
        Product product4 = new Product("Relógio Casio Digital", 499.99, "1 unidade");
        Product product5 = new Product("Mochila Nike Preta", 399.99, "1 unidade");
        Product product6 = new Product("Jaqueta Puma Vermelha", 599.99, "1 unidade");
        Product product7 = new Product("Óculos de Sol Ray-Ban", 799.99, "1 unidade");
        Product product8 = new Product("Boné New Era Azul", 99.99, "1 unidade");
        Product product9 = new Product("Bermuda Oakley Preta", 249.99, "1 unidade");
        Product product10 = new Product("Tênis Asics Branco", 699.99, "1 unidade");
        Product product11 = new Product("Chinelo Havaianas Verde", 49.99, "1 unidade");
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);
        products.add(product11);

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
