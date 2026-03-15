package com.example.gazoraallasbejelento;

import com.example.gazoraallasbejelento.data.entity.Meter;
import com.example.gazoraallasbejelento.data.entity.Property;

import org.junit.Test;

import static org.junit.Assert.*;

public class MeterRelationTest {

    @Test
    public void meter_belongsToProperty() {
        Property property = new Property(1, "Lakás", "Budapest, Példa u. 10.");
        property.setId(5);

        Meter meter = new Meter(5, "GZ999999", "gas");

        assertEquals(property.getId(), meter.getPropertyId());
    }
}
