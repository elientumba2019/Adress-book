package com.example.elie.smartaddressbook.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.example.elie.smartaddressbook.R;
import com.example.elie.smartaddressbook.database.ContactFactory;
import com.example.elie.smartaddressbook.fragments.ContactListFragment;
import com.example.elie.smartaddressbook.fragments.EditContactFragment;
import com.example.elie.smartaddressbook.fragments.SingleContactFragment;
import com.example.elie.smartaddressbook.model.ContactModel;

/**
 * Created by elie on 17-8-5.
 */


/**
 * crime list activity
 */
public class ContactListActivity extends MotherSingleActivity
        implements ContactListFragment.Callbacks , SingleContactFragment.Callbacks,
    EditContactFragment.Callbacks{


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
     * the method will return an id belonging to an alias resource
     * @return
     */
    @Override
    protected int getLayout() {
        //returning an alias resource
        return R.layout.layout_to_put_on_screen;
    }




    /**
     * Callbacks method implemented from an hosted fragment
     * that will be used to decide whether to open a new Activity
     * or simply to fill another container with some data
     * @param contact
     */
    @Override
    public void onContactClicked(ContactModel contact) {

        //it the layout is on a phone
        if(this.findViewById(R.id.container_detail) == null){
            Intent intent = SingleContactActivity.getActivityIntent(ContactListActivity.this , contact.getID());
            startActivity(intent);
        }


        //when the layout is on a tablet;
        else {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.container_detail ,
                    SingleContactFragment.getFragmentInstance(contact.getID())).commit();
        }
    }





    /**
     * actions to be performed when a users clicks
     * on the update buttpn on the layout
     * what will happen , will dpend on whether the application
     * is being used on phone or on table
     * @param model
     */
    @Override
    public void onUpdateClicked(ContactModel model) {


        //when the application is being used on tablet
        if(this.findViewById(R.id.container_detail) != null){

            EditContactFragment edit = EditContactFragment.getInstance(model.getID());
            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.container_detail , edit).commit();
        }
    }



    /**
     * Callbacks method that is called whenever a
     * contact  is deleted
     * @param contact
     */
    @Override
    public void onDeleteCliked(ContactModel contact , SingleContactFragment fragment) {
        ContactFactory.getFactoryInstance(getApplicationContext()).deleteContact(contact.getID());
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().remove(fragment).commit();
    }





    /**
     * Actions to be performed after a user clicked
     * on the save button
     * on a mobile phone the activity will be killed
     * on a tablet the fragment will simply be removed
     */
    @Override
    public void onSavedButtonCliked(EditContactFragment fragment) {

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().remove(fragment).commit();
    }
}
