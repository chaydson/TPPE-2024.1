package org.example.controller.sale;

import org.example.controller.SaleController;
import org.example.model.Address;
import org.example.model.Customer;
import org.example.model.Product;
import org.example.model.Sale;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculateTotalValueTest {

    private List<Product> itens;
    private double shipping;
    private double icmsTax;
    private double municipalTax;
    private Customer customer;
    private String digits;
    private double expectedTotalValue;

    // Constructor for parameterized tests
    public CalculateTotalValueTest(List<Product> itens, double shipping, double icmsTax, double municipalTax, Customer customer, String digits, double expectedTotalValue) {
        this.itens = itens;
        this.shipping = shipping;
        this.icmsTax = icmsTax;
        this.municipalTax = municipalTax;
        this.customer = customer;
        this.digits = digits;
        this.expectedTotalValue = expectedTotalValue;
    }

    // Define the test data with @Parameters
    @Parameters(name = "{index}: calculateTotalValue({0}, {1}, {2}, {3}, {4}, {5})={6}")
    public static Collection<Object[]> data() {
        Address address1 = new Address("Region A", true);
        Address address2 = new Address("Region B", false);

        // Example products
        Product product1 = new Product("Product A", 50.0, "unit");
        Product product2 = new Product("Product B", 30.0, "unit");


        List<Product> productList1 = Arrays.asList(product1);
        List<Product> productList2 = Arrays.asList(product2);
        List<Product> productListBoth = Arrays.asList(product1, product2);

        // Example customers
        Customer regularCustomer = new Customer("John Doe", "123456789", "123-456", false, "john@example.com", address1);
        regularCustomer.setSpecial(false);

        Customer specialCustomer = new Customer("Jane Smith", "987654321", "456-789", true, "jane@example.com", address2);
        specialCustomer.setSpecial(true);

        return Arrays.asList(new Object[][]{
                {productList1, 10.0, 0.1, 0.05, regularCustomer, "1234", 67.5},                     // Regular customer, one product, without company card
                {productListBoth, 15.0, 0.2, 0.1, regularCustomer, "4296 1300 1234 5678", 119.0},   // Regular customer, two products, with company card
                {productList2, 8.0, 0.15, 0.08, specialCustomer, "9876", 40.41},                     // Special customer, one product, without company card
                {productListBoth, 12.0, 0.25, 0.12, specialCustomer, "4296 1399 9876 5432", 97.28}   // Special customer, two products, with company card
        });
    }

    @Test
    public void testCalculateTotalValue() {
        SaleController saleController = new SaleController();
        double actualTotalValue = saleController.calculateTotalValue(itens, shipping, icmsTax, municipalTax, customer, digits);
        assertEquals("Expected total value does not match for customer " + customer.getName(), expectedTotalValue, actualTotalValue, 0.0);
    }
}
