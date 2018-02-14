package com.fission.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.fission.R;
import com.fission.common.Const;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        setTitle("oye");
        Intent intent = getIntent();
        String message = intent.getStringExtra(Const.USER_NAME);
        EditText editText = findViewById(R.id.editText3);
        if (!message.isEmpty()) {
            editText.setText("Welcome " + message);
        }
    }
}
