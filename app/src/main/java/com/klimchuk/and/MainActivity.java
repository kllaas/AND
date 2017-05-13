package com.klimchuk.and;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showMapsFragment();
    }

    private void showMapsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        MapsFragment fragment = MapsFragment.newInstance();
        ft.add(R.id.maps_container, fragment);
        ft.commit();
    }
}
