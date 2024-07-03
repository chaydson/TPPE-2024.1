package org.example.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CustomerTest {

    private final String name;
    private final String cpf;
    private final String phone;
    private final boolean special;
    private final String email;
    private final Address address;

    public CustomerTest(String name, String cpf, String phone, boolean special, String email, Address address) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.special = special;
        this.email = email;
        this.address = address;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"John Doe", "12345678900", "555-1234", false, "john@example.com", new Address("Centro Oeste", true)},
                {"Jane Doe", "98765432100", "555-5678", true, "jane@example.com", new Address("Distrito Federal", false)}
        });
    }

    @Test
    public void testCustomerCreation() {
        Customer customer = new Customer(name, cpf, phone, special, email, address);
        assertEquals(name, customer.getName());
        assertEquals(cpf, customer.getCpf());
        assertEquals(phone, customer.getPhone());
        assertEquals(special, customer.isSpecial());
        assertEquals(email, customer.getEmail());
        assertEquals(address, customer.getAddress());
    }

    @Test
    public void testSetName() {
        Customer customer = new Customer(name, cpf, phone, special, email, address);
        String newName = "New Name";
        customer.setName(newName);
        assertEquals(newName, customer.getName());
    }

    @Test
    public void testSetCpf() {
        Customer customer = new Customer(name, cpf, phone, special, email, address);
        String newCpf = "11111111111";
        customer.setCpf(newCpf);
        assertEquals(newCpf, customer.getCpf());
    }

    @Test
    public void testSetPhone() {
        Customer customer = new Customer(name, cpf, phone, special, email, address);
        String newPhone = "555-9999";
        customer.setPhone(newPhone);
        assertEquals(newPhone, customer.getPhone());
    }

    @Test
    public void testSetSpecial() {
        Customer customer = new Customer(name, cpf, phone, special, email, address);
        customer.setSpecial(!special);
        assertEquals(!special, customer.isSpecial());
    }

    @Test
    public void testSetEmail() {
        Customer customer = new Customer(name, cpf, phone, special, email, address);
        String newEmail = "new@example.com";
        customer.setEmail(newEmail);
        assertEquals(newEmail, customer.getEmail());
    }

    @Test
    public void testSetAddress() {
        Customer customer = new Customer(name, cpf, phone, special, email, address);
        Address newAddress = new Address("New Location", false);
        customer.setAddress(newAddress);
        assertEquals(newAddress, customer.getAddress());
    }

    @Test
    public void testToString() {
        Customer customer = new Customer(name, cpf, phone, special, email, address);
        String expectedString = "Name: " + name +
                ", CPF: " + cpf +
                ", Phone: " + phone +
                ", Special: " + special +
                ", Email: " + email +
                "\n";
        assertEquals(expectedString, customer.toString());
    }
}
