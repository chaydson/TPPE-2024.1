package org.example.model;

public class Address {
    private String region;
    private boolean capital;

    // Constructor
    public Address(String region, boolean capital) {
        this.region = region;
        this.capital = capital;
    }

    // Getters and Setters
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }
}
