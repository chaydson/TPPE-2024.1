package org.example.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
    private final List<Sale> purchasesHistoric;

    public PrimeCustomerTest(String name, String cpf, String phone, boolean special, String email, Address address, List<Sale> purchasesHistoric, double cashback) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.special = special;
        this.email = email;
        this.address = address;
        this.purchasesHistoric = purchasesHistoric;
        this.cashback = cashback;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Sale> purchasesHistoric = new ArrayList<>();

        return Arrays.asList(new Object[][]{
                {"John Doe", "12345678900", "555-1234", false, "john@example.com", new Address("Centro Oeste", true), purchasesHistoric, 10.0},
                {"Jane Doe", "98765432100", "555-5678", true, "jane@example.com", new Address("Distrito Federal", false), purchasesHistoric, 20.0}
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
        double newCashback = primeCustomer.getCashback();
        assertEquals(newCashback, primeCustomer.getCashback(), 0);
    }

    @Test
    public void testToString() {
        PrimeCustomer customer = new PrimeCustomer(name, cpf, phone, special, email, address, purchasesHistoric, cashback);
        String expectedString = "Customer{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", phone='" + phone + '\'' +
                ", special=" + special +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", purchasesHistoric size=" + purchasesHistoric.size() +
                "}, Cashback: " + cashback + "\n";
        assertEquals(expectedString, customer.toString());
    }
}
