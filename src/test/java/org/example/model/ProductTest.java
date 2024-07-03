package org.example.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public class ProductTest {

    @Parameters(name = "{index}: {1} {2} {3} -> {4}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { null, -10.0, "kg", "Value cannot be negative or null" },
                { null, null, "kg", "Value cannot be negative or null" },
                { null, 10.0, null, "Unit cannot be null or empty" },
                { null, 10.0, "", "Unit cannot be null or empty" },
                { null, 10.0, "kg", "Description cannot be null or empty" },
                { "", 10.0, "kg", "Description cannot be null or empty" },
                { "Camiseta Branca", 10.0, null, "Unit cannot be null or empty" },
                { "Fones de Ouvido", 10.0, "", "Unit cannot be null or empty" },
                { "Mochila Escolar", null, "kg", "Value cannot be negative or null" },
                { "Capa de Celular", -10.0, "kg", "Value cannot be negative or null" },
                { null, 10.0, "kg", "Description cannot be null or empty" },
                { "", 10.0, "kg", "Description cannot be null or empty" },
        });
    }

    @Parameter(0)
    public String description;

    @Parameter(1)
    public Double value;

    @Parameter(2)
    public String unit;

    @Parameter(3)
    public String expectedMessage;

    @Test
    public void testInvalidConstructorArguments() {
        if (description == null || description.isEmpty()) {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Product(description, value, unit);
            });
            assertEquals("Description cannot be null or empty", exception.getMessage());
        } else if (value == null || value < 0) {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Product(description, value, unit);
            });
            assertEquals("Value cannot be negative or null", exception.getMessage());
        } else if (unit == null || unit.isEmpty()) {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Product(description, value, unit);
            });
            assertEquals("Unit cannot be null or empty", exception.getMessage());
        }
    }

    @Test
    public void testProductInstantiation() {
        Product product = new Product("Camisa Preta", 99.99, "kg");

        assertNotNull(product);
        assertNotNull(product.getCode());
        assertEquals("Camisa Preta", product.getDescription());
        assertEquals(Double.valueOf(99.99), product.getValue());
        assertEquals("kg", product.getUnit());
    }

    @Test
    public void testGettersAndSetters() {
        Product product = new Product("Tenis da Nike", 99.99, "kg");

        Integer initialCode = product.getCode();

        product.setDescription("Tenis da Nike 2024");
        assertEquals("Tenis da Nike 2024", product.getDescription());

        product.setValue(199.99);
        assertEquals(Double.valueOf(199.99), product.getValue());

        product.setUnit("pcs");
        assertEquals("pcs", product.getUnit());

        assertEquals(initialCode, product.getCode());
    }

    @Test
    public void testInvalidSetterArguments() {
        Product product = new Product("Garrafa Termica", 10.0, "kg");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setValue(null);
        });
        assertEquals("Value cannot be negative or null", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setValue(-5.0);
        });
        assertEquals("Value cannot be negative or null", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setUnit(null);
        });
        assertEquals("Unit cannot be null or empty", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setUnit("");
        });
        assertEquals("Unit cannot be null or empty", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setDescription(null);
        });
        assertEquals("Description cannot be null or empty", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            product.setDescription("");
        });
        assertEquals("Description cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testToString() {
        Product product = new Product("Smartwatch", 99.99, "kg");
        String expected = "code=" + product.getCode() +
                ", description='Smartwatch'" +
                ", value=99.99" +
                ", unit='kg'" +
                "\n";
        assertEquals(expected, product.toString());
    }
}
