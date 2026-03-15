package com.example.gazoraallasbejelento;

import com.example.gazoraallasbejelento.data.entity.Property;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyTest {

    @Test
    public void createProperty_success() {
        Property property = new Property(
                1,
                "Otthon",
                "Szeged, Fő utca 1."
        );

        assertEquals(1, property.getUserId());
        assertEquals("Otthon", property.getName());
        assertEquals("Szeged, Fő utca 1.", property.getAddress());
    }
}
