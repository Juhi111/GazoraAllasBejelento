package com.example.gazoraallasbejelento.ui.admin;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gazoraallasbejelento.R;
import com.example.gazoraallasbejelento.data.database.AppDatabase;
import com.example.gazoraallasbejelento.data.entity.User;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private Button manageUsersButton;
    private Button manageMetersButton;
    private Button managePropertiesButton;
    private ListView userListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userRole = getIntent().getStringExtra("userRole");

        if (userRole == null || !userRole.equals("ADMIN")) {
            finish();
            return;
        }

        setContentView(R.layout.activity_admin);

        manageUsersButton = findViewById(R.id.manageUsersButton);
        manageMetersButton = findViewById(R.id.manageMetersButton);
        managePropertiesButton = findViewById(R.id.managePropertiesButton);
        userListView = findViewById(R.id.userListView);

        manageUsersButton.setOnClickListener(v -> loadUsers());

        manageMetersButton.setOnClickListener(v ->
                Toast.makeText(this, "Mérő kezelés később lesz implementálva", Toast.LENGTH_SHORT).show()
        );

        managePropertiesButton.setOnClickListener(v ->
                Toast.makeText(this, "Fogyasztási hely kezelés később lesz implementálva", Toast.LENGTH_SHORT).show()
        );
    }

    private void loadUsers() {
        AppDatabase db = AppDatabase.getInstance(this);
        List<User> users = db.userDao().getAllUsers();

        List<String> userTexts = new ArrayList<>();

        for (User user : users) {
            String text = user.getName() + " - " + user.getEmail() + " - " + user.getRole();
            userTexts.add(text);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                userTexts
        );

        userListView.setAdapter(adapter);
    }
}