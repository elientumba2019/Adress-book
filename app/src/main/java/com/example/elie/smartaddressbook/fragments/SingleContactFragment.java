package com.example.elie.smartaddressbook.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.elie.smartaddressbook.R;
import com.example.elie.smartaddressbook.activities.EditContactActivity;
import com.example.elie.smartaddressbook.activities.SingleContactActivity;
import com.example.elie.smartaddressbook.database.ContactFactory;
import com.example.elie.smartaddressbook.model.ContactModel;

import java.util.UUID;

/**
 * Created by elie on 17-8-14.
 */

/**
 * Fragment that will containt the layout and other
 * corresponding operation for the single detail fragment
 */
public class SingleContactFragment extends Fragment {



    //fragment argument key
    private static final String FRAGMENT_ARGUMENT_KEY = "key";
    private ContactModel contact;
    private TextView firstname , lastname , address , phone , sex;
    private RadioButton male , female;
    private UUID contactId;

    private Callbacks callbacks;




    /**
     * callbacks interface
     */
    public interface Callbacks{
        void onUpdateClicked(ContactModel model);
        void onDeleteCliked(ContactModel contact , SingleContactFragment fragment);
    }



    /**
     * attaching the fragment to its host activity
     * so that it can be able to invoke methods
     * contained in its host activity
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(getActivity().findViewById(R.id.container_detail) != null) {
            callbacks = (Callbacks) context;
        }
    }



    /**
     * detaching a fragment from its host activity
     */
    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }




    /**
     * onCreate method
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contactId = (UUID) getArguments().getSerializable(FRAGMENT_ARGUMENT_KEY);
        contact = ContactFactory.getFactoryInstance(getActivity()).getContact(contactId);

    }





    /**
     * inflater method
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.single_contact_layout , container , false);
        getReferenceOnLayout(view);
        setLayout();
        setHasOptionsMenu(true);
        return view;
    }




    /**
     * get reference of all element that are on the layout
     * @param view
     */
    private void getReferenceOnLayout(View view){

        firstname = (TextView)view.findViewById(R.id.firstname);
        lastname = (TextView)view.findViewById(R.id.lastname);
        sex = (TextView)view.findViewById(R.id.sex);
        address = (TextView)view.findViewById(R.id.address_detail);
        phone = (TextView)view.findViewById(R.id.phone);
    }





    /**
     * puts element on the layout
     */
    private void setLayout(){
        firstname.setText(contact.getFirstname());
        lastname.setText(contact.getLastname());

        if(contact.getSex() == 'M'){
            sex.setText("Male");
        }
        else {
            sex.setText("Female");
        }


        address.setText(contact.getAddress());
        phone.setText(contact.getPhoneNumber());


    }




    /**
     * return the fragment argument
     * of a given fragment
     * @param contactId
     * @return
     */
    public static SingleContactFragment getFragmentInstance(UUID contactId){

        Bundle bundle = new Bundle();
        bundle.putSerializable(FRAGMENT_ARGUMENT_KEY , contactId);
        SingleContactFragment fragment = new SingleContactFragment();
        fragment.setArguments(bundle);

        return fragment;
    }





    /**
     * method to inflate the menu
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_ressouce_menu , menu);
    }





    /**
     * even tp be trigered when either element
     * of the menu is being clciked
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.edit : {

                //if the app is being used on a phone
                if(isOnPhone()){
                    Intent intent = EditContactActivity.getActivityIntent(getActivity() , contact.getID());
                    startActivity(intent);
                }

                //used on tablet
                else{
                    callbacks.onUpdateClicked(contact);
                }

                return true;
            }



            case R.id.delete : {

                if(isOnPhone()){
                    ContactFactory.getFactoryInstance(getActivity()).deleteContact(contact.getID());
                    getActivity().finish();
                }

                else{
                    callbacks.onDeleteCliked(contact , SingleContactFragment.this);
                }

                return true;
            }

            default:
                super.onOptionsItemSelected(item);
                return true;
        }
    }



    /**
     * determines whether the app is being used on a mobile
     * phone or on a table
     * @return
     */
    private boolean isOnPhone() {
        return getActivity().findViewById(R.id.container_detail) == null;
    }




    /**
     * deletes a contact in the database
     */
    private void deleteContact(){
        ContactFactory.getFactoryInstance(getActivity()).deleteContact(contact.getID());
    }




    /**
     * onResume
     */
    @Override
    public void onResume() {
        super.onResume();
        contact = ContactFactory.getFactoryInstance(getActivity()).getContact(contactId);
        setLayout();
    }
}
