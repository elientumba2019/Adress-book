package com.example.elie.smartaddressbook.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.elie.smartaddressbook.model.ContactModel;

/**
 * Created by elie on 17-8-26.
 */


/**
 * this class extends the fragment class
 * and its main purpose is to display a message that is to be
 * displayed to the user asking wheter or not the contact should be deleted
 */
public class DeleteDialogFragment extends DialogFragment {



    private static String KEY_FOR_ARGUMENT = "key";




    /**
     * method that will be used to create the dialog
     * that will be displayed on Screen
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("Are you sure you want to delete ?").setPositiveButton("Delete" , null).create();

        return  dialog;
    }





    /**
     * fragment argument
     * this method returns a new object of type
     * delete dialog toher with its argument inside
     * @param c
     * @return
     */
    public static DeleteDialogFragment getinstanceFragment(ContactModel c){

        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_FOR_ARGUMENT , c);
        DeleteDialogFragment fragment = new DeleteDialogFragment();
        fragment.setArguments(bundle);
        return  fragment;
    }
}
