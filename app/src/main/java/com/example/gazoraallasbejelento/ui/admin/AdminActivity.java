package com.example.gazoraallasbejelento.ui.admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gazoraallasbejelento.R;

public class AdminActivity extends AppCompatActivity {

    private Button manageUsersButton;
    private Button manageMetersButton;
    private Button managePropertiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        manageUsersButton = findViewById(R.id.manageUsersButton);
        manageMetersButton = findViewById(R.id.manageMetersButton);
        managePropertiesButton = findViewById(R.id.managePropertiesButton);

        manageUsersButton.setOnClickListener(v ->
                Toast.makeText(this, "Felhasználó kezelés később lesz implementálva", Toast.LENGTH_SHORT).show()
        );

        manageMetersButton.setOnClickListener(v ->
                Toast.makeText(this, "Mérő kezelés később lesz implementálva", Toast.LENGTH_SHORT).show()
        );

        managePropertiesButton.setOnClickListener(v ->
                Toast.makeText(this, "Fogyasztási hely kezelés később lesz implementálva", Toast.LENGTH_SHORT).show()
        );
    }
}