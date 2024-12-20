package com.cts.adb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.adb.entity.Contact;
import com.cts.adb.exception.AdbException;
import com.cts.adb.repo.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepo;
	
	@Override
	public Contact createContact(Contact contact) throws AdbException {
		/*
		 * if(contactRepo.existsById(contact.getContactId())) { throw new
		 * AdbException("Duplicate Contact Id"); }
		 */
		return contactRepo.save(contact);
	}

	@Override
	public Contact updateContact(Contact contact) throws AdbException {
		if(!contactRepo.existsById(contact.getContactId())) {
			throw new AdbException("Contact Not Found");
		}
		return contactRepo.save(contact);
	}

	@Override
	public Contact getById(long id) throws AdbException {
		if(!contactRepo.existsById(id)) {
			throw new AdbException("Contact Not Found");
		}

		return contactRepo.findById(id).orElse(null);
	}

	@Override
	public List<Contact> getAll() throws AdbException {
		return contactRepo.findAll();
	}

	@Override
	public void deleteById(long id) throws AdbException {
		if(!contactRepo.existsById(id)) {
			throw new AdbException("Contact Not Found");
		}
		contactRepo.deleteById(id);
	}

}
