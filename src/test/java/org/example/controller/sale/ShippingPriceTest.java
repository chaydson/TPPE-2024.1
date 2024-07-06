package org.example.controller.sale;

import org.example.controller.SaleController;
import org.example.model.Address;
import org.example.model.Customer;
import org.example.model.PrimeCustomer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ShippingPriceTest {

    private final boolean isCapital;
    private final String region;
    private final double expectedShipping;
    private static final Address address = new Address("Centro-oeste", true);
    private final Customer customer;

    public ShippingPriceTest(boolean isCapital, String region, double expectedShipping, Customer customer) {
        this.isCapital = isCapital;
        this.region = region;
        this.expectedShipping = expectedShipping;
        this.customer = customer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {true, "Distrito Federal", 0.0, new PrimeCustomer("Lucas", "08415758196", "61991890585", true, "lucas@gmail.com", address, 10)},
                {false, "Distrito Federal", 0.0, new PrimeCustomer("Lucas", "08415758196", "61991890585", false, "lucas@gmail.com", address, 10)},
                {true, "Centro-oeste", 10.0, new Customer("Ana", "34567890123", "61993456789", false, "ana@gmail.com", address)},
                {false, "Centro-oeste", 13.0, new Customer("Ana", "34567890123", "61993456789", false, "ana@gmail.com", address)},
                {true, "Sul", 7.0, new Customer("Pedro", "45678901234", "61994567890", true, "pedro@gmail.com", address)},
                {false, "Sul", 9.1, new Customer("Pedro", "45678901234", "61994567890", true, "pedro@gmail.com", address)},
                {true, "Nordeste", 15.0, new Customer("Mariana", "56789012345", "61995678901", false, "mariana@gmail.com", address)},
                {false, "Nordeste", 18.0, new Customer("Mariana", "56789012345", "61995678901", false, "mariana@gmail.com", address)},
                {true, "Norte", 20.0, new Customer("Carlos", "67890123456", "61996789012", false, "carlos@gmail.com", address)},
                {false, "Norte", 15.0, new Customer("Carlos", "67890123456", "61996789012", false, "carlos@gmail.com", address)},
                {true, "Sudeste", 7.0, new Customer("Fernanda", "78901234567", "61997890123", false, "fernanda@gmail.com", address)},
                {false, "Sudeste", 10.0, new Customer("Fernanda", "78901234567", "61997890123", false, "fernanda@gmail.com", address)},
        });
    }

    @Test
    public void testCalculateShipping() {
        SaleController saleController = new SaleController();
        assertEquals(expectedShipping, saleController.calculateShipping(isCapital, region, customer), 0.0);
    }
}
