package org.example.controller.sale;

import org.example.controller.SaleController;
import org.example.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IsSpecialTest {

    private Customer customer;
    private boolean expectedSpecialStatus;

    public IsSpecialTest(Customer customer, boolean expectedSpecialStatus) {
        this.customer = customer;
        this.expectedSpecialStatus = expectedSpecialStatus;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Address address = new Address("Sample Region", true);

        Tax tax = new Tax(0, 0);

        SaleDetails firstSaleDetail = new SaleDetails(0.0, tax, 110.0, 10.0);
        SaleDetails secondSaleDetail = new SaleDetails(0.0, tax, 95.0, 5.0);
        SaleDetails thirdSaleDetail = new SaleDetails(0.0, tax, 90.0, 10.0);
        SaleDetails fourthSaleDetail = new SaleDetails(0.0, tax, 95.0, 5.0);

        // Sample purchases for a special customer
        List<Sale> purchasesOver100 = Arrays.asList(
                new Sale("01/06/2024", null, null, "Credit Card", firstSaleDetail),
                new Sale("02/05/2024", null, null, "Debit Card", secondSaleDetail)
        );

        // Sample purchases for a non-special customer
        List<Sale> purchasesUnder100 = Arrays.asList(
                new Sale("01/06/2024", null, null, "Credit Card", thirdSaleDetail),
                new Sale("02/05/2024", null, null, "Debit Card", fourthSaleDetail)
        );

        // Create test cases
        return Arrays.asList(new Object[][] {
                { new Customer("John Doe", "12345678900", "123456789", true, "john.doe@example.com", address, purchasesOver100), true },
                { new Customer("Jane Smith", "98765432100", "987654321", false, "jane.smith@example.com", address, purchasesUnder100), false },
        });
    }

    @Test
    public void testIsSpecial() {
        assertEquals(expectedSpecialStatus, SaleController.isSpecial(customer));
    }
}
