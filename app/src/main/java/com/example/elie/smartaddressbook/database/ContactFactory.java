package com.example.elie.smartaddressbook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.transition.Scene;

import com.example.elie.smartaddressbook.model.ContactModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.UUID;

/**
 * Created by elie on 17-8-5.
 */


/**
 * Singleton factory
 */
public class ContactFactory implements Serializable {


    private static ContactFactory factory;

    //day 2
    private Context context;
    private SQLiteDatabase database;


    /**
     * constructor
     */
    private ContactFactory(Context context){

        //gets the application context
        this.context = context.getApplicationContext();
        Helper helper = new Helper(context);
        database = helper.getWritableDatabase();
    }



    /**
     * Instance singleton method to return the instance of the
     * singleton instead for havind multiple object we only have one
     * @return
     */
    public static ContactFactory getFactoryInstance(Context context){

        if(factory == null){
            factory = new ContactFactory(context);
            return factory;
        }

        return  factory;
    }





    /**
     * List random for return contact created
     * @return
     */
    public  ArrayList<ContactModel> getContactList(){

        ContactCursorWrapper wrapper = new ContactCursorWrapper(getQueryCursor(null , null));
        ArrayList<ContactModel> models = new ArrayList<>();




        try{

            wrapper.moveToFirst();

            while (!wrapper.isAfterLast()){
                ContactModel c = wrapper.getContact();
                models.add(c);
                wrapper.moveToNext();
            }

        }

        finally {
            wrapper.close();
        }

        return  models;

    }




    /**
     * returns a single contact;
     * @param id
     * @return
     */
    public ContactModel getContact(UUID id){

        ContactModel contact;
        Cursor cursor = getQueryCursor(Schema.ContactTable.Columns.ID + " = ?" , new String[]{id.toString()});
        ContactCursorWrapper wrapper = new ContactCursorWrapper(cursor);



        try{

            if(wrapper.getCount() == 0){
                throw new RuntimeException("Contact not Found");
            }



            wrapper.moveToFirst();
            contact = wrapper.getContact();
        }

        finally {
            wrapper.close();
        }

        return contact;
    }






    /**
     * adds a contact to the database
     * @param model
     */
    public void addContact(ContactModel model){
        ContentValues values = getContentValue(model);
        database.insert(Schema.ContactTable.NAME , null , values);
    }






    /**
     * returns a content value corresponding
     * @param model
     */
    private ContentValues getContentValue(ContactModel model){

        ContentValues values = new ContentValues();

        values.put(Schema.ContactTable.Columns.ID , model.getID().toString());
        values.put(Schema.ContactTable.Columns.FIRSTNAME , model.getFirstname());
        values.put(Schema.ContactTable.Columns.LASTNAME , model.getLastname());
        values.put(Schema.ContactTable.Columns.SEX , String.valueOf(model.getSex()));
        values.put(Schema.ContactTable.Columns.ADDRESS , model.getAddress());
        values.put(Schema.ContactTable.Columns.PHONE_NUMBER , model.getPhoneNumber());

        return values;
    }






    /**
     * returns a cursor that will be used to get Crimes
     * @param where
     * @param whereArgs
     * @return
     */
    private Cursor getQueryCursor(String where , String whereArgs[]){

        Cursor cursor = database.query(
                Schema.ContactTable.NAME,
                null,
                where,
                whereArgs,
                null,
                null,
                null

        );

        return cursor;
    }






    /**
     * updates an existing contact inside the database
     * @param contactModel
     */
    public void updateContact(ContactModel contactModel){

        ContentValues updateValues = getContentValue(contactModel);
        database.update(Schema.ContactTable.NAME , updateValues , Schema.ContactTable.Columns.ID + " = ?"
        , new String[]{contactModel.getID().toString()});
    }






    /**
     * deletes an existing contact from the database
     * @param id
     */
    public void deleteContact(UUID id){

        database.delete(Schema.ContactTable.NAME , Schema.ContactTable.Columns.ID + " = ?" ,
                new String[]{id.toString()});
    }

}
