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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ProgressBar mViewProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.LogIn));
        setContentView(R.layout.activity_login);

        mViewProgressBar = (ProgressBar) findViewById(R.id.login_progress_bar);
        final EditText etUserName = findViewById(R.id.editText);
        final EditText etPassword = findViewById(R.id.editText2);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.log_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        findViewById(R.id.sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

public void signIn() {

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
                        FirebaseUser user = mAuth.getCurrentUser();
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

    public void displayToastMessage(String msg) {

        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

}
