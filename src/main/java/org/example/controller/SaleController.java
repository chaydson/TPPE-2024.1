package org.example.controller;

import org.example.model.*;

import java.time.LocalDate;
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
        String date = getDateFromUser();
        Customer customer = getCustomerFromUser();
        getProductsFromUser();
        double discount = getDiscountFromUser(customer);
        String creditCardDigits = getCreditCardFromUser();

        double icmsTax = calculateTaxes(customer.getAddress().getRegion())[0];
        double municipalTax = calculateTaxes(customer.getAddress().getRegion())[1];
        double shipping = calculateShipping(customer.getAddress().isCapital(), customer.getAddress().getRegion(), customer);

        double totalValue = calculateTotalValue(itens, shipping, icmsTax, municipalTax, customer, creditCardDigits);

        calculateCashBack(customer, creditCardDigits, totalValue);

        Tax tax = new Tax(icmsTax, municipalTax);
        SaleDetails firstSaleDetail = new SaleDetails(discount, tax, totalValue, shipping);
        Sale sale = new Sale(date, customer, itens, creditCardDigits, firstSaleDetail);

        sales.add(sale);
        customer.getPurchasesHistoric().add(sale);
        System.out.println("Sale created successfully");
        resetSaleProcess();
    }

    public void calculateCashBack(Customer customer, String digits, double totalValue) {
        double cashback = 0;

        if (customer instanceof PrimeCustomer) {
            double cashbackByReal = isCompanyCard(digits) ? 0.05 : 0.03;
            cashback = totalValue * cashbackByReal;
            ((PrimeCustomer) customer).setCashback(cashback + ((PrimeCustomer) customer).getCashback());
        }
    }

    private String getDateFromUser() {
        System.out.println("Enter the sale date in the format dd/MM/yyyy");
        return scanner.nextLine();
    }

    private Customer getCustomerFromUser() {
        String cpf;
        do {
            System.out.println("Enter customer CPF:");
            cpf = scanner.nextLine();
        } while (!verifyCustomer(cpf));

        Customer customer = new Customer();

        for (Customer c : customerController.getCustomers()) {
            if (c.getCpf().equals(cpf)) {
                customer = c;
            }
        }
        return customer;
    }

    private void getProductsFromUser() {
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
    }

    private double getDiscountFromUser(Customer customer) {
        double discount = 0.0;
        if (customer instanceof PrimeCustomer) {
            System.out.println(customer.getName() + " has " + String.format("%.2f", ((PrimeCustomer) customer).getCashback()) + " reais in cashback, do you want to use it as a discount? (y/n)");
            String answer = scanner.nextLine();
            if (answer.equals("y")) {
                discount = ((PrimeCustomer) customer).getCashback();
                for (Customer c : customerController.getCustomers()) {
                    if (c.getCpf().equals(customer.getCpf())) {
                        customerController.getCustomers().remove(c);
                        ((PrimeCustomer) c).resetCashBack();
                        customerController.getCustomers().add(c);
                    }
                }
            }
        }
        return discount;
    }

    private String getCreditCardFromUser() {
        System.out.println("Enter the card number in the format XXXX XXXX XXXX XXXX:");
        return scanner.nextLine();
    }

    private void resetSaleProcess() {
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

    public static void isSpecialForAllCustomers() {
        for (Customer c : customerController.getCustomers()) {
            if (isSpecial(c)) { c.setSpecial(true); }
        }
        System.out.println("Successfully updated customers");
    }

    public static boolean isSpecial(Customer customer){
        int previousMonth= LocalDate.now().minusMonths(1).getMonthValue();
        int currentYear = LocalDate.now().getYear();

        double pastMonthPurchases = 0;

        String convertedMonth = previousMonth + "";
        String convertedYear = currentYear + "";

        if(convertedMonth.length() == 1){ convertedMonth = "0" + convertedMonth; }

        for (Sale sale : customer.getPurchasesHistoric()){
            if(sale.getDate().substring(3, 5).equals(convertedMonth) && convertedYear.equals(sale.getDate().substring(6))){
                pastMonthPurchases += sale.getSaleDetails().getTotal();
            }
        }

        return pastMonthPurchases > 100;
    }

    public double calculateDiscount(Customer customer, double totalValue, boolean isCompanyCard){
        double discount = 0;

        if(customer.isSpecial()){ discount = 0.1; }

        if(customer.isSpecial() && isCompanyCard) { discount += 0.1; }

        totalValue = totalValue -  totalValue * discount;

        return totalValue;
    }

    public double calculateTotalValue(List<Product> itens, double shipping, double icmsTax, double municipalTax, Customer customer, String digits){
        double totalValue = 0;

        for (Product p : itens) { totalValue += p.getValue(); }

        totalValue = (icmsTax * totalValue) + (municipalTax * totalValue) + shipping + totalValue;

        totalValue = calculateDiscount(customer, totalValue, isCompanyCard(digits));

        return totalValue;
    }

    public void calculateCachBack(Customer customer, String digits, double totalValue) {
        double cashback = 0;

        if(customer instanceof PrimeCustomer){
            double cashbackByReal = isCompanyCard(digits) ? 0.05 : 0.03;
            cashback = totalValue * cashbackByReal;
            ((PrimeCustomer) customer).setCashback(cashback + ((PrimeCustomer) customer).getCashback());
        }
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
        if(input != null && !input.isEmpty()) {
            String regex = "^4296 13\\d{2} \\d{4} \\d{4}$";

            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(input);

            return matcher.matches();
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
