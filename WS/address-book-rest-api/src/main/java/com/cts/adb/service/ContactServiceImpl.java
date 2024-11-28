package com.cts.adb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.adb.entity.Contact;
import com.cts.adb.exception.ContactNotFoundException;
import com.cts.adb.repo.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepo;
	
	@Override
	public Contact createContact(Contact contact) {
		return contactRepo.save(contact);
	}

	@Override
	public Contact updateContact(Contact contact) throws ContactNotFoundException {
		if(!contactRepo.existsById(contact.getContactId())) {
			throw new ContactNotFoundException("Contact Not Found");
		}
		return contactRepo.save(contact);
	}

	@Override
	public Contact getById(long id) throws ContactNotFoundException {
		if(!contactRepo.existsById(id)) {
			throw new ContactNotFoundException("Contact Not Found");
		}

		return contactRepo.findById(id).orElse(null);
	}

	@Override
	public List<Contact> getAll() {
		return contactRepo.findAll();
	}

	@Override
	public void deleteById(long id) throws ContactNotFoundException {
		if(!contactRepo.existsById(id)) {
			throw new ContactNotFoundException("Contact Not Found");
		}
		contactRepo.deleteById(id);
	}

}
