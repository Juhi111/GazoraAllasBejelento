package com.example.gazoraallasbejelento;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gazoraallasbejelento.data.dao.UserDao;
import com.example.gazoraallasbejelento.data.database.AppDatabase;
import com.example.gazoraallasbejelento.data.entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UserDaoIntegrationTest {

    private AppDatabase db;
    private UserDao userDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        userDao = db.userDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertAndReadUser_success() {
        User user = new User(
                "Teszt User",
                "tesztuser@email.com",
                "123456",
                "USER"
        );

        userDao.insert(user);

        User loadedUser = userDao.getUserByEmail("tesztuser@email.com");

        assertNotNull(loadedUser);
        assertEquals("Teszt User", loadedUser.getName());
        assertEquals("USER", loadedUser.getRole());
        assertEquals("123456", loadedUser.getPassword());
    }
}