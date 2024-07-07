package org.example.controller.sale;

import org.example.controller.SaleController;
import org.example.model.Address;
import org.example.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculateDiscountTest {

    private Customer customer;
    private double totalValue;
    private boolean isCompanyCard;
    private double expectedDiscountedValue;

    // Constructor for parameterized tests
    public CalculateDiscountTest(Customer customer, double totalValue, boolean isCompanyCard, double expectedDiscountedValue) {
        this.customer = customer;
        this.totalValue = totalValue;
        this.isCompanyCard = isCompanyCard;
        this.expectedDiscountedValue = expectedDiscountedValue;
    }

    // Define the test data with @Parameters
    @Parameters(name = "{index}: calculateDiscount({0}, {1}, {2})={3}")
    public static Collection<Object[]> data() {
        Address address1 = new Address("Region A", true);
        Address address2 = new Address("Region B", false);

        // Example customers
        Customer regularCustomer = new Customer("John Doe", "123456789", "123-456", false, "john@example.com", address1);
        regularCustomer.setSpecial(false);

        Customer specialCustomer = new Customer("Jane Smith", "987654321", "456-789", true, "jane@example.com", address2);
        specialCustomer.setSpecial(true);

        return Arrays.asList(new Object[][]{
                {regularCustomer, 90.0, false, 90.0},    //   Regular customer, no company card, no special discount
                {regularCustomer, 95.0, true, 95.0},     //   Regular customer, with company card, no special discount
                {specialCustomer, 150.0, false, 135.0},  //   Special customer, no company card, 10% discount
                {specialCustomer, 300.0, true, 240.0}    //   Special customer, with company card, 20% discount
        });
    }

    @Test
    public void testCalculateDiscount() {
        SaleController saleController = new SaleController();
        double actualDiscountedValue = saleController.calculateDiscount(customer, totalValue, isCompanyCard);
        assertEquals("Expected discounted value does not match for customer " + customer.getName(), expectedDiscountedValue, actualDiscountedValue, 0.0);
    }
}
