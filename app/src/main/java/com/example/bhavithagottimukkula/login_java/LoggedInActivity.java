package com.example.bhavithagottimukkula.login_java;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class  LoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        setTitle("Welcome");
        Intent intent = getIntent();
        String message = intent.getStringExtra(LoginActivity.userName);
        EditText editText = findViewById(R.id.editText3);
        if(!message.isEmpty()) {
            editText.setText("Welcome " + message);
        }
    }
}
