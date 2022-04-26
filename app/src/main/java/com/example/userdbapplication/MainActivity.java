package com.example.userdbapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        Button signupButton = findViewById(R.id.signupButton);
        Button showUsersButton = findViewById(R.id.showUsersButton);
        Button changePasswordButton = findViewById(R.id.changePasswordButton);

        DatabaseHelper db = new DatabaseHelper(this);

        signupButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SignupActivity.class)));

        loginButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            boolean result = db.fetchUser(username, password);
            if (result) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });

        showUsersButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ShowUsersActivity.class)));

        changePasswordButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, changePasswordActivity.class)));
    }
}