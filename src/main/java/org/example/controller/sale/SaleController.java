package org.example.controller.sale;

import org.example.model.Customer;
import org.example.model.Product;
import org.example.model.Sale;

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
        String date = scanner.nextLine();
        String cpf;
        do {
            System.out.println("Enter customer CPF:");
            cpf = scanner.nextLine();
        } while (!verifyCustomer(cpf));

        while (insertingProduct) {
            System.out.println("Enter the " + count + "º Product Code:");
            Integer product = scanner.nextInt();

            Product foundedProduct = checkProduct(product);

            if (foundedProduct != null) {
                System.out.println("Item added successfully: " + foundedProduct.getDescription());
                itens.add(foundedProduct);
                count++;
            } else {
                System.out.println("Product not found");
            }

            System.out.println("Do you want to add another product? (y/n)");
            scanner.nextLine();
            String answer = scanner.nextLine();
            if (answer.equals("n") && !itens.isEmpty()) {
                insertingProduct = false;
            } else if (answer.equals("n")) {
                System.out.println("You must add at least one product.");
            }
        }

        System.out.println("Enter Payment Method:");
        String paymentMethod = scanner.nextLine();
        System.out.println("Enter Discount:");
        Integer discount = scanner.nextInt();

        Customer customer =  new Customer();

        for (Customer c : customerController.getCustomers()) {
            if (c.getCpf().equals(cpf)) {
                customer = c;
            }
        }

        double icmsTax = calculateTaxes(customer.getAddress().getRegion())[0];
        double municipalTax = calculateTaxes(customer.getAddress().getRegion())[1];
        double shipping = calculateShipping(customer.getAddress().isCapital(), customer.getAddress().getRegion());

        double totalValue = calculateTotalValue(shipping, icmsTax, municipalTax);

        Sale sale = new Sale(date, customer, itens, paymentMethod, shipping, discount, icmsTax, municipalTax, totalValue);

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

    public double[] calculateTaxes(String region){
        double[] results = new double[2];
        if(region.equals("Distrito Federal")){
            results[0] = 0.18;
        } else {
            results[0] = 0.12;
            results[1] = 0.04;
        }
        return results;
    }

    public double calculateTotalValue(double shipping, double icmsTax, double municipalTax){
        double totalValue = 0;

        for (Product p : itens) { totalValue += p.getValue(); }

        totalValue = (icmsTax * totalValue) + (municipalTax * totalValue) + shipping + totalValue;

        return totalValue;
    }

    public boolean verifyCustomer(String cpf){
        for (Customer c : customerController.getCustomers()) {
            if (c.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public Product checkProduct(Integer productId) {
        for (Product p : productController.getProducts()) {
            if (p.getCode().equals(productId)) {
                return p;
            }
        }
        return null;
    }
}
