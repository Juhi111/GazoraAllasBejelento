package com.example.gazoraallasbejelento;

import com.example.gazoraallasbejelento.data.entity.Reading;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReadingNoteTest {

    @Test
    public void readingNote_savedCorrectly() {
        Reading reading = new Reading(
                2,
                "2026-03-15",
                1450,
                "havi diktálás"
        );

        assertEquals("havi diktálás", reading.getNote());
    }
}
