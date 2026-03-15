package com.example.gazoraallasbejelento.ui.admin;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Spinner;
import com.example.gazoraallasbejelento.data.entity.Property;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gazoraallasbejelento.R;
import com.example.gazoraallasbejelento.data.database.AppDatabase;
import com.example.gazoraallasbejelento.data.entity.Meter;
import java.util.ArrayList;
import java.util.List;

public class MeterActivity extends AppCompatActivity {

    private EditText meterSerialInput;
    private EditText meterPropertyIdInput;
    private Button saveMeterButton;
    private ListView meterListView;
    private List<Meter> meters;
    private Spinner propertySpinner;
    private List<Property> properties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter);

        meterSerialInput = findViewById(R.id.meterSerialInput);
        propertySpinner = findViewById(R.id.propertySpinner);
        saveMeterButton = findViewById(R.id.saveMeterButton);
        meterListView = findViewById(R.id.meterListView);

        saveMeterButton.setOnClickListener(v -> saveMeter());

        meterListView.setOnItemLongClickListener((parent, view, position, id) -> {
            deleteMeter(position);
            return true;
        });

        loadProperties();
        loadMeters();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMeters();
    }

    private void saveMeter() {
        String serial = meterSerialInput.getText().toString().trim();
        if (serial.isEmpty()) {
            Toast.makeText(this, "A mérő gyári számát ki kell tölteni", Toast.LENGTH_SHORT).show();
            return;
        }

        if (properties == null || properties.isEmpty()) {
            Toast.makeText(this, "Nincs kiválasztható fogyasztási hely", Toast.LENGTH_SHORT).show();
            return;
        }

        Property selectedProperty = properties.get(propertySpinner.getSelectedItemPosition());
        int propertyId = selectedProperty.getId();

        Meter meter = new Meter(propertyId, serial, "gas");

        AppDatabase db = AppDatabase.getInstance(this);
        db.meterDao().insert(meter);

        Toast.makeText(this, "Mérő mentve", Toast.LENGTH_SHORT).show();

        meterSerialInput.setText("");

        loadMeters();
    }

    private void loadMeters() {
        AppDatabase db = AppDatabase.getInstance(this);
        meters = db.meterDao().getAllMeters();

        List<String> meterTexts = new ArrayList<>();

        for (Meter meter : meters) {
            String text = "ID: " + meter.getId()
                    + " | Property ID: " + meter.getPropertyId()
                    + " | Gyári szám: " + meter.getMeterNumber();
            meterTexts.add(text);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                meterTexts
        );

        meterListView.setAdapter(adapter);
    }

    private void deleteMeter(int position) {
        if (meters == null || position < 0 || position >= meters.size()) {
            Toast.makeText(this, "Érvénytelen kiválasztás", Toast.LENGTH_SHORT).show();
            return;
        }

        Meter selectedMeter = meters.get(position);

        AppDatabase db = AppDatabase.getInstance(this);
        db.meterDao().delete(selectedMeter);

        Toast.makeText(this, "Mérő törölve", Toast.LENGTH_SHORT).show();
        loadMeters();
    }

    private void loadProperties() {
        AppDatabase db = AppDatabase.getInstance(this);
        properties = db.propertyDao().getAllProperties();

        List<String> propertyTexts = new ArrayList<>();

        for (Property property : properties) {
            String text = property.getId() + " - " + property.getName() + " - " + property.getAddress();
            propertyTexts.add(text);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                propertyTexts
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        propertySpinner.setAdapter(adapter);
    }
}