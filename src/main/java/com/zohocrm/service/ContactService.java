package com.zohocrm.service;

import java.util.List;

import com.zohocrm.entities.Contact;

public interface ContactService {
	public void saveContact(Contact contact);
	List<Contact> getContacts();              //same package , public not required
	public Contact getContactById(long id);

}
