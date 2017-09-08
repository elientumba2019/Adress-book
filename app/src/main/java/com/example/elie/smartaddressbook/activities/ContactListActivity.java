package com.example.elie.smartaddressbook.activities;

import android.support.v4.app.Fragment;

import com.example.elie.smartaddressbook.R;
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



    /**
     * will return an alias ressource ID that will be used
     * to define which layout the application is supposed tp take depending on
     * whether the user is running the application on a phone
     * or on a tablet
     * the method will return an id belonging to an alias ressource
     * @return
     */
    @Override
    protected int getLayout() {
        //returning an alias resource
        return R.layout.layout_to_put_on_screen;
    }
}
