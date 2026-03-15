package com.example.gazoraallasbejelento;

import com.example.gazoraallasbejelento.data.entity.Reminder;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReminderTest {

    @Test
    public void createReminder_success() {

        Reminder reminder = new Reminder(
                "Diktálás",
                "2026-02-01"
        );

        assertEquals("Diktálás", reminder.getTitle());
        assertEquals("2026-02-01", reminder.getDate());
    }
}
