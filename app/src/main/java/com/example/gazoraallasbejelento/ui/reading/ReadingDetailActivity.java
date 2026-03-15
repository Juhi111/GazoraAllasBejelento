package com.example.gazoraallasbejelento.ui.reading;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gazoraallasbejelento.R;
import com.example.gazoraallasbejelento.data.database.AppDatabase;
import com.example.gazoraallasbejelento.data.entity.Reading;
import com.example.gazoraallasbejelento.data.entity.Meter;
import java.util.List;
import com.example.gazoraallasbejelento.data.database.AppDatabase;

public class ReadingDetailActivity extends AppCompatActivity {

    private TextView dateText;
    private TextView meterValueText;
    private TextView noteText;
    private Button editButton;
    private Button deleteButton;
    private int readingId;
    private TextView meterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_detail);

        dateText = findViewById(R.id.dateText);
        meterValueText = findViewById(R.id.meterValueText);
        noteText = findViewById(R.id.noteText);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);
        meterText = findViewById(R.id.meterText);

        readingId = getIntent().getIntExtra("readingId", -1);
        String date = getIntent().getStringExtra("date");
        int value = getIntent().getIntExtra("value", 0);
        String note = getIntent().getStringExtra("note");
        int meterId = getIntent().getIntExtra("meterId", -1);

        String meterNumberText = "ismeretlen mérő";

        if (meterId != -1) {
            AppDatabase db = AppDatabase.getInstance(this);
            List<Meter> meters = db.meterDao().getAllMeters();

            for (Meter meter : meters) {
                if (meter.getId() == meterId) {
                    meterNumberText = meter.getMeterNumber();
                    break;
                }
            }
        }

        meterText.setText("Mérő: " + meterNumberText);

        dateText.setText("Dátum: " + date);
        meterValueText.setText("Óraállás: " + value + " m3");
        noteText.setText("Megjegyzés: " + note);

        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(ReadingDetailActivity.this, NewReadingActivity.class);
            intent.putExtra("readingId", readingId);
            intent.putExtra("date", getIntent().getStringExtra("date"));
            intent.putExtra("value", getIntent().getIntExtra("value", 0));
            intent.putExtra("note", getIntent().getStringExtra("note"));
            startActivity(intent);
        });


        deleteButton.setOnClickListener(v -> deleteReading());
    }

    private void deleteReading() {
        if (readingId == -1) {
            Toast.makeText(this, "Hibás azonosító", Toast.LENGTH_SHORT).show();
            return;
        }

        AppDatabase db = AppDatabase.getInstance(this);
        Reading reading = db.readingDao().getReadingById(readingId);

        if (reading != null) {
            db.readingDao().delete(reading);
            Toast.makeText(this, "Óraállás törölve", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ReadingDetailActivity.this, ReadingListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "A rekord nem található", Toast.LENGTH_SHORT).show();
        }
    }
}
