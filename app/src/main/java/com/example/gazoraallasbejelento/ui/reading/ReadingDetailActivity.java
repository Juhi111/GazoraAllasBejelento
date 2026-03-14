package com.example.gazoraallasbejelento.ui.reading;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gazoraallasbejelento.R;

public class ReadingDetailActivity extends AppCompatActivity {

    private TextView dateText;
    private TextView meterValueText;
    private TextView noteText;
    private Button editButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_detail);

        dateText = findViewById(R.id.dateText);
        meterValueText = findViewById(R.id.meterValueText);
        noteText = findViewById(R.id.noteText);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);

        String date = getIntent().getStringExtra("date");
        int value = getIntent().getIntExtra("value", 0);
        String note = getIntent().getStringExtra("note");

        dateText.setText("Dátum: " + date);
        meterValueText.setText("Óraállás: " + value + " m3");
        noteText.setText("Megjegyzés: " + note);

        editButton.setOnClickListener(v ->
                Toast.makeText(this, "A szerkesztés később lesz bekötve", Toast.LENGTH_SHORT).show()
        );

        deleteButton.setOnClickListener(v ->
                Toast.makeText(this, "A törlés később lesz bekötve", Toast.LENGTH_SHORT).show()
        );
    }
}