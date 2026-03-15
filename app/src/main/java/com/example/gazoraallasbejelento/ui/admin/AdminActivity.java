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
    private List<User> users;

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

        userListView.setOnItemClickListener((parent, view, position, id) -> toggleUserRole(position));

        userListView.setOnItemLongClickListener((parent, view, position, id) -> {
            deleteUser(position);
            return true;
        });
    }

    private void loadUsers() {
        AppDatabase db = AppDatabase.getInstance(this);
        users = db.userDao().getAllUsers();

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

    private void deleteUser(int position) {
        if (users == null || position < 0 || position >= users.size()) {
            Toast.makeText(this, "Érvénytelen kiválasztás", Toast.LENGTH_SHORT).show();
            return;
        }

        User selectedUser = users.get(position);

        String loggedEmail = getIntent().getStringExtra("userEmail");

        if (selectedUser.getEmail().equals(loggedEmail)) {
            Toast.makeText(this, "Saját felhasználót nem törölheted", Toast.LENGTH_SHORT).show();
            return;
        }

        AppDatabase db = AppDatabase.getInstance(this);
        db.userDao().delete(selectedUser);

        Toast.makeText(this, "Felhasználó törölve", Toast.LENGTH_SHORT).show();
        loadUsers();
    }

    private void toggleUserRole(int position) {
        if (users == null || position < 0 || position >= users.size()) {
            Toast.makeText(this, "Érvénytelen kiválasztás", Toast.LENGTH_SHORT).show();
            return;
        }

        User selectedUser = users.get(position);

        String loggedEmail = getIntent().getStringExtra("userEmail");

        if (selectedUser.getEmail().equals(loggedEmail)) {
            Toast.makeText(this, "A saját szerepkörödet nem módosíthatod", Toast.LENGTH_SHORT).show();
            return;
        }

        if ("ADMIN".equals(selectedUser.getRole())) {
            selectedUser.setRole("USER");
        } else {
            selectedUser.setRole("ADMIN");
        }

        AppDatabase db = AppDatabase.getInstance(this);
        db.userDao().update(selectedUser);

        Toast.makeText(this, "Szerepkör módosítva: " + selectedUser.getRole(), Toast.LENGTH_SHORT).show();
        loadUsers();
    }
}