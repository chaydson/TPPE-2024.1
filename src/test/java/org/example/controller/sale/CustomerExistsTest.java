package org.example.controller.sale;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CustomerExistsTest {
    @Parameterized.Parameters(name = "{index}: cpf={0} expected={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "08415758196", true },
                { "12345678901", true },
                { "23456789012", true },
                { "34363830123", false },
                { "45575951534", false }
        });
    }

    @Parameterized.Parameter(0)
    public String cpf;

    @Parameterized.Parameter(1)
    public boolean expected;

    @Test
    public void testVerifyCustomer() {
        SaleController saleController = new SaleController();
        boolean result = saleController.verifyCustomer(cpf);
        assertEquals(expected, result);
    }
}
