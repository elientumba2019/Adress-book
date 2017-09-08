package com.example.elie.smartaddressbook.activities;

import android.support.annotation.LayoutRes;
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
        setContentView(getLayout());



        //gets the fragment manager
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.container);


        if(fragment == null){
            //method to be called by other activities
            fragment = getFragment();
            manager.beginTransaction().add(R.id.container , fragment).commit();
        }
    }






    /**
     * returns a layout that an activity decides to
     * inflate
     * @return
     */
    @LayoutRes
    protected int getLayout(){
        return R.layout.activity_main;
    }




    /**
     * returns a fragment from a certain activity that
     * will be set on screen
     * @return
     */
    protected abstract Fragment getFragment();
}
