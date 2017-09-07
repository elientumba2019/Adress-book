package com.example.elie.smartaddressbook.activities;

/**
 * Created by ntumba on 17-9-2.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.elie.smartaddressbook.fragments.EditContactFragment;

import java.util.UUID;

/**
 * opens an activity which is used to
 * modify a contact
 */
public class EditContactActivity extends MotherSingleActivity {


    private static String CONTACT_ID_KEY = "contact key";




    /**
     * returns a fragment that is used to display on
     * Screen and tralala
     * @return
     */
    @Override
    protected Fragment getFragment() {
        UUID uuid = (UUID) getIntent().getSerializableExtra(CONTACT_ID_KEY);
        return EditContactFragment.getInstance(uuid);
    }





    /**
     * returns an intent that will be used by an
     * activity to open this one
     * @param context
     * @return
     */
    public static Intent getActivityIntent(Context context , UUID contactID){
        Intent intent = new Intent(context , EditContactActivity.class);
        intent.putExtra(CONTACT_ID_KEY , contactID);
        return  intent;
    }
}
