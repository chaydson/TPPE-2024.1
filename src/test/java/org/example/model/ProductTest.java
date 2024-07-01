package org.example.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.UUID;

import org.junit.Test;

public class ProductTest {

    @Test
    public void testProductInstantiation() {
        Product product = new Product("Product Description", 99.99, "kg");

        assertNotNull(product);
        assertNotNull(product.getCode());
        assertEquals("Product Description", product.getDescription());
        assertEquals(Double.valueOf(99.99), product.getValue());
        assertEquals("kg", product.getUnit());
    }

    @Test
    public void testGettersAndSetters() {
        Product product = new Product("Product Description", 99.99, "kg");

        UUID initialCode = product.getCode();

        product.setDescription("New Description");
        assertEquals("New Description", product.getDescription());

        product.setValue(199.99);
        assertEquals(Double.valueOf(199.99), product.getValue());

        product.setUnit("pcs");
        assertEquals("pcs", product.getUnit());

        // Ensure the code (UUID) remains unchanged
        assertEquals(initialCode, product.getCode());
    }

    @Test
    public void testNegativeValueInConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("Product Description", -10.0, "kg");
        });
        assertEquals("Value cannot be negative or null", exception.getMessage());
    }

    @Test
    public void testNullValueInConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("Product Description", null, "kg");
        });
        assertEquals("Value cannot be negative or null", exception.getMessage());
    }

    @Test
    public void testNullValueInSetter() {
        Product product = new Product("Product Description", 10.0, "kg");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setValue(null);
        });
        assertEquals("Value cannot be negative or null", exception.getMessage());
    }

    @Test
    public void testNegativeValueInSetter() {
        Product product = new Product("Product Description", 10.0, "kg");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setValue(-5.0);
        });
        assertEquals("Value cannot be negative or null", exception.getMessage());
    }

    @Test
    public void testNullUnitInConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("Product Description", 10.0, null);
        });
        assertEquals("Unit cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testEmptyUnitInConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("Product Description", 10.0, "");
        });
        assertEquals("Unit cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testNullUnitInSetter() {
        Product product = new Product("Product Description", 10.0, "kg");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setUnit(null);
        });
        assertEquals("Unit cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testEmptyUnitInSetter() {
        Product product = new Product("Product Description", 10.0, "kg");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setUnit("");
        });
        assertEquals("Unit cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testNullDescriptionInConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product(null, 10.0, "kg");
        });
        assertEquals("Description cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testEmptyDescriptionInConstructor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("", 10.0, "kg");
        });
        assertEquals("Description cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testNullDescriptionInSetter() {
        Product product = new Product("Product Description", 10.0, "kg");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setDescription(null);
        });
        assertEquals("Description cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testEmptyDescriptionInSetter() {
        Product product = new Product("Product Description", 10.0, "kg");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setDescription("");
        });
        assertEquals("Description cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testToString() {
        Product product = new Product("Product Description", 99.99, "kg");
        String expected = "code=" + product.getCode() +
                ", description='Product Description'" +
                ", value=99.99" +
                ", unit='kg'" +
                "\n";
        assertEquals(expected, product.toString());
    }
}
