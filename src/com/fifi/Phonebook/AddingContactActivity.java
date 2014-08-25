package com.fifi.Phonebook;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddingContactActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private EditText  firstname;
    private EditText  lastname;
    private EditText  email;
    private EditText  phoneNumber;
    private EditText  homeAddress;
    private Button createButton;
    private Button viewList;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final DatasourceDAO dao = new DatabasesourceDAOImpl(this);

        firstname = (EditText)findViewById(R.id.txtFirstname);
        lastname = (EditText)findViewById(R.id.txtLastname);
        email = (EditText)findViewById(R.id.txtEmail);
        phoneNumber = (EditText)findViewById(R.id.txtCellphone);
        homeAddress = (EditText)findViewById(R.id.txtHomeAddress);
        createButton = (Button)findViewById(R.id.btnCreate);
        viewList = (Button)findViewById(R.id.btnDisplay);


        createButton.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                        boolean didItWork = true;
                       try {
                           String name = firstname.getText().toString();
                           String surname = lastname.getText().toString();
                           String mail = email.getText().toString();
                           String numbers = phoneNumber.getText().toString();
                           String address = homeAddress.getText().toString();

                           ContactDetails c = new ContactDetails.Builder(name)
                                             // .Id()
                                              .Lastname(surname)
                                              .CellNumber(numbers)
                                              .Email(mail)
                                              .HomeAddress(address)
                                              .build();
                           dao.createContactDetails(c);


                       }catch (Exception e){
                             didItWork = false;
                             String error = e.toString();
                             Dialog d = new Dialog(AddingContactActivity.this);
                             d.setTitle("Error");
                             TextView tv = new TextView(AddingContactActivity.this);
                             tv.setText(error);
                             d.setContentView(tv);
                             d.show();
                       }finally {
                           if(didItWork){
                               Dialog d = new Dialog(AddingContactActivity.this);
                               d.setTitle("Successfully");
                               TextView tv = new TextView(AddingContactActivity.this);
                               tv.setText("Successfully Added");
                               d.setContentView(tv);
                               d.show();

                           }
                       }
            }
        });
        viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(AddingContactActivity.this, ViewingAddedContactActivity.class));


            }
        });

    }

}
