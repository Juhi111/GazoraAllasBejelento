package com.example.gazoraallasbejelento.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "readings")
public class Reading {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int meterId;
    private String date;
    private int value;
    private String note;

    public Reading(int meterId, String date, int value, String note) {
        this.meterId = meterId;
        this.date = date;
        this.value = value;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getMeterId() {
        return meterId;
    }

    public void setMeterId(int meterId) {
        this.meterId = meterId;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
