package com.example.elie.smartaddressbook.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.location.Criteria;

import com.example.elie.smartaddressbook.model.ContactModel;

import java.util.UUID;

/**
 * Created by elie on 17-8-18.
 */


/**
 * returns a contact
 * or can be used later for other type
 */
public class ContactCursorWrapper extends CursorWrapper {



    public ContactCursorWrapper(Cursor cursor) {
        super(cursor);
    }



    public ContactModel getContact(){

        String id = getString(getColumnIndex(Schema.ContactTable.Columns.ID));
        String firstname = getString(getColumnIndex(Schema.ContactTable.Columns.FIRSTNAME));
        String lastname = getString(getColumnIndex(Schema.ContactTable.Columns.LASTNAME));
        String sex = getString(getColumnIndex(Schema.ContactTable.Columns.SEX));
        String address = getString(getColumnIndex(Schema.ContactTable.Columns.ADDRESS));
        String phone = getString(getColumnIndex(Schema.ContactTable.Columns.PHONE_NUMBER));


        ContactModel c = new ContactModel();
        c.setID(UUID.fromString(id));
        c.setFirstname(firstname);
        c.setLastname(lastname);
        c.setSex(sex.charAt(0));
        c.setAddress(address);
        c.setPhoneNumber(phone);


        return  c;
    }
}
