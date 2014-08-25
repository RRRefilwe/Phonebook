package com.fifi.Phonebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Fifi on 2014-08-24.
 */
public class DBAdapter extends SQLiteOpenHelper {

    public static final String TABLE_CONTACT = "Contacts";

    public static final String COLUMN_ROWID = "_id";
    public static final String COLUMN_FIRSTNAME= "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CELLPHONE = "cellNumber";
    public static final String COLUMN_ADDRESS = "address";

    private static final String DATABASE_NAME = "PhoneBook.db";
    private static final int DATABASE_VERSION = 1;


    private  static final String CREATE_CONTACT_TABLE    = "create table IF NOT EXISTS "
                + TABLE_CONTACT + " ( "
                + COLUMN_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FIRSTNAME + " TEXT NOT NULL,"
                + COLUMN_LASTNAME + " TEXT NOT NULL,"
                + COLUMN_CELLPHONE + " TEXT NOT NULL,"
                + COLUMN_EMAIL + " TEXT NOT NULL,"
                + COLUMN_ADDRESS + " TEXT NOT NULL );" ;



        public DBAdapter(Context context){

            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(CREATE_CONTACT_TABLE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

            Log.w(DBAdapter.class.getName(), "Updating database from " + oldVersion + " to " + newVersion + ", Which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
            onCreate(db);
        }


}
