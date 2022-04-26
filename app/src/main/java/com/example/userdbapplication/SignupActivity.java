package com.example.userdbapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText sUsernameEditText = findViewById(R.id.sUsernameEditText);
        EditText sPasswordEditText = findViewById(R.id.sPasswordEditText);
        EditText sConfirmPasswordEditText = findViewById(R.id.sConfirmPasswordEditText);
        Button sSignupButton = findViewById(R.id.sSignupButton);

        DatabaseHelper db = new DatabaseHelper(this);

        sSignupButton.setOnClickListener(view -> {
            String username = sUsernameEditText.getText().toString();
            String password = sPasswordEditText.getText().toString();
            String confirmPassword = sConfirmPasswordEditText.getText().toString();

            if (password.equals(confirmPassword)) {
                User user = new User(username, password);
                long result = db.insertUser(user);
                if (result > 0) {
                    Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(this, "Signup failed", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            }
        });
    }
}