package com.example.gazoraallasbejelento.ui.reading;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gazoraallasbejelento.R;
import com.example.gazoraallasbejelento.data.database.AppDatabase;
import com.example.gazoraallasbejelento.data.entity.Reading;

import java.util.ArrayList;
import java.util.List;

public class ReadingListActivity extends AppCompatActivity {

    private ListView readingListView;
    private List<Reading> readings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_list);

        readingListView = findViewById(R.id.readingListView);

        loadReadings();
    }

    private void loadReadings() {
        AppDatabase db = AppDatabase.getInstance(this);
        readings = db.readingDao().getAllReadings();

        List<String> readingTexts = new ArrayList<>();

        for (Reading reading : readings) {
            String text = reading.getDate()
                    + " - "
                    + reading.getValue()
                    + " m3 | Megjegyzés: "
                    + reading.getNote();
            readingTexts.add(text);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                readingTexts
        );

        readingListView.setAdapter(adapter);

        readingListView.setOnItemClickListener((parent, view, position, id) -> {
            Reading selectedReading = readings.get(position);

            Intent intent = new Intent(ReadingListActivity.this, ReadingDetailActivity.class);
            intent.putExtra("date", selectedReading.getDate());
            intent.putExtra("value", selectedReading.getValue());
            intent.putExtra("note", selectedReading.getNote());
            startActivity(intent);
        });
    }
}