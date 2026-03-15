package com.example.gazoraallasbejelento;

import com.example.gazoraallasbejelento.data.entity.Meter;

import org.junit.Test;

import static org.junit.Assert.*;

public class MeterTest {

    @Test
    public void createMeter_success() {

        Meter meter = new Meter(
                1,
                "GZ-123456",
                "gas"
        );

        assertEquals(1, meter.getPropertyId());
        assertEquals("GZ-123456", meter.getMeterNumber());
    }
}
