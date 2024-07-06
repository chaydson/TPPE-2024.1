package org.example;

import org.example.controller.SaleController;

import java.util.*;

public class TerminalApp {

    private static final SaleController saleController = new SaleController();
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Terminal Options:");
            System.out.println("1. Create Customer");
            System.out.println("2. List Customer");
            System.out.println("3. Create Product");
            System.out.println("4. List Product");
            System.out.println("5. Create Sale");
            System.out.println("6. List Sale");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    SaleController.customerController.createCustomer();
                    break;
                case 2:
                    System.out.print(SaleController.customerController.getCustomers());
                    break;
                case 3:
                    SaleController.productController.createProduct();
                    break;
                case 4:
                    System.out.print(SaleController.productController.getProducts());
                    break;
                case 5:
                    saleController.createSale();
                    break;
                case 6:
                    System.out.println(saleController.getSales());;
                    break;
                case 7:
                    exit = true;
                    System.out.println("Exiting Terminal...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 7.");
                    break;
            }
        }
        scanner.close();
    }
}
