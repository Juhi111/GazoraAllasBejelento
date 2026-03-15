package com.example.gazoraallasbejelento;

import com.example.gazoraallasbejelento.data.entity.Reading;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class ReadingSortTest {

    @Test
    public void readings_sortedAscendingByValue() {
        List<Reading> readings = new ArrayList<>();
        readings.add(new Reading(1, "2026-01-01", 2000, "A"));
        readings.add(new Reading(1, "2026-01-02", 1500, "B"));
        readings.add(new Reading(1, "2026-01-03", 3000, "C"));

        Collections.sort(readings, Comparator.comparingInt(Reading::getValue));

        assertEquals(1500, readings.get(0).getValue());
        assertEquals(2000, readings.get(1).getValue());
        assertEquals(3000, readings.get(2).getValue());
    }
}
