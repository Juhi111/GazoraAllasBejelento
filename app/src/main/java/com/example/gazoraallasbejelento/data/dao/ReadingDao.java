package com.example.gazoraallasbejelento.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.gazoraallasbejelento.data.entity.Reading;

import java.util.List;

@Dao
public interface ReadingDao {

    @Insert
    void insert(Reading reading);

    @Query("SELECT * FROM readings")
    List<Reading> getAllReadings();


    @Query("SELECT * FROM readings WHERE id = :readingId LIMIT 1")
    Reading getReadingById(int readingId);

    @Delete
    void delete(Reading reading);


}
