package com.example.gazoraallasbejelento.ui.reading;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gazoraallasbejelento.R;

public class NewReadingActivity extends AppCompatActivity {

    private Button saveReadingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reading);

        saveReadingButton = findViewById(R.id.saveReadingButton);

        saveReadingButton.setOnClickListener(v ->
                Toast.makeText(this, "Mentés később lesz bekötve", Toast.LENGTH_SHORT).show()
        );
    }
}