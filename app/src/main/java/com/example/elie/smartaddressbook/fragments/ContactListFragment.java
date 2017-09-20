package com.example.elie.smartaddressbook.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elie.smartaddressbook.activities.AddContactActivity;
import com.example.elie.smartaddressbook.activities.SingleContactActivity;
import com.example.elie.smartaddressbook.database.ContactFactory;
import com.example.elie.smartaddressbook.model.ContactModel;
import com.example.elie.smartaddressbook.R;

import java.util.ArrayList;


/**
 * the contact list fragment class is responsible for holding a
 * list of the person contact
 *
 */
public class ContactListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContactFactory factory; // = ContactFactory.getFactoryInstance(getActivity()); code canceled because the activity couldnt get got because inecistant
    private ArrayList<ContactModel> list; // = factory.getContactList(20);
    private AdapterForContact adapter = null;


    //Callbacks variable
    private Callbacks callbacks;


    /**
     * call backs interface that will be attached to an
     * host activity , through which the fragment will
     * be able to call its host activity's method
     */
    public interface Callbacks{
        void onContactClicked(ContactModel contact);

    }



    /**
     * Method to attach a fragment to
     * its host activity
     * @param context
     */
    public void onAttach(Context context){
        super.onAttach(context);
        callbacks = (Callbacks)context;

    }


    /**
     * detaching the fragment from its host
     * activity
     */
    public void onDetach(){
        super.onDetach();
        callbacks = null;
    }




    /**
     * onCreates method to create the activity
     * day 2
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        factory = ContactFactory.getFactoryInstance(getActivity());
        list = factory.getContactList();
    }






    /**
     * onCreate method responsible for inflating the layout
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        //layout inflation
        View view = inflater.inflate(R.layout.list_contact_layout , container , false);

        //getting the recycler view
        recyclerView = view.findViewById(R.id.contact_list_recycler_view);

        updateUI();

        return  view;
    }




    /**
     * updates the layout whenever the a contact has been changed
     * or whenever a contact has been deleted
     */
    public void update(){

        list = ContactFactory.getFactoryInstance(getActivity()).getContactList();

        if(adapter == null){
            adapter = new AdapterForContact(list);
            recyclerView.setAdapter(adapter);
        }
        else{
            adapter.setContactModels(list);
            adapter.notifyDataSetChanged();
        }
    }




    /**
     * updates the UI
     */
    public void updateUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         adapter = new AdapterForContact(list);
        recyclerView.setAdapter(adapter);
    }






    /**
     * Inner class that will be used as a view holder for
     * for the recycler view that will display contact
     */
    public class ViewHolderForContact extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView username;
        private ContactModel contact;



        public ViewHolderForContact(View view){
            super(view);

            //getting refernce to element on layout
            username = (TextView)view.findViewById(R.id.username);
            view.setOnClickListener(this);
        }


        //getters and setters
        public TextView getUsername() {
            return username;
        }

        public void setUsername(TextView username) {
            this.username = username;
        }






        /**
         * bind the model with the screen
         * @param contactModel
         */
        public void bind(ContactModel contactModel){

            //sets the object that will be used when clicked on
            contact = contactModel;
            username.setText(contactModel.getFirstname() + " " + contactModel.getLastname());
        }




        /**
         * opens the single contact activity
         * when it is clicked by a the user
         * @param view
         */
        @Override
        public void onClick(View view) {
            callbacks.onContactClicked(contact);
        }
    }







    /**
     * adapter for the recycler view for contact
     */
    public class AdapterForContact extends RecyclerView.Adapter<ViewHolderForContact>{


        /**
         * setter
         * @param contactModels
         */
        public void setContactModels(ArrayList<ContactModel> contactModels) {
            this.contactModels = contactModels;
        }




        private ArrayList<ContactModel> contactModels;



        /**
         * constructor
         * @param list
         */
        public AdapterForContact(ArrayList<ContactModel> list){
            this.contactModels = list;
        }





        /**
         * inflates what needs to be inflated
         * @param parent
         * @param viewType
         * @return
         */
        @Override
        public ViewHolderForContact onCreateViewHolder(ViewGroup parent, int viewType) {

            //inflating the layout
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            // view for single row item
            View view = inflater.inflate(R.layout.contact_item_row_layout , parent , false);

            ViewHolderForContact holderForContact = new ViewHolderForContact(view);

            return holderForContact;
        }




        /**
         * takes care for binding
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(ViewHolderForContact holder, int position) {

            holder.bind(contactModels.get(position));
        }




        /**
         * returns the current number of item in the list
         * @return
         */
        @Override
        public int getItemCount() {
            return contactModels.size();
        }
    }






    /**
     * adds a menu element to the window allowing us to
     * add more contacts
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_contact_menu_action , menu);
    }





    /**
     * acts according tp what option in the menu has been selected
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.add_contact : {
                Intent intent = AddContactActivity.getActivityIntent(getActivity());
                startActivity(intent);
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }




    /**
     * displays the new list of contact with the new modification
     * when the activity is restored
     */
    @Override
    public void onResume() {
        super.onResume();
        list = factory.getContactList();
        updateUI();
    }
}
