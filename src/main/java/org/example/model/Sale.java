package org.example.model;

import java.util.List;
import java.util.UUID;

public class Sale {
    private UUID code;
    private String date;
    private Customer customer;
    private List<Product> itens;
    private String paymentMethod;
    private Double shipment;
    private Integer discount;
    private Double taxICMS;
    private Double taxMunicipal;

    public Sale(String date, Customer customer, List<Product> itens, String paymentMethod, Double shipment, Integer discount, Double taxICMS, Double taxMunicipal) {
        this.date = date;
        this.customer = customer;
        this.itens = itens;
        this.paymentMethod = paymentMethod;
        this.shipment = shipment;
        this.discount = discount;
        this.taxICMS = taxICMS;
        this.taxMunicipal = taxMunicipal;
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

    public Double getShipment() {
        return shipment;
    }

    public void setShipment(Double shipment) {
        this.shipment = shipment;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getTaxICMS() {
        return taxICMS;
    }

    public void setTaxICMS(Double taxICMS) {
        this.taxICMS = taxICMS;
    }

    public Double getTaxMunicipal() {
        return taxMunicipal;
    }

    public void setTaxMunicipal(Double taxMunicipal) {
        this.taxMunicipal = taxMunicipal;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "code=" + code +
                ", date='" + date + '\'' +
                ", customer=" + customer +
                ", itens=" + itens +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", shipment=" + shipment +
                ", discount=" + discount +
                ", taxICMS=" + taxICMS +
                ", taxMunicipal=" + taxMunicipal +
                '}';
    }
}
