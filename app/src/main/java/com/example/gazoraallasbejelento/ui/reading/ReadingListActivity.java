package com.example.gazoraallasbejelento.ui.reading;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gazoraallasbejelento.R;

public class ReadingListActivity extends AppCompatActivity {

    private ListView readingListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_list);

        readingListView = findViewById(R.id.readingListView);

        String[] demoReadings = {
                "2026-03-01 - 1234 m3",
                "2026-02-01 - 1198 m3",
                "2026-01-01 - 1160 m3"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                demoReadings
        );

        readingListView.setAdapter(adapter);

        readingListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ReadingListActivity.this, ReadingDetailActivity.class);
            startActivity(intent);
        });
    }
}