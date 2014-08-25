package com.fifi.Phonebook;

import java.util.List;

/**
 * Created by Fifi on 2014-08-24.
 */
public interface DatasourceDAO {

    public void createContactDetails(ContactDetails contactDetails);
    public void upgradeContactDetails(ContactDetails contactDetails);
    public ContactDetails findContactById(int id);
    public void  deleteContact(ContactDetails contactDetails);
    public ContactDetails getContactDetails();
    public List<ContactDetails> getContactList();
    public String getData();


}
