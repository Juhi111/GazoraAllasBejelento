package com.example.gazoraallasbejelento.ui.reading;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gazoraallasbejelento.R;
import com.example.gazoraallasbejelento.data.database.AppDatabase;
import com.example.gazoraallasbejelento.data.entity.Reading;
import com.example.gazoraallasbejelento.data.entity.Meter;
import java.util.ArrayList;
import java.util.List;
import android.widget.Button;
import java.util.Collections;
import java.util.Comparator;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ProgressBar;

public class ReadingListActivity extends AppCompatActivity {

    private ListView readingListView;
    private List<Reading> readings;
    private EditText searchInput;
    private ArrayAdapter<String> adapter;
    private Button sortButton;
    private boolean sortAscending = true;
    private Spinner filterMeterSpinner;
    private List<Meter> meters;
    private int selectedMeterId = -1;
    private TextView emptyStateText;
    private ProgressBar loadingProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_list);

        readingListView = findViewById(R.id.readingListView);
        searchInput = findViewById(R.id.searchInput);
        sortButton = findViewById(R.id.sortButton);
        filterMeterSpinner = findViewById(R.id.filterMeterSpinner);
        emptyStateText = findViewById(R.id.emptyStateText);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);

        loadReadings();

        sortButton.setOnClickListener(v -> {

            if (readings == null) return;

            if (sortAscending) {
                Collections.sort(readings, Comparator.comparingInt(Reading::getValue));
                sortButton.setText("Rendezés csökkenőre");
            } else {
                Collections.sort(readings, Comparator.comparingInt(Reading::getValue).reversed());
                sortButton.setText("Rendezés növekvőre");
            }

            sortAscending = !sortAscending;

            refreshReadingList();
        });

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null) {
                    adapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        loadMeters();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadReadings();
    }

    private void loadReadings() {
        AppDatabase db = AppDatabase.getInstance(this);
        readings = db.readingDao().getAllReadings();

        loadingProgressBar.setVisibility(View.VISIBLE);
        readingListView.setVisibility(View.GONE);
        emptyStateText.setVisibility(View.GONE);

        List<String> readingTexts = new ArrayList<>();

        for (Reading reading : readings) {
            if (selectedMeterId != -1 && reading.getMeterId() != selectedMeterId) {
                continue;
            }
            Meter meter = db.meterDao().getAllMeters()
                    .stream()
                    .filter(m -> m.getId() == reading.getMeterId())
                    .findFirst()
                    .orElse(null);

            String meterText = (meter != null) ? meter.getMeterNumber() : "ismeretlen mérő";

            String text = "Mérő: " + meterText
                    + " | Dátum: " + reading.getDate()
                    + " | Állás: " + reading.getValue() + " m3"
                    + " | Megjegyzés: " + reading.getNote();

            readingTexts.add(text);
        }

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                readingTexts
        );

        if (readingTexts.isEmpty()) {
            readingListView.setVisibility(View.GONE);
            emptyStateText.setVisibility(View.VISIBLE);
        } else {
            readingListView.setVisibility(View.VISIBLE);
            emptyStateText.setVisibility(View.GONE);
        }

        readingListView.setAdapter(adapter);

        readingListView.setOnItemClickListener((parent, view, position, id) -> {
            Reading selectedReading = readings.get(position);

            Intent intent = new Intent(ReadingListActivity.this, ReadingDetailActivity.class);
            intent.putExtra("readingId", selectedReading.getId());
            intent.putExtra("date", selectedReading.getDate());
            intent.putExtra("value", selectedReading.getValue());
            intent.putExtra("note", selectedReading.getNote());
            intent.putExtra("meterId", selectedReading.getMeterId());
            startActivity(intent);
        });

        loadingProgressBar.setVisibility(View.GONE);
    }

    private void refreshReadingList() {
        AppDatabase db = AppDatabase.getInstance(this);

        List<String> readingTexts = new ArrayList<>();

        for (Reading reading : readings) {
            String meterText = "ismeretlen mérő";

            List<Meter> allMeters = db.meterDao().getAllMeters();
            for (Meter meter : allMeters) {
                if (meter.getId() == reading.getMeterId()) {
                    meterText = meter.getMeterNumber();
                    break;
                }
            }

            String text = "Mérő: " + meterText
                    + " | Dátum: " + reading.getDate()
                    + " | Állás: " + reading.getValue() + " m3"
                    + " | Megjegyzés: " + reading.getNote();

            readingTexts.add(text);
        }

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                readingTexts
        );

        readingListView.setAdapter(adapter);
    }

    private void loadMeters() {

        AppDatabase db = AppDatabase.getInstance(this);
        meters = db.meterDao().getAllMeters();

        List<String> meterTexts = new ArrayList<>();
        meterTexts.add("Összes mérő");

        for (Meter meter : meters) {
            meterTexts.add(meter.getMeterNumber());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                meterTexts
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        filterMeterSpinner.setAdapter(adapter);

        filterMeterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    selectedMeterId = -1;
                } else {
                    selectedMeterId = meters.get(position - 1).getId();
                }

                loadReadings();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}