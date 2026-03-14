package com.example.gazoraallasbejelento.ui.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gazoraallasbejelento.R;
import com.example.gazoraallasbejelento.data.database.AppDatabase;
import com.example.gazoraallasbejelento.data.entity.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText registerEmailInput;
    private EditText registerPasswordInput;
    private EditText confirmPasswordInput;
    private Button registerUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameInput = findViewById(R.id.nameInput);
        registerEmailInput = findViewById(R.id.registerEmailInput);
        registerPasswordInput = findViewById(R.id.registerPasswordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        registerUserButton = findViewById(R.id.registerUserButton);

        registerUserButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String name = nameInput.getText().toString().trim();
        String email = registerEmailInput.getText().toString().trim();
        String password = registerPasswordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Minden mezőt ki kell tölteni", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "A jelszavak nem egyeznek", Toast.LENGTH_SHORT).show();
            return;
        }

        AppDatabase db = AppDatabase.getInstance(this);

        if (db.userDao().getUserByEmail(email) != null) {
            Toast.makeText(this, "Ez az email már regisztrálva van", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(name, email, password, "USER");
        db.userDao().insert(user);

        Toast.makeText(this, "Sikeres regisztráció", Toast.LENGTH_SHORT).show();
        finish();
    }
}