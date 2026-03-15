package com.example.gazoraallasbejelento.ui.admin;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gazoraallasbejelento.R;
import com.example.gazoraallasbejelento.data.database.AppDatabase;
import com.example.gazoraallasbejelento.data.entity.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyActivity extends AppCompatActivity {

    private EditText propertyNameInput;
    private EditText propertyAddressInput;
    private Button savePropertyButton;
    private ListView propertyListView;
    private List<Property> properties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        propertyNameInput = findViewById(R.id.propertyNameInput);
        propertyAddressInput = findViewById(R.id.propertyAddressInput);
        savePropertyButton = findViewById(R.id.savePropertyButton);
        propertyListView = findViewById(R.id.propertyListView);

        savePropertyButton.setOnClickListener(v -> saveProperty());

        propertyListView.setOnItemLongClickListener((parent, view, position, id) -> {
            deleteProperty(position);
            return true;
        });

        loadProperties();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProperties();
    }

    private void saveProperty() {
        String name = propertyNameInput.getText().toString().trim();
        String address = propertyAddressInput.getText().toString().trim();

        if (name.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Minden mezőt ki kell tölteni", Toast.LENGTH_SHORT).show();
            return;
        }

        Property property = new Property(1, name, address);

        AppDatabase db = AppDatabase.getInstance(this);
        db.propertyDao().insert(property);

        Toast.makeText(this, "Fogyasztási hely mentve", Toast.LENGTH_SHORT).show();

        propertyNameInput.setText("");
        propertyAddressInput.setText("");

        loadProperties();
    }

    private void loadProperties() {
        AppDatabase db = AppDatabase.getInstance(this);
        properties = db.propertyDao().getAllProperties();

        List<String> propertyTexts = new ArrayList<>();

        for (Property property : properties) {
            String text = property.getName() + " - " + property.getAddress();
            propertyTexts.add(text);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                propertyTexts
        );

        propertyListView.setAdapter(adapter);
    }

    private void deleteProperty(int position) {
        if (properties == null || position < 0 || position >= properties.size()) {
            Toast.makeText(this, "Érvénytelen kiválasztás", Toast.LENGTH_SHORT).show();
            return;
        }

        Property selectedProperty = properties.get(position);

        AppDatabase db = AppDatabase.getInstance(this);
        db.propertyDao().delete(selectedProperty);

        Toast.makeText(this, "Fogyasztási hely törölve", Toast.LENGTH_SHORT).show();
        loadProperties();
    }
}