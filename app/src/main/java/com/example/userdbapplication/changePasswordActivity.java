package com.example.userdbapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class changePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        EditText changeUsernameEditText = findViewById(R.id.changeUsernameEditText);
        EditText changePasswordEditText = findViewById(R.id.changePasswordEditText);
        EditText confirmChangePasswordEditText = findViewById(R.id.confirmChangePasswordEditText);
        Button changePasswordButton = findViewById(R.id.cChangePasswordButton);

        DatabaseHelper db = new DatabaseHelper(this);

        changePasswordButton.setOnClickListener(view -> {
            String username = changeUsernameEditText.getText().toString();
            String password = changePasswordEditText.getText().toString();
            String confirmPassword = confirmChangePasswordEditText.getText().toString();

            if (password.equals(confirmPassword)) {
                User user = new User(username, password);
                int result = db.updatePassword(user);
                if (result == 2) {
                    Toast.makeText(this, "Successfully changed password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Password can't be changed ERROR " + result, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            }
        });
    }
}