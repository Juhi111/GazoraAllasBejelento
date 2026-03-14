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
    }

    private void saveReading() {

        String date = dateInput.getText().toString();
        String valueText = meterValueInput.getText().toString();
        String note = noteInput.getText().toString();

        if (date.isEmpty() || valueText.isEmpty()) {
            Toast.makeText(this, "Minden mezőt ki kell tölteni", Toast.LENGTH_SHORT).show();
            return;
        }

        int value = Integer.parseInt(valueText);

        Reading reading = new Reading(
                1,
                date,
                value,
                note
        );

        AppDatabase db = AppDatabase.getInstance(this);
        db.readingDao().insert(reading);

        Toast.makeText(this, "Óraállás mentve", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(NewReadingActivity.this, ReadingListActivity.class);
        startActivity(intent);
        finish();

    }
}