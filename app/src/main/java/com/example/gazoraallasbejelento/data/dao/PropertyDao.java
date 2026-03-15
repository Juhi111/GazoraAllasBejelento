package com.example.gazoraallasbejelento.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gazoraallasbejelento.data.entity.Property;

import java.util.List;

@Dao
public interface PropertyDao {

    @Insert
    void insert(Property property);

    @Query("SELECT * FROM properties")
    List<Property> getAllProperties();

    @Update
    void update(Property property);

    @Delete
    void delete(Property property);
}
