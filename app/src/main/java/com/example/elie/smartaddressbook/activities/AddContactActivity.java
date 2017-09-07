package com.example.elie.smartaddressbook.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.elie.smartaddressbook.fragments.AddContactFragment;

/**
 * Created by elie on 17-8-18.
 */



/**
 * activity to add a new contact to the list
 */
public class AddContactActivity extends MotherSingleActivity {




    /**
     * return sthe activity's corresponding fragment
     * @return
     */
    @Override
    protected Fragment getFragment() {
        return new AddContactFragment();
    }




    /**
     * returns an intent that will be used by some actity
     * to open this
     * @param context
     * @return
     */
    public static Intent getActivityIntent(Context context){
        Intent intent = new Intent(context , AddContactActivity.class);
        return  intent;
    }
}
