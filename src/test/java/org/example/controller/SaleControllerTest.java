package org.example.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SaleControllerTest {

    private final boolean isCapital;
    private final String region;
    private final double expectedShipping;

    public SaleControllerTest(boolean isCapital, String region, double expectedShipping) {
        this.isCapital = isCapital;
        this.region = region;
        this.expectedShipping = expectedShipping;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {true, "Distrito Federal", 5.0},
                {false, "Distrito Federal", 0.0},
                {true, "Centro-oeste", 10.0},
                {false, "Centro-oeste", 13.0},
                {true, "Sul", 10.0},
                {false, "Sul", 13.0},
                {true, "Nordeste", 15.0},
                {false, "Nordeste", 18.0},
                {true, "Norte", 20.0},
                {false, "Norte", 15.0},
                {true, "Sudeste", 7.0},
                {false, "Sudeste", 10.0},
        });
    }

    @Test
    public void testCalculateShipping() {
        SaleController saleController = new SaleController();
        assertEquals(expectedShipping, saleController.calculateShipping(isCapital, region), 0.0);
    }
}
