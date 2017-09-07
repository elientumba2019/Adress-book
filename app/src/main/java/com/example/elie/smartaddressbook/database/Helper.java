package com.example.elie.smartaddressbook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by elie on 17-8-14.
 */


/**
 * helper for the database
 */
public class Helper extends SQLiteOpenHelper {

    private static int version = 1;



    /**
     * helper constructor
     * @param context
     */
    public Helper(Context context) {
        super(context , Schema.DATABASE_NAME , null , version);
    }


    /**
     * oncreate
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        //create the contact table in the database
        sqLiteDatabase.execSQL(

                "create table " + Schema.ContactTable.NAME + "(" +
                        "id integer primary key autoincrement , " +
                        Schema.ContactTable.Columns.ID + "," +
                        Schema.ContactTable.Columns.FIRSTNAME + "," +
                        Schema.ContactTable.Columns.LASTNAME + "," +
                        Schema.ContactTable.Columns.SEX + "," +
                        Schema.ContactTable.Columns.ADDRESS + "," +
                        Schema.ContactTable.Columns.PHONE_NUMBER +
                        ")"
        );


        //create the favorite table in the database
        sqLiteDatabase.execSQL(

                "create table " + Schema.Favorite.NAME + "(" +
                        Schema.Favorite.Columns.ID + "," +
                        Schema.Favorite.Columns.FIRSTNAME + "," +
                        Schema.Favorite.Columns.LASTNAME + "," +
                        Schema.Favorite.Columns.SEX + "," +
                        Schema.Favorite.Columns.ADDRESS + "," +
                        Schema.Favorite.Columns.PHONE_NUMBER +
                        ")"
        );
    }



    /**
     * onUpgrade
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
