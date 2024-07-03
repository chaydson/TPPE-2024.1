package org.example.model;

public class Product {
    private static int nextCode = 0;
    private Integer code;
    private String description;
    private Double value;
    private String unit;

    public Product(String description, Double value, String unit) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (value == null || value < 0) {
            throw new IllegalArgumentException("Value cannot be negative or null");
        }
        if (unit == null || unit.isEmpty()) {
            throw new IllegalArgumentException("Unit cannot be null or empty");
        }
        this.code = nextCode++;
        this.description = description;
        this.value = value;
        this.unit = unit;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        if (value == null || value < 0) {
            throw new IllegalArgumentException("Value cannot be negative or null");
        }
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        if (unit == null || unit.isEmpty()) {
            throw new IllegalArgumentException("Unit cannot be null or empty");
        }
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "code=" + code +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", unit='" + unit + '\'' +
                "\n";
    }
}
