package com.example.bhavithagottimukkula.login_java.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bhavithagottimukkula.login_java.fragment.HomeFragment;
import com.example.bhavithagottimukkula.login_java.R;
import com.example.bhavithagottimukkula.login_java.common.Const;

public class WelcomeUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);

        Intent intent = getIntent();
        String userName = intent.getStringExtra(Const.USER_NAME);

        Bundle bundle = new Bundle();
        bundle.putString(Const.USER_NAME, userName);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);

        // Loading Fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,homeFragment);
        fragmentTransaction.commit();


    }

}
