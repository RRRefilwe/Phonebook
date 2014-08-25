package com.fifi.Phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Fifi on 2014-08-24.
 */
public class DatabasesourceDAOImpl implements DatasourceDAO {

    private SQLiteDatabase ourDatabase;
    private  DBAdapter dbHelper;

    public DatabasesourceDAOImpl(Context c){

        dbHelper = new DBAdapter(c);

    }
    public void open() throws SQLException {

         ourDatabase = dbHelper.getWritableDatabase();

    }
    public void close(){

       dbHelper.close();
    }


    @Override
    public void createContactDetails(ContactDetails c) {

        open();
        ContentValues values = new ContentValues();
        values.put(DBAdapter.COLUMN_FIRSTNAME, c.getFirstname());
        values.put(DBAdapter.COLUMN_LASTNAME, c.getLastname());
        values.put(DBAdapter.COLUMN_CELLPHONE, c.getEmail());
        values.put(DBAdapter.COLUMN_EMAIL, c.getEmail());
        values.put(DBAdapter.COLUMN_ADDRESS, c.getHomeAddress());

        ourDatabase.insert(DBAdapter.TABLE_CONTACT, null, values);
        close();
    }

    @Override
    public void upgradeContactDetails(ContactDetails c) {
        open();
        ContentValues values = new ContentValues();
        values.put(DBAdapter.COLUMN_CELLPHONE, c.getCellNumber());
        ourDatabase.update(DBAdapter.TABLE_CONTACT, values,DBAdapter.COLUMN_ROWID + " =?", new String[]{String.valueOf(c.getId())});
        close();
    }

    @Override
    public ContactDetails findContactById(int id) {

        open();
        Cursor cursor = ourDatabase.query(DBAdapter.TABLE_CONTACT, new String[]
                        {DBAdapter.COLUMN_ROWID, DBAdapter.COLUMN_FIRSTNAME,
                        DBAdapter.COLUMN_LASTNAME, DBAdapter.COLUMN_CELLPHONE,
                        DBAdapter.COLUMN_EMAIL, DBAdapter.COLUMN_ADDRESS},
                        DBAdapter.COLUMN_ROWID + " =? ",
                        new String[]{String.valueOf(id)},null, null, null );
        if(cursor!= null)
            cursor.moveToFirst();
        ContactDetails contacts = new ContactDetails
                                 .Builder(cursor.getString(1))
                                 .Id(cursor.getInt(0))
                                 .Lastname(cursor.getString(2))
                                 .CellNumber(cursor.getString(3))
                                 .Email(cursor.getString(4))
                                 .HomeAddress(cursor.getString(5))
                                 .build();
        close();

        return contacts;
    }

    @Override
    public void deleteContact(ContactDetails c) {
            open();
            ourDatabase.delete(DBAdapter.TABLE_CONTACT,
                    DBAdapter.COLUMN_ROWID + " =? " ,
                    new String[]{String.valueOf(c.getId())});
            close();
    }

    @Override
    public ContactDetails getContactDetails() {
        String selectQuery = "SELECT * FROM" + DBAdapter.TABLE_CONTACT;
        ContactDetails contacts = null;
        open();
        Cursor cursor = ourDatabase.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{

                contacts = new ContactDetails
                        .Builder(cursor.getString(1))
                        .Id(cursor.getInt(0))
                        .Lastname(cursor.getString(2))
                        .CellNumber(cursor.getString(3))
                        .Email(cursor.getString(4))
                        .HomeAddress(cursor.getString(5))
                        .build();

            }while(cursor.moveToNext());
        }
        close();
        return  contacts;

    }

    @Override
    public List<ContactDetails> getContactList() {

        open();
        Cursor cursor = ourDatabase.query(DBAdapter.TABLE_CONTACT, new String[]
                        {DBAdapter.COLUMN_ROWID, DBAdapter.COLUMN_FIRSTNAME,
                                DBAdapter.COLUMN_LASTNAME, DBAdapter.COLUMN_CELLPHONE,
                                DBAdapter.COLUMN_EMAIL, DBAdapter.COLUMN_ADDRESS},
                null, null, null, null, null);


        List<ContactDetails> contacts = null;
        ContactDetails c;

        if (cursor.moveToFirst()) {
            do {

                c = new ContactDetails
                        .Builder(cursor.getString(1))
                        .Id(cursor.getInt(0))
                        .Lastname(cursor.getString(2))
                        .CellNumber(cursor.getString(3))
                        .build();
                contacts.add(c);

            } while (cursor.moveToNext());
        }
        return  contacts;
    }
    @Override
    public String getData() {

        open();
        Cursor cursor = ourDatabase.query(DBAdapter.TABLE_CONTACT, new String[]
                        {DBAdapter.COLUMN_ROWID, DBAdapter.COLUMN_FIRSTNAME,
                                DBAdapter.COLUMN_LASTNAME, DBAdapter.COLUMN_CELLPHONE,
                                DBAdapter.COLUMN_EMAIL, DBAdapter.COLUMN_ADDRESS},
                null, null, null, null, null);
        String result = null;

//        ContactDetails contacts;
//        if (cursor.moveToFirst()) {
//            do {
//
//                contacts = new ContactDetails
//                        .Builder(cursor.getString(1))
//                        .Id(cursor.getInt(0))
//                        .Lastname(cursor.getString(2))
//                        .CellNumber(cursor.getString(3))
//                        .build();
//                result =  contacts.getLastname() + "                            "
//                        + contacts.getCellNumber() +  "\n";
//
//            } while (cursor.moveToNext());
//        }
        int iName = cursor.getColumnIndex(DBAdapter.COLUMN_LASTNAME);
        int iCell = cursor.getColumnIndex(DBAdapter.COLUMN_CELLPHONE);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            result = cursor.getString(iName) + "                                    " +
                    cursor.getString(iCell);
        }




        close();
       return result;
    }

}
