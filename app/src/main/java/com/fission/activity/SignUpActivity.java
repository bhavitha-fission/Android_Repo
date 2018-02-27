package com.fission.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fission.R;
import com.fission.db.DataBaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private ProgressBar mViewProgressBar;
    private DataBaseHelper mDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.registration));
        setContentView(R.layout.activity_sign_up);
        mDataBaseHelper = new DataBaseHelper(this);
        mViewProgressBar = findViewById(R.id.signup_progress_bar);
        final EditText etUsername = findViewById(R.id.userName);
        final EditText etEmail = findViewById(R.id.et_email);
        final EditText etPassword = findViewById(R.id.password);

        findViewById(R.id.signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signUp();
            }
        });
    }

    private void insertDataIntoDB(String name, String email, String Password) {

        boolean result = mDataBaseHelper.insertDataIntoDataBase(name, email, Password);

        if (result) {
            displayToastMessage(getString(R.string.data_saved));
        } else {
            displayToastMessage(getString(R.string.data_not_saved));
        }
    }

    private void displayToastMessage(String msg) {
        Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    private void signUp() {
        mDataBaseHelper = new DataBaseHelper(this);
        mViewProgressBar = findViewById(R.id.signup_progress_bar);
        final EditText etUsername = findViewById(R.id.userName);
        final EditText etEmail = findViewById(R.id.et_email);
        final EditText etPassword = findViewById(R.id.password);

        if (TextUtils.isEmpty(etEmail.getText())
                || TextUtils.isEmpty(etPassword.getText())
                || TextUtils.isEmpty(etUsername.getText())) {

            displayToastMessage(getString(R.string.plese_enter_all_values));
            return;
        }

        insertDataIntoDB(etUsername.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
        mViewProgressBar.setVisibility(View.VISIBLE);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            displayToastMessage(getString(R.string.registration_successful));
                            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            displayToastMessage(task.getException().getMessage());
                        }
                    }
                });
    }
}

