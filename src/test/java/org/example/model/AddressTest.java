package org.example.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AddressTest {

    private final String region;
    private final boolean capital;

    public AddressTest(String region, boolean capital) {
        this.region = region;
        this.capital = capital;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Distrito Federal", true},
                {"Norte", true},
                {"Sul", false}
        });
    }

    @Test
    public void testAddressCreation() {
        Address address = new Address(region, capital);
        assertEquals(region, address.getRegion());
        assertEquals(capital, address.isCapital());
    }
}

