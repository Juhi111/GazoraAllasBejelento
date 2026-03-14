package com.example.gazoraallasbejelento.ui.reading;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gazoraallasbejelento.R;

public class ReadingDetailActivity extends AppCompatActivity {

    private Button editButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_detail);

        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);

        editButton.setOnClickListener(v ->
                Toast.makeText(this, "A szerkesztés később lesz bekötve", Toast.LENGTH_SHORT).show()
        );

        deleteButton.setOnClickListener(v ->
                Toast.makeText(this, "A törlés később lesz bekötve", Toast.LENGTH_SHORT).show()
        );
    }
}