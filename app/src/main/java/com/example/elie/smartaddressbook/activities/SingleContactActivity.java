package com.example.elie.smartaddressbook.activities;

/**
 * Created by elie on 17-8-14.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.elie.smartaddressbook.fragments.SingleContactFragment;
import com.example.elie.smartaddressbook.model.ContactModel;

import java.util.UUID;

/**
 * class that will be used to
 * display the details about a contact
 */
public class SingleContactActivity extends MotherSingleActivity {


    private static final String INTENT_KEY_CONTACT_ID = "key";


    /**
     * return the corresponding fragment
     * @return
     */
    @Override
    protected Fragment getFragment() {
        UUID id = (UUID) getIntent().getSerializableExtra(INTENT_KEY_CONTACT_ID);
        return SingleContactFragment.getFragmentInstance(id);
    }




    /**
     * return the intent that will be used by some activity
     * to open this
     * @param context
     * @return
     */
    public static Intent getActivityIntent(Context context , UUID contactId){
        Intent intent = new Intent(context , SingleContactActivity.class);
        intent.putExtra(INTENT_KEY_CONTACT_ID , contactId);
        return intent;
    }
}
