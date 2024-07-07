package org.example.controller;

import org.example.model.Customer;
import org.example.model.PrimeCustomer;
import org.example.model.Product;
import org.example.model.Sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Customer customer =  new Customer();

        for (Customer c : customerController.getCustomers()) {
            if (c.getCpf().equals(cpf)) {
                customer = c;
            }
        }

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

        double discount = 0.0;
        System.out.println("Enter the card number in the format XXXX XXXX XXXX XXXX:");
        String paymentMethod = scanner.nextLine();
        if (customer instanceof PrimeCustomer){
            System.out.println(customer.getName() + " has " + String.format("%.2f", ((PrimeCustomer) customer).getCashback()) + " reais in cashback, do you want to use it as a discount? (y/n)");
            String answer = scanner.nextLine();
            if (answer.equals("y")) {
                discount = ((PrimeCustomer) customer).getCashback();
                for (Customer c : customerController.getCustomers()) {
                    if (c.getCpf().equals(cpf)) {
                        customerController.getCustomers().remove(c);
                        ((PrimeCustomer) c).resetCashBack();
                        customerController.getCustomers().add(c);
                    }
                }
            }
        }

        double icmsTax = calculateTaxes(customer.getAddress().getRegion())[0];
        double municipalTax = calculateTaxes(customer.getAddress().getRegion())[1];
        double shipping = calculateShipping(customer.getAddress().isCapital(), customer.getAddress().getRegion(), customer);

        double totalValue = calculateTotalValueAndCashBack(shipping, icmsTax, municipalTax, customer, paymentMethod);

        Sale sale = new Sale(date, customer, itens, paymentMethod, shipping, discount, icmsTax, municipalTax, totalValue);

        sales.add(sale);
        System.out.println("Sale created successfully");
        insertingProduct = true;
        count = 1;
        itens = new ArrayList<>();
    }

    public double calculateShipping(boolean isCapital, String region, Customer customer) {

        if (customer instanceof PrimeCustomer) {
            return 0.0;
        }

        double shippingPrice = switch (region) {
            case "Distrito Federal" -> isCapital ? 5.0 : 0.0;
            case "Centro-oeste", "Sul" -> isCapital ? 10.0 : 13.0;
            case "Nordeste" -> isCapital ? 15.0 : 18.0;
            case "Norte" -> isCapital ? 20.0 : 15.0;
            case "Sudeste" -> isCapital ? 7.0 : 10.0;
            default -> 0.0;
        };

        if (customer.isSpecial()) {
            shippingPrice *= 0.7;
        }

        return shippingPrice;
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

    public double calculateTotalValueAndCashBack(double shipping, double icmsTax, double municipalTax, Customer customer, String digits){
        double totalValue = 0;

        for (Product p : itens) { totalValue += p.getValue(); }

        if(customer instanceof PrimeCustomer){
            double cashbackByReal = isCompanyCard(digits) ? 0.05 : 0.03;
            double cashback = totalValue * cashbackByReal;
            ((PrimeCustomer) customer).setCashback(cashback);
        }

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

    public static boolean isCompanyCard(String input) {
        // Expressão regular para corresponder ao formato 4296 13XX XXXX XXXX
        String regex = "^4296 13\\d{2} \\d{4} \\d{4}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
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
