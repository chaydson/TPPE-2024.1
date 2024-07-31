package org.example.model;

public class Tax {
    private double taxICMS;
    private double taxMunicipal;

    public Tax(double taxICMS, double taxMunicipal) {
        this.taxICMS = taxICMS;
        this.taxMunicipal = taxMunicipal;
    }

    public double getTaxICMS() {
        return taxICMS;
    }

    public void setTaxICMS(double taxICMS) {
        this.taxICMS = taxICMS;
    }

    public double getTaxMunicipal() {
        return taxMunicipal;
    }

    public void setTaxMunicipal(double taxMunicipal) {
        this.taxMunicipal = taxMunicipal;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "taxICMS=" + taxICMS +
                ", taxMunicipal=" + taxMunicipal +
                '}';
    }
}
