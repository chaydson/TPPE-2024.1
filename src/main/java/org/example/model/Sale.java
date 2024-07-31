package org.example.model;

import java.util.List;
import java.util.UUID;

public class Sale {
    private UUID code;
    private String date;
    private Customer customer;
    private List<Product> itens;
    private String paymentMethod;
    private SaleDetails saleDetails;

    public Sale(String date, Customer customer, List<Product> itens, String paymentMethod, SaleDetails saleDetails) {
        this.date = date;
        this.customer = customer;
        this.itens = itens;
        this.paymentMethod = paymentMethod;
        this.saleDetails = saleDetails;
    }

    public UUID getCode() {
        return code;
    }

    public void setCode(UUID code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getItens() {
        return itens;
    }

    public void setItens(List<Product> itens) {
        this.itens = itens;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public SaleDetails getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(SaleDetails saleDetails) {
        this.saleDetails = saleDetails;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "code=" + code +
                ", date='" + date + '\'' +
                ", customer=" + customer +
                ", itens=" + itens +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", saleDetails=" + saleDetails +
                '}';
    }
}
