package com.fission.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.fission.R;
import com.fission.fragment.HomeFragment;
import com.fission.fragment.ProfileFragment;
import com.fission.fragment.SettingsFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.home));
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        loadFragment(R.string.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                displayToastMessage(getString(R.string.home));
                                loadFragment(R.string.home);
                                break;
                            case R.id.action_profile:
                                displayToastMessage(getString(R.string.profile));
                                loadFragment(R.string.profile);
                                break;
                            case R.id.action_settings:
                                displayToastMessage(getString(R.string.settings));
                                loadFragment(R.string.settings);
                                break;
                        }
                        return true;
                    }
                });

    }

    private void displayToastMessage(String msg) {

        Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void loadFragment(int id) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;

        switch (id) {
            case R.string.home:
                fragment = new HomeFragment();
                break;
            case R.string.profile:
                fragment = new ProfileFragment();
                break;
            case R.string.settings:
                fragment = new SettingsFragment();
                break;
        }

        if (fragment != null) {
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }
}
