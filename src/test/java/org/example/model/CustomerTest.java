package org.example.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CustomerTest {

    private final String name;
    private final String cpf;
    private final String phone;
    private final boolean special;
    private final String email;
    private final Address address;
    private final List<Sale> purchasesHistoric;

    public CustomerTest(String name, String cpf, String phone, boolean special, String email, Address address, List<Sale> purchasesHistoric) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.special = special;
        this.email = email;
        this.address = address;
        this.purchasesHistoric = purchasesHistoric;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Sale> purchasesHistoric = new ArrayList<>();

        return Arrays.asList(new Object[][]{
                {"John Doe", "12345678900", "555-1234", false, "john@example.com", new Address("Centro Oeste", true), purchasesHistoric},
                {"Jane Doe", "98765432100", "555-5678", true, "jane@example.com", new Address("Distrito Federal", false), purchasesHistoric}
        });
    }

    @Test
    public void testCustomerCreation() {
        Customer customer = new Customer(name, cpf, phone, special, email, address, purchasesHistoric);
        assertEquals(name, customer.getName());
        assertEquals(cpf, customer.getCpf());
        assertEquals(phone, customer.getPhone());
        assertEquals(special, customer.isSpecial());
        assertEquals(email, customer.getEmail());
        assertEquals(address, customer.getAddress());
        assertEquals(purchasesHistoric, customer.getPurchasesHistoric());
    }

    @Test
    public void testSetName() {
        Customer customer = new Customer(name, cpf, phone, special, email, address, purchasesHistoric);
        String newName = "New Name";
        customer.setName(newName);
        assertEquals(newName, customer.getName());
    }

    @Test
    public void testSetCpf() {
        Customer customer = new Customer(name, cpf, phone, special, email, address, purchasesHistoric);
        String newCpf = "11111111111";
        customer.setCpf(newCpf);
        assertEquals(newCpf, customer.getCpf());
    }

    @Test
    public void testSetPhone() {
        Customer customer = new Customer(name, cpf, phone, special, email, address, purchasesHistoric);
        String newPhone = "555-9999";
        customer.setPhone(newPhone);
        assertEquals(newPhone, customer.getPhone());
    }

    @Test
    public void testSetSpecial() {
        Customer customer = new Customer(name, cpf, phone, special, email, address, purchasesHistoric);
        customer.setSpecial(!special);
        assertEquals(!special, customer.isSpecial());
    }

    @Test
    public void testSetEmail() {
        Customer customer = new Customer(name, cpf, phone, special, email, address, purchasesHistoric);
        String newEmail = "new@example.com";
        customer.setEmail(newEmail);
        assertEquals(newEmail, customer.getEmail());
    }

    @Test
    public void testSetAddress() {
        Customer customer = new Customer(name, cpf, phone, special, email, address, purchasesHistoric);
        Address newAddress = new Address("New Location", false);
        customer.setAddress(newAddress);
        assertEquals(newAddress, customer.getAddress());
    }

    @Test
    public void testToString() {
        Customer customer = new Customer(name, cpf, phone, special, email, address, purchasesHistoric);
        String expectedString = "Customer{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", phone='" + phone + '\'' +
                ", special=" + special +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", purchasesHistoric size=" + purchasesHistoric.size() +
                '}';
        assertEquals(expectedString, customer.toString());
    }
}
