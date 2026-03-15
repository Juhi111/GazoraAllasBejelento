package com.example.gazoraallasbejelento;

import com.example.gazoraallasbejelento.data.entity.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserRoleTest {

    @Test
    public void userRole_canBeAdmin() {
        User user = new User(
                "Admin Elek",
                "admin@email.com",
                "123456",
                "ADMIN"
        );

        assertEquals("ADMIN", user.getRole());
    }
}
