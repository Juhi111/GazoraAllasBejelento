package com.example.gazoraallasbejelento.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gazoraallasbejelento.data.dao.ReadingDao;
import com.example.gazoraallasbejelento.data.entity.Meter;
import com.example.gazoraallasbejelento.data.entity.Property;
import com.example.gazoraallasbejelento.data.entity.Reading;
import com.example.gazoraallasbejelento.data.entity.Reminder;
import com.example.gazoraallasbejelento.data.entity.User;

@Database(
        entities = {
                User.class,
                Property.class,
                Meter.class,
                Reading.class,
                Reminder.class
        },
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract ReadingDao readingDao();

    public static synchronized AppDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "gazora_database"
                    )
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
