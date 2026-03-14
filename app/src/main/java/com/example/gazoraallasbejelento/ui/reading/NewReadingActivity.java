package com.example.gazoraallasbejelento.ui.reading;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gazoraallasbejelento.R;
import com.example.gazoraallasbejelento.data.database.AppDatabase;
import com.example.gazoraallasbejelento.data.entity.Reading;
import android.app.DatePickerDialog;
import java.util.Calendar;

public class NewReadingActivity extends AppCompatActivity {

    private EditText dateInput;
    private EditText meterValueInput;
    private EditText noteInput;
    private Button saveReadingButton;
    private int readingId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reading);

        dateInput = findViewById(R.id.dateInput);
        meterValueInput = findViewById(R.id.meterValueInput);
        noteInput = findViewById(R.id.noteInput);
        saveReadingButton = findViewById(R.id.saveReadingButton);

        readingId = getIntent().getIntExtra("readingId", -1);

        String date = getIntent().getStringExtra("date");
        int value = getIntent().getIntExtra("value", 0);
        String note = getIntent().getStringExtra("note");

        if (readingId != -1) {
            dateInput.setText(date);
            meterValueInput.setText(String.valueOf(value));
            noteInput.setText(note);
            saveReadingButton.setText("Módosítás mentése");
        }

        saveReadingButton.setOnClickListener(v -> saveReading());
        dateInput.setOnClickListener(v -> showDatePicker());

    }

    private void saveReading() {

        String date = dateInput.getText().toString().trim();
        String valueText = meterValueInput.getText().toString().trim();
        String note = noteInput.getText().toString().trim();

        if (date.isEmpty() || valueText.isEmpty()) {
            Toast.makeText(this, "Minden mezőt ki kell tölteni", Toast.LENGTH_SHORT).show();
            return;
        }

        int value = Integer.parseInt(valueText);

        AppDatabase db = AppDatabase.getInstance(this);

        if (readingId == -1) {
            Reading reading = new Reading(1, date, value, note);
            db.readingDao().insert(reading);
            Toast.makeText(this, "Óraállás mentve", Toast.LENGTH_SHORT).show();
        } else {
            Reading reading = new Reading(1, date, value, note);
            reading.setId(readingId);
            db.readingDao().update(reading);
            Toast.makeText(this, "Óraállás módosítva", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(NewReadingActivity.this, ReadingListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
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

                    dateInput.setText(date);
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }
}