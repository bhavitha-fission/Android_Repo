package com.fission.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.fission.R;
import com.fission.common.Const;
import com.fission.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
