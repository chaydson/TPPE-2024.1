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
    private double taxICMS;
    private double taxMunicipal;
    private Double total;

    public Sale(String date, Customer customer, List<Product> itens, String paymentMethod, Double shipment, Integer discount, double taxICMS, double taxMunicipal, Double total) {
        this.code = UUID.randomUUID();
        this.date = date;
        this.customer = customer;
        this.itens = itens;
        this.paymentMethod = paymentMethod;
        this.shipment = shipment;
        this.discount = discount;
        this.taxICMS = taxICMS;
        this.taxMunicipal = taxMunicipal;
        this.total = total;
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

    public double getTaxICMS() {
        return taxICMS;
    }

    public void setTaxICMS(int taxICMS) {
        this.taxICMS = taxICMS;
    }

    public double getTaxMunicipal() {
        return taxMunicipal;
    }

    public void setTaxMunicipal(int taxMunicipal) {
        this.taxMunicipal = taxMunicipal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
                ", total=" + total +
                '}';
    }
}
