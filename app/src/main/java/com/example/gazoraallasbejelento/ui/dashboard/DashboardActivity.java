package com.example.gazoraallasbejelento.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gazoraallasbejelento.R;
import com.example.gazoraallasbejelento.ui.admin.AdminActivity;
import com.example.gazoraallasbejelento.ui.login.LoginActivity;
import com.example.gazoraallasbejelento.ui.reading.NewReadingActivity;
import com.example.gazoraallasbejelento.ui.reading.ReadingListActivity;
import com.example.gazoraallasbejelento.ui.reminder.ReminderActivity;

public class DashboardActivity extends AppCompatActivity {

    private Button newReadingButton;
    private Button readingListButton;
    private Button reminderButton;
    private Button adminButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        newReadingButton = findViewById(R.id.newReadingButton);
        readingListButton = findViewById(R.id.readingListButton);
        reminderButton = findViewById(R.id.reminderButton);
        adminButton = findViewById(R.id.adminButton);
        logoutButton = findViewById(R.id.logoutButton);

        String userRole = getIntent().getStringExtra("userRole");

        if (userRole == null || !userRole.equals("ADMIN")) {
            adminButton.setVisibility(View.GONE);
        }

        newReadingButton.setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, NewReadingActivity.class)));

        readingListButton.setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, ReadingListActivity.class)));

        reminderButton.setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, ReminderActivity.class)));

        adminButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, AdminActivity.class);
            intent.putExtra("userRole", userRole);
            intent.putExtra("userEmail", getIntent().getStringExtra("userEmail"));
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}