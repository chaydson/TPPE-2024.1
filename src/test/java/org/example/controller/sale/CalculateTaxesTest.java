package org.example.controller.sale;

import static org.junit.Assert.assertArrayEquals;
import java.util.Arrays;
import java.util.Collection;

import org.example.controller.SaleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CalculateTaxesTest {

    private final String region;
    private final double[] expected;

    public CalculateTaxesTest(String region, double[] expected) {
        this.region = region;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Distrito Federal", new double[]{0.18, 0.0}},
                {"OtherRegion", new double[]{0.12, 0.04}},
                {"AnotherRegion", new double[]{0.12, 0.04}}
        });
    }

    @Test
    public void testCalculateTaxes() {
        SaleController saleController = new SaleController();
        assertArrayEquals(expected, saleController.calculateTaxes(region), 0.0);
    }
}
