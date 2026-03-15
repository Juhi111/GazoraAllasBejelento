package com.example.gazoraallasbejelento.ui.reading;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gazoraallasbejelento.R;
import com.example.gazoraallasbejelento.data.database.AppDatabase;
import com.example.gazoraallasbejelento.data.entity.Reading;
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.widget.Spinner;
import com.example.gazoraallasbejelento.data.entity.Meter;
import java.util.List;
import java.util.ArrayList;

public class NewReadingActivity extends AppCompatActivity {

    private EditText dateInput;
    private EditText meterValueInput;
    private EditText noteInput;
    private Button saveReadingButton;
    private int readingId = -1;
    private Spinner meterSpinner;
    private List<Meter> meters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reading);

        dateInput = findViewById(R.id.dateInput);
        meterValueInput = findViewById(R.id.meterValueInput);
        noteInput = findViewById(R.id.noteInput);
        saveReadingButton = findViewById(R.id.saveReadingButton);
        meterSpinner = findViewById(R.id.meterSpinner);

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

        loadMeters();

    }

    private void saveReading() {

        String date = dateInput.getText().toString().trim();
        String valueText = meterValueInput.getText().toString().trim();
        String note = noteInput.getText().toString().trim();

        if (date.isEmpty() || valueText.isEmpty()) {
            Toast.makeText(this, "Minden mezőt ki kell tölteni", Toast.LENGTH_SHORT).show();
            return;
        }

        if (meters == null || meters.isEmpty()) {
            Toast.makeText(this, "Nincs kiválasztható mérő", Toast.LENGTH_SHORT).show();
            return;
        }

        int value = Integer.parseInt(valueText);

        Meter selectedMeter = meters.get(meterSpinner.getSelectedItemPosition());
        int meterId = selectedMeter.getId();

        AppDatabase db = AppDatabase.getInstance(this);

        if (readingId == -1) {
            Reading reading = new Reading(meterId, date, value, note);
            db.readingDao().insert(reading);
            Toast.makeText(this, "Óraállás mentve", Toast.LENGTH_SHORT).show();
        } else {
            Reading reading = new Reading(meterId, date, value, note);
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

    private void loadMeters() {

        AppDatabase db = AppDatabase.getInstance(this);
        meters = db.meterDao().getAllMeters();

        List<String> meterTexts = new ArrayList<>();

        for (Meter meter : meters) {
            String text = "ID: " + meter.getId() + " - " + meter.getMeterNumber();
            meterTexts.add(text);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                meterTexts
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        meterSpinner.setAdapter(adapter);
    }
}