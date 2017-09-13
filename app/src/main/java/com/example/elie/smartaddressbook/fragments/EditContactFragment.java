package com.example.elie.smartaddressbook.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.elie.smartaddressbook.R;
import com.example.elie.smartaddressbook.database.ContactFactory;
import com.example.elie.smartaddressbook.model.ContactModel;

import java.util.UUID;

/**
 * Created by ntumba on 17-9-2.
 */



public class EditContactFragment extends Fragment {


    private EditText firstname;
    private EditText lastname;
    private RadioGroup sex;
    private RadioButton male , female;
    private EditText address;
    private EditText phoneField;



    private static final String ARGUMENT_ID = "argument id";
    ContactFactory factory = ContactFactory.getFactoryInstance(getActivity());
    ContactModel contact;


    private Callbacks callbacks;



    public interface Callbacks{
        void onSavedButtonCliked(EditContactFragment fragment);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getActivity().findViewById(R.id.container_detail) != null){
            callbacks = (Callbacks)context;
        }

    }


    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    /**
     * oncreate for initialization prior starting an
     * activity
     * or fragment 
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        UUID uuid = (UUID) getArguments().getSerializable(ARGUMENT_ID);
        contact = factory.getContact(uuid);

    }





    /**
     * on create view method in charge for
     * operations relative to the layout
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_contact_layout , container , false);
        findElementOnLayout(view);
        updateLayout();
        return view;
    }






    /**
     * find element on the layout and makes
     * references to them
     * @param view
     */
    private void findElementOnLayout(View view){

        firstname = (EditText)view.findViewById(R.id.firstname_edit);
        lastname = (EditText)view.findViewById(R.id.lastname_edit);
        sex = (RadioGroup)view.findViewById(R.id.sex_group_edit);
        male = (RadioButton)view.findViewById(R.id.male_edit);
        female = (RadioButton)view.findViewById(R.id.female_edit);
        address = (EditText)view.findViewById(R.id.address_edit);
        phoneField = (EditText)view.findViewById(R.id.phone_number_edit);

    }


    /**
     * method that will be used to update element on the layout
     */
    private void updateLayout(){
        firstname.setText(contact.getFirstname());
        lastname.setText(contact.getLastname());

        if(contact.getSex() == 'M'){
            male.setChecked(true);
        }
        else{
            female.setChecked(true);
        }


        address.setText(contact.getAddress());
        phoneField.setText(contact.getPhoneNumber());

    }






    /**
     * return an instance of the fragment
     * with the argument
     * @param uuid
     * @return
     */
    public static EditContactFragment getInstance(UUID uuid){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_ID , uuid);
        EditContactFragment fragment = new EditContactFragment();
        fragment.setArguments(bundle);
        return fragment;
    }





    /**
     * creates a menu and inflates on the layout
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_contact_menu , menu);
    }





    /**
     * function to implement the event that
     * are triggered  when element of the menu are being
     * clicked on
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.edit_save :
                ContactFactory.getFactoryInstance(getActivity()).updateContact(getContact());
                if(getActivity().findViewById(R.id.container_detail) == null){
                    getActivity().finish();
                }

                else{
                    callbacks.onSavedButtonCliked(EditContactFragment.this);
                }
                return true;


            default:
                super.onOptionsItemSelected(item);
                break;
        }
        return true;
    }




    /**
     * return the new contact that
     * will be used when updating
     * @return
     */
    private ContactModel getContact(){

        ContactModel model = new ContactModel();
        model.setFirstname(firstname.getText().toString());
        model.setLastname(lastname.getText().toString());
        model.setSex((male.isChecked()) ? 'M' : 'F');
        model.setAddress(address.getText().toString());
        model.setPhoneNumber(phoneField.getText().toString());
        model.setID(contact.getID());

        return model;
    }
}
