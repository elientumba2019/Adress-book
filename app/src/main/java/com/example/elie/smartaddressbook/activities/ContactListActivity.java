package com.example.elie.smartaddressbook.activities;

import android.support.v4.app.Fragment;

import com.example.elie.smartaddressbook.fragments.ContactListFragment;

/**
 * Created by elie on 17-8-5.
 */


/**
 * crime list activity
 */
public class ContactListActivity extends MotherSingleActivity {


    /**
     * returns a fragment holding a list of contact
     * @return
     */
    protected Fragment getFragment(){
        return new ContactListFragment();
    }
}
