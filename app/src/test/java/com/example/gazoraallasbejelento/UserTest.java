package com.example.gazoraallasbejelento;

import com.example.gazoraallasbejelento.data.entity.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void createUser_success() {

        User user = new User(
                "Teszt Elek",
                "teszt@email.com",
                "123456",
                "USER"
        );

        assertEquals("Teszt Elek", user.getName());
        assertEquals("USER", user.getRole());
    }

}