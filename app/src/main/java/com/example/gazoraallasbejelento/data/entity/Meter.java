package com.example.gazoraallasbejelento.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meters")
public class Meter {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int propertyId;
    private String meterNumber;
    private String type;

    public Meter(int propertyId, String meterNumber, String type) {
        this.propertyId = propertyId;
        this.meterNumber = meterNumber;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }


    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}