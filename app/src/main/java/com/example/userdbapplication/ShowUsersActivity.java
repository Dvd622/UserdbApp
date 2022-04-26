package com.example.userdbapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);

        ListView userListView = findViewById(R.id.userListView);

        DatabaseHelper db = new DatabaseHelper(this);
        List<User> userList = db.fetchAllUser();

        ArrayList<String> usernameList = new ArrayList<>();

        for (User user:userList) {
            usernameList.add(user.getUsername());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, usernameList);
        userListView.setAdapter(adapter);
    }
}