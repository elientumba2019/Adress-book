package com.example.elie.smartaddressbook.database;

import java.io.Serializable;

/**
 * Created by elie on 17-8-14.
 */

public class Schema implements Serializable {

    public static final String DATABASE_NAME = "Contact.db";


    /**
     * class for the contacts table
     */
    public static final class ContactTable{

        public static final String NAME = "contact";

        public static final class Columns{

            public static final String ID = "contactId";
            public static final String FIRSTNAME = "firstname";
            public static final String LASTNAME = "lastname";
            public static final String SEX = "sex";
            public static final String ADDRESS = "address";
            public static final String PHONE_NUMBER = "phone";

        }
    }




    /**
     * class for the favorite table
     */
    public static final class Favorite{

        public static final String NAME = "favorite";

        public static final class Columns{

            public static final String ID = "contactId";
            public static final String FIRSTNAME = "firstname";
            public static final String LASTNAME = "lastname";
            public static final String SEX = "sex";
            public static final String ADDRESS = "address";
            public static final String PHONE_NUMBER = "phone";
        }
    }
}
