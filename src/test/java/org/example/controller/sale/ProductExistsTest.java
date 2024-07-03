package org.example.controller.sale;

import org.example.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Parameterized.class)
public class ProductExistsTest {

    private final Integer productId;
    private final String expectedProductName;

    public ProductExistsTest(Integer productId, String expectedProductName) {
        this.productId = productId;
        this.expectedProductName = expectedProductName;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, "Tenis Nike Preto"},
                {1, "Camisa Adidas Branca"},
                {2, "Calça Jeans Levi's Azul"},
                {3, "Relógio Casio Digital"},
                {4, "Mochila Nike Preta"},
                {5, "Jaqueta Puma Vermelha"},
                {6, "Óculos de Sol Ray-Ban"},
                {7, "Boné New Era Azul"},
                {8, "Bermuda Oakley Preta"},
                {9, "Tênis Asics Branco"},
                {10, "Chinelo Havaianas Verde"},
                {11, null}, // Invalid productId
                {12, null}, // Invalid productId
        });
    }

    @Test
    public void testCheckProduct() {
        SaleController saleController = new SaleController();
        Product product = saleController.checkProduct(productId);
        if (expectedProductName != null) {
            assertEquals(expectedProductName, product.getDescription());
        } else {
            assertNull(product);
        }
    }
}
