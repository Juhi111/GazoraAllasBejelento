package com.example.gazoraallasbejelento;

import com.example.gazoraallasbejelento.data.entity.Reading;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReadingTest {

    @Test
    public void createReading_success() {

        Reading reading = new Reading(
                1,
                "2026-01-01",
                1234,
                "teszt megjegyzés"
        );

        assertEquals(1, reading.getMeterId());
        assertEquals(1234, reading.getValue());
        assertEquals("teszt megjegyzés", reading.getNote());
    }
}
