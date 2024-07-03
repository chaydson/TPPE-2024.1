package org.example.model;

public class Customer {
    private String name;
    private String cpf; // @id
    private String phone;
    private boolean special;
    private String email;
    private Address address;

    public Customer() {}

    // Constructor
    public Customer(String name, String cpf, String phone, boolean special, String email, Address address) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.special = special;
        this.email = email;
        this.address = address;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                ", CPF: " + cpf +
                ", Phone: " + phone +
                ", Special: " + special +
                ", Email: " + email +
                "\n";
    }
}