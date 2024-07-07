package org.example.controller.sale;

import org.example.controller.SaleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IsCompanyCardTest {

    private final String input;
    private final boolean expectedResult;

    public IsCompanyCardTest(String input, boolean expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameters(name = "{index}: isCompanyCard({0}) = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Valid inputs
                {"4296 1300 1234 5678", true},
                {"4296 1399 9876 5432", true},
                {"4296 1345 6789 0123", true},
                // Invalid inputs
                {"4296 1300 1234 56789", false},   // too many digits in last group
                {"4296 1300 1234", false},         // incomplete number
                {"4296 1300 1234 ABCD", false},    // contains non-digit characters
                {"1234 5678 9012 3456", false},    // incorrect format
                {"", false},                       // empty string
                {null, false}                      // null input
        });
    }

    @Test
    public void testIsCompanyCard() {
        boolean actualResult = SaleController.isCompanyCard(input);
        assertEquals("Result does not match for input: " + input, expectedResult, actualResult);
    }
}

