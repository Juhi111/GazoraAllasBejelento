package com.example.gazoraallasbejelento.ui.reminder;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.app.DatePickerDialog;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gazoraallasbejelento.R;
import com.example.gazoraallasbejelento.data.database.AppDatabase;
import com.example.gazoraallasbejelento.data.entity.Reminder;
import java.util.ArrayList;
import java.util.List;

public class ReminderActivity extends AppCompatActivity {

    private EditText reminderTitleInput;
    private EditText reminderDateInput;
    private Button saveReminderButton;
    private ListView reminderListView;
    private List<Reminder> reminders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);


        reminderTitleInput = findViewById(R.id.reminderTitleInput);
        reminderDateInput = findViewById(R.id.reminderDateInput);
        saveReminderButton = findViewById(R.id.saveReminderButton);
        reminderListView = findViewById(R.id.reminderListView);

        reminderDateInput.setOnClickListener(v -> showDatePicker());

        saveReminderButton.setOnClickListener(v -> saveReminder());

        reminderListView.setOnItemLongClickListener((parent, view, position, id) -> {
            deleteReminder(position);
            return true;
        });

        loadReminders();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadReminders();
    }

    private void saveReminder() {
        String title = reminderTitleInput.getText().toString().trim();
        String date = reminderDateInput.getText().toString().trim();

        if (title.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Minden mezőt ki kell tölteni", Toast.LENGTH_SHORT).show();
            return;
        }

        Reminder reminder = new Reminder(title, date);

        AppDatabase db = AppDatabase.getInstance(this);
        db.reminderDao().insert(reminder);

        Toast.makeText(this, "Emlékeztető mentve", Toast.LENGTH_SHORT).show();

        reminderTitleInput.setText("");
        reminderDateInput.setText("");

        loadReminders();
    }

    private void loadReminders() {
        AppDatabase db = AppDatabase.getInstance(this);
        reminders = db.reminderDao().getAllReminders();

        List<String> reminderTexts = new ArrayList<>();

        for (Reminder reminder : reminders) {
            String text = reminder.getTitle() + " - " + reminder.getDate();
            reminderTexts.add(text);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                reminderTexts
        );

        reminderListView.setAdapter(adapter);
    }

    private void deleteReminder(int position) {
        if (reminders == null || position < 0 || position >= reminders.size()) {
            Toast.makeText(this, "Érvénytelen kiválasztás", Toast.LENGTH_SHORT).show();
            return;
        }

        Reminder selectedReminder = reminders.get(position);

        AppDatabase db = AppDatabase.getInstance(this);
        db.reminderDao().delete(selectedReminder);

        Toast.makeText(this, "Emlékeztető törölve", Toast.LENGTH_SHORT).show();
        loadReminders();
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = selectedYear + "-" +
                            String.format("%02d", selectedMonth + 1) + "-" +
                            String.format("%02d", selectedDay);

                    reminderDateInput.setText(date);
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }

}