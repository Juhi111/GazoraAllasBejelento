package com.example.gazoraallasbejelento.ui.reminder;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gazoraallasbejelento.R;

public class ReminderActivity extends AppCompatActivity {

    private Button saveReminderButton;
    private ListView reminderListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        saveReminderButton = findViewById(R.id.saveReminderButton);
        reminderListView = findViewById(R.id.reminderListView);

        String[] demoReminders = {
                "Havi diktálás - 2026-04-01",
                "Havi diktálás - 2026-05-01"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                demoReminders
        );

        reminderListView.setAdapter(adapter);

        saveReminderButton.setOnClickListener(v ->
                Toast.makeText(this, "Az emlékeztető mentése később lesz bekötve", Toast.LENGTH_SHORT).show()
        );
    }
}