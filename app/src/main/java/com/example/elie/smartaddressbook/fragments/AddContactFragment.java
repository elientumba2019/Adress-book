package com.example.elie.smartaddressbook.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.elie.smartaddressbook.R;
import com.example.elie.smartaddressbook.database.ContactFactory;
import com.example.elie.smartaddressbook.model.ContactModel;

/**
 * Created by elie on 17-8-18.
 */

public class AddContactFragment extends Fragment {


    //defining fields present in the layout
    private EditText firstname;
    private EditText lastname;
    private RadioGroup sex;
    private RadioButton male , female;
    private EditText address;
    private EditText phoneField;

    //field for the database
    ContactFactory factory;




    /**
     * onCreate method
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        factory = ContactFactory.getFactoryInstance(getActivity());
    }







    /**
     * returns the view correponding to the crate contact wizzard
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_contact_layout , container , false);
        getReferenceToLayout(view);
        return view;
    }


    /**
     * gets a reference of all the element on the layout
     * @param view
     */
    private void getReferenceToLayout(View view){

        firstname = (EditText)view.findViewById(R.id.firstname);
        lastname = (EditText)view.findViewById(R.id.lastname);
        sex = (RadioGroup)view.findViewById(R.id.sex_group);
        male = (RadioButton)view.findViewById(R.id.male);
        female = (RadioButton)view.findViewById(R.id.female);
        address = (EditText)view.findViewById(R.id.address);
        phoneField = (EditText)view.findViewById(R.id.phone_number);
    }






    /**
     * creates amenu in the tool bar
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_contact_fragment_save_menu , menu);
    }






    /**
     * this method will triger some action when a menu element is elected
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.save_contact : {
                saveContact();
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * saves a contact in the database
     */
    private void saveContact(){

        String fname = firstname.getText().toString();
        String lname = lastname.getText().toString();
        char seX = (male.isChecked() ? 'M' : 'F');
        String addresS = address.getText().toString();
        String phone = phoneField.getText().toString();

        //creating a new contact

        ContactModel contact = new ContactModel();
        contact.setFirstname(fname);
        contact.setLastname(lname);
        contact.setSex(seX);
        contact.setAddress(addresS);
        contact.setPhoneNumber(phone);

        //Toast.makeText(getActivity() , contact.toString() , Toast.LENGTH_LONG).show();
        factory.addContact(contact);
        SavedMessageDialog dialog = new SavedMessageDialog();
        dialog.show(getFragmentManager() , "show");

    }
}
