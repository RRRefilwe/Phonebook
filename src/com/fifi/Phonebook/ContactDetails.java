package com.fifi.Phonebook;

import android.os.Build;

/**
 * Created by Fifi on 2014-08-24.
 */
public class ContactDetails {

    private int id;
    private String firstname;
    private  String lastname;
    private String cellNumber;
    private String email;
    private String homeAddress;

    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public String getCellNumber(){
        return cellNumber;
    }
    public String getEmail(){
        return email;
    }
    public String getHomeAddress(){
        return homeAddress;
    }
    public int getId(){
        return id;
    }

    private ContactDetails(){

    }
    public ContactDetails(Builder builder){
        id = builder.id;
        firstname= builder.firstname;
        lastname = builder.lastname;
        cellNumber = builder.cellNumber;
        email = builder.email;
        homeAddress = builder.homeAddress;
    }

    public static class Builder{

        private  int id;
        private String firstname;
        private  String lastname;
        private String cellNumber;
        private String email;
        private String homeAddress;

        public Builder(String firstname){
            this.firstname = firstname;
        }
        public Builder Id(int id){
            this.id = id;
            return this;
        }
        public Builder Firstname(String firstname){
            this.firstname = firstname;
            return this;
        }
        public Builder Lastname(String lastname){
            this.lastname = lastname;
            return this;
        }
        public Builder CellNumber(String cellNumber){
            this.cellNumber = cellNumber;
            return this;
        }
        public Builder Email(String email){
            this.email = email;
            return this;
        }
        public Builder HomeAddress(String homeAddress){
            this.homeAddress = homeAddress;
            return this;
        }

        public ContactDetails build(){
            return new ContactDetails(Builder.this);
        }
    }

}
