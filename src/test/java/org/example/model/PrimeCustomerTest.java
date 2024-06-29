package org.example.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PrimeCustomerTest {

    private final String name;
    private final String cpf;
    private final String phone;
    private final boolean special;
    private final String email;
    private final Address address;
    private final double cashback;

    public PrimeCustomerTest(String name, String cpf, String phone, boolean special, String email, Address address, double cashback) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.special = special;
        this.email = email;
        this.address = address;
        this.cashback = cashback;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"John Doe", "12345678900", "555-1234", false, "john@example.com", new Address("Nordeste", true), 50.0},
                {"Jane Doe", "98765432100", "555-5678", true, "jane@example.com", new Address("Norte", false), 100.0}
        });
    }

    @Test
    public void testPrimeCustomerCreation() {
        PrimeCustomer customer = new PrimeCustomer(name, cpf, phone, special, email, address, cashback);
        assertEquals(name, customer.getName());
        assertEquals(cpf, customer.getCpf());
        assertEquals(phone, customer.getPhone());
        assertEquals(special, customer.isSpecial());
        assertEquals(email, customer.getEmail());
        assertEquals(address, customer.getAddress());
        assertEquals(cashback, customer.getCashback(), 0.01); // Using delta for double comparison
    }
}

