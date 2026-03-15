package com.example.gazoraallasbejelento.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.gazoraallasbejelento.data.entity.Meter;

import java.util.List;

@Dao
public interface MeterDao {

    @Insert
    void insert(Meter meter);

    @Query("SELECT * FROM meters")
    List<Meter> getAllMeters();

    @Query("SELECT * FROM meters WHERE propertyId = :propertyId")
    List<Meter> getMetersForProperty(int propertyId);

    @Delete
    void delete(Meter meter);
}
