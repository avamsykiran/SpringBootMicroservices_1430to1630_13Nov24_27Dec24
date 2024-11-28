package com.cts.adb.service;

import java.util.List;

import com.cts.adb.entity.Contact;
import com.cts.adb.exception.ContactNotFoundException;

public interface ContactService {
	Contact createContact(Contact contact) ;
	Contact updateContact(Contact contact) throws ContactNotFoundException ;
	Contact getById(long id) throws ContactNotFoundException ;
	List<Contact> getAll() ;
	void deleteById(long id) throws ContactNotFoundException ;	
}
