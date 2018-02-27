package com.fission.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fission.R;
import com.fission.common.Const;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private ProgressBar mViewProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.LogIn));
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.log_in).setOnClickListener(this);
        findViewById(R.id.sign_up).setOnClickListener(this);
        findViewById(R.id.forgotPassword).setOnClickListener(this);
    }

private void signIn() {

    mViewProgressBar = (ProgressBar) findViewById(R.id.login_progress_bar);
    final EditText etUserName = findViewById(R.id.editText);
    final EditText etPassword = findViewById(R.id.editText2);

    mViewProgressBar.setVisibility(View.VISIBLE);
    final String userName = etUserName.getText().toString();
    String password = etPassword.getText().toString();
    if (userName.matches("") || password.matches("")) {

        displayToastMessage(getString(R.string.enter_all_values));
        mViewProgressBar.setVisibility(View.GONE);
        return;
    }

    mAuth.signInWithEmailAndPassword(userName, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        mViewProgressBar.setVisibility(View.GONE);
                        displayToastMessage(getString(R.string.sign_in_success));
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra(Const.USER_NAME, userName);
                        startActivity(intent);

                    } else {
                        // If sign in fails, display a message to the user.
                        mViewProgressBar.setVisibility(View.GONE);
                        displayToastMessage(getString(R.string.authentication_failed));

                    }
                }
            });
}

    private void displayToastMessage(String msg) {

        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.forgotPassword :
                Intent i = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(i);
                break;
            case R.id.sign_up :
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.log_in :
                signIn();
                break;
        }
    }
}
