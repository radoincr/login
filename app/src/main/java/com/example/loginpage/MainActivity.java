package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpage.R;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    int loginAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginAttempts = 0;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(MainActivity.this,calculator.class);
                    startActivity(i);
                    loginAttempts = 0; // reset the counter after successful login
                } else {
                    loginAttempts++;
                    if (loginAttempts >= 5) {
                        loginButton.setEnabled(false); // disable the button
                        Toast.makeText(MainActivity.this, "Too many failed attempts. Try again later.", Toast.LENGTH_SHORT).show();

                        // re-enable the button after 5 minutes
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loginButton.setEnabled(true);
                                loginAttempts = 0; // reset the counter
                            }
                        }, 5000); // 300000 milliseconds = 5 minutes
                    } else {
                        Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}