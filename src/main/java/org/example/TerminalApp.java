package org.example;

import org.example.controller.CustomerController;

import java.util.*;

public class TerminalApp {

    private static final CustomerController customerController = new CustomerController();
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Terminal Options:");
            System.out.println("1. Create Customer");
            System.out.println("2. List Customer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customerController.createCustomer();
                    break;
                case 2:
                    System.out.print(customerController.getCustomers());
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting Terminal...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                    break;
            }
        }
        scanner.close();
    }
}
