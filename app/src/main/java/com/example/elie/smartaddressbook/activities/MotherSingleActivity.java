package com.example.elie.smartaddressbook.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.elie.smartaddressbook.R;


/**
 * class to be extended bu other classes following the sam convention
 */
public abstract class MotherSingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //gets the fragment manager
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.container);


        if(fragment == null){
            //method to be called by other activities
            fragment = getFragment();
            manager.beginTransaction().add(R.id.container , fragment).commit();
        }
    }




    protected abstract Fragment getFragment();
}
