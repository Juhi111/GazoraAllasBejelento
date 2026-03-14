package com.example.gazoraallasbejelento.ui.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gazoraallasbejelento.R;

public class RegisterActivity extends AppCompatActivity {

    private Button registerUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUserButton = findViewById(R.id.registerUserButton);

        registerUserButton.setOnClickListener(v ->
                Toast.makeText(this, "A regisztráció később lesz bekötve", Toast.LENGTH_SHORT).show()
        );
    }
}