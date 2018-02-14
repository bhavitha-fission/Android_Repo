package com.example.bhavithagottimukkula.login_java.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bhavithagottimukkula.login_java.R;
import com.example.bhavithagottimukkula.login_java.common.Const;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ProgressBar mviewProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mviewProgressBar = (ProgressBar)findViewById(R.id.login_progress_bar);
        final EditText etUserName = findViewById(R.id.editText);
        final EditText etPassword = findViewById(R.id.editText2);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mviewProgressBar.setVisibility(View.VISIBLE);
                final String userName = etUserName.getText().toString();
                 String password = etPassword.getText().toString();
                if(userName.matches("") || password.matches("")) {

                    Toast.makeText(LoginActivity.this, "please enter all values",
                            Toast.LENGTH_SHORT).show();
                    mviewProgressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.signInWithEmailAndPassword(userName, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    Toast.makeText(LoginActivity.this, "Sign in success",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Intent intent = new Intent(LoginActivity.this, WelcomeUserActivity.class);
                                    intent.putExtra(Const.USER_NAME, userName);
                                    startActivity(intent);

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                 }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }



}
