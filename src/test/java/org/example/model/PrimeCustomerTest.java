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
                {"John Doe", "12345678900", "555-1234", false, "john@example.com", new Address("Centro Oeste", true), 10.0},
                {"Jane Doe", "98765432100", "555-5678", true, "jane@example.com", new Address("Distrito Federal", false), 20.0}
        });
    }

    @Test
    public void testPrimeCustomerCreation() {
        PrimeCustomer primeCustomer = new PrimeCustomer(name, cpf, phone, special, email, address, cashback);
        assertEquals(name, primeCustomer.getName());
        assertEquals(cpf, primeCustomer.getCpf());
        assertEquals(phone, primeCustomer.getPhone());
        assertEquals(special, primeCustomer.isSpecial());
        assertEquals(email, primeCustomer.getEmail());
        assertEquals(address, primeCustomer.getAddress());
        assertEquals(cashback, primeCustomer.getCashback(), 0);
    }

    @Test
    public void testSetCashback() {
        PrimeCustomer primeCustomer = new PrimeCustomer(name, cpf, phone, special, email, address, cashback);
        double newCashback = 30.0;
        primeCustomer.setCashback(newCashback);
        assertEquals(newCashback, primeCustomer.getCashback(), 0);
    }

    @Test
    public void testToString() {
        PrimeCustomer primeCustomer = new PrimeCustomer(name, cpf, phone, special, email, address, cashback);
        String expectedString = "Name: " + name +
                ", CPF: " + cpf +
                ", Phone: " + phone +
                ", Special: " + special +
                ", Email: " + email +
                "\n, Cashback: " + cashback +
                "\n";
        assertEquals(expectedString, primeCustomer.toString());
    }
}
