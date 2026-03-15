package com.example.gazoraallasbejelento;
import org.junit.Test;
import com.example.gazoraallasbejelento.data.entity.Reading;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ReadingFilterTest {

    @Test
    public void readings_filteredByMeterId() {
        List<Reading> readings = new ArrayList<>();
        readings.add(new Reading(1, "2026-01-01", 1000, "A"));
        readings.add(new Reading(2, "2026-01-02", 1100, "B"));
        readings.add(new Reading(1, "2026-01-03", 1200, "C"));

        List<Reading> filtered = new ArrayList<>();

        for (Reading reading : readings) {
            if (reading.getMeterId() == 1) {
                filtered.add(reading);
            }
        }

        assertEquals(2, filtered.size());
        assertEquals(1, filtered.get(0).getMeterId());
        assertEquals(1, filtered.get(1).getMeterId());
    }
}
