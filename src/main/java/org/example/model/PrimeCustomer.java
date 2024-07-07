package org.example.model;

import java.util.List;

public class PrimeCustomer extends Customer{
    private double cashback;

    // Constructor
    public PrimeCustomer(String name, String cpf, String phone, boolean special, String email, Address address, double cashback) {
        super(name, cpf, phone, special, email, address);
        this.cashback = cashback;
    }

    public PrimeCustomer(String name, String cpf, String phone, boolean special, String email, Address address, List<Sale> purchasesHistoric, double cashback) {
        super(name, cpf, phone, special, email, address, purchasesHistoric);
        this.cashback = cashback;
    }

    // Getter and Setter for cashback
    public double getCashback() {
        return cashback;
    }

    public void setCashback(double cashback) {
        this.cashback += cashback;
    }

    public void resetCashBack(){
        this.cashback = 0.0;
    }

    @Override
    public String toString() {
        return super.toString() + ", Cashback: " + cashback +
                "\n";
    }
}
