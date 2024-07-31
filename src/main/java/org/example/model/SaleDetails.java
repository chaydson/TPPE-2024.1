package org.example.model;

public class SaleDetails {
    private double discount;
    private final Tax tax;
    private Double total;
    private Double shipment;

    public SaleDetails(double discount, Tax tax, Double total, Double shipment) {
        this.discount = discount;
        this.tax = tax;
        this.total = total;
        this.shipment = shipment;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Tax getTax() {
        return tax;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getShipment() {
        return shipment;
    }

    public void setShipment(Double shipment) {
        this.shipment = shipment;
    }

    @Override
    public String toString() {
        return "SaleDetails{" +
                "discount=" + discount +
                ", tax=" + tax +
                ", total=" + total +
                ", shipment=" + shipment +
                '}';
    }
}
