package com.example.bhavithagottimukkula.login_java;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public static final String userName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button   = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,LoggedInActivity.class);
                EditText editText = (EditText) findViewById(R.id.editText);
                String userNameString = editText.getText().toString();
                intent.putExtra(userName,userNameString);
                startActivity(intent);
            }
        });

        Button signUpButton = (Button) findViewById(R.id.button2);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

}
