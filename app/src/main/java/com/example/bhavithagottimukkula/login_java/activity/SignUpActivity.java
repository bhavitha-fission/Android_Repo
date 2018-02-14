package com.example.bhavithagottimukkula.login_java.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bhavithagottimukkula.login_java.R;
import com.example.bhavithagottimukkula.login_java.db.DataBaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ProgressBar mviewProgressBar;
    DataBaseHelper mDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registartion");
        setContentView(R.layout.activity_sign_up);
        mDataBaseHelper = new DataBaseHelper(this);
        mviewProgressBar = findViewById(R.id.signup_progress_bar);
        final EditText etUsername = findViewById(R.id.userName);
        final EditText etEmail = findViewById(R.id.et_email);
        final EditText etPassword = findViewById(R.id.password);

        findViewById(R.id.signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(etEmail.getText().toString().matches("") || etPassword.getText().toString().matches("") || etUsername.getText().toString().matches("")) {
                  Toast.makeText(SignUpActivity.this,"please enter all values",Toast.LENGTH_SHORT).show();
                  return;
               }
                insertData(etUsername.getText().toString(),etEmail.getText().toString(),etPassword.getText().toString());
                mviewProgressBar.setVisibility(view.VISIBLE);
                mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Registration successfull", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(SignUpActivity.this, WelcomeUserActivity.class);
                                    startActivity(intent);
                                } else {
                                    Log.e("Error", task.getException().toString());
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }

    public void insertData(String name,String email,String Password) {

        boolean result = mDataBaseHelper.insertDataIntoDataBase(name,email,Password);

        if(result == true ){
            displayToastMessage("Data saved in database!");
        }
        else {
            displayToastMessage("Data not saved !");
        }
    }

    public void displayToastMessage(String msg) {

        Toast.makeText(SignUpActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}
