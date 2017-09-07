package com.example.elie.smartaddressbook.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;

import com.example.elie.smartaddressbook.R;

/**
 * Created by elie on 17-8-18.
 */


/**
 * dialog fragment message
 */
public class SavedMessageDialog extends DialogFragment {


    private DialogInterface.OnClickListener listener;



    /**
     * creates a dialog
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finish();
            }
        };


        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("Congratulation").
                setPositiveButton("Close" , listener).setView(R.layout.contact_saved_layout).create();
        return dialog;
    }
}
