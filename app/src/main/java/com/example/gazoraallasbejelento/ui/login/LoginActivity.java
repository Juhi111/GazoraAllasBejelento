package com.example.gazoraallasbejelento.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gazoraallasbejelento.R;
import com.example.gazoraallasbejelento.data.database.AppDatabase;
import com.example.gazoraallasbejelento.data.entity.User;
import com.example.gazoraallasbejelento.ui.dashboard.DashboardActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        loginButton.setOnClickListener(v -> login());
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
    private void login() {

        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email és jelszó szükséges", Toast.LENGTH_SHORT).show();
            return;
        }

        AppDatabase db = AppDatabase.getInstance(this);

        User user = db.userDao().getUserByEmail(email);

        if (user == null) {
            Toast.makeText(this, "Nincs ilyen felhasználó", Toast.LENGTH_SHORT).show();
            return;
        }

        String savedPassword = user.getPassword();
        if (savedPassword == null || !savedPassword.equals(password)) {
            Toast.makeText(this, "Hibás jelszó", Toast.LENGTH_SHORT).show();
            return;
        }

        String role = user.getRole();
        if (role == null || role.isEmpty()) {
            role = "USER";
        }

        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        intent.putExtra("userRole", role);
        startActivity(intent);
        finish();
    }
}