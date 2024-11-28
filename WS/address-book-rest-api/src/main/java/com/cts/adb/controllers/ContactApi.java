package com.cts.adb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.adb.entity.Contact;
import com.cts.adb.exception.ContactNotFoundException;
import com.cts.adb.exception.InvalidContactDetailsException;
import com.cts.adb.service.ContactService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contacts")
public class ContactApi {

	@Autowired
	private ContactService contactService;

	@GetMapping
	ResponseEntity<List<Contact>> getAllAction() {
		List<Contact> data = contactService.getAll();
		return new ResponseEntity<List<Contact>>(data, HttpStatus.OK);
	}
	
	@GetMapping("/{cid}")
	ResponseEntity<Contact> getByIdAction(@PathVariable("cid") long id) throws ContactNotFoundException{
		Contact data = contactService.getById(id);
		return new ResponseEntity<Contact>(data, HttpStatus.OK);
	}
	
	@PostMapping
	ResponseEntity<Contact> addContact(@RequestBody @Valid Contact contact,BindingResult result) throws InvalidContactDetailsException{
		if(result.hasErrors()) {
			String errMsg = result.getAllErrors().stream().map(ObjectError::getDefaultMessage).reduce((m1,m2) -> m1+","+m2).orElse("");
			throw new InvalidContactDetailsException(errMsg);
		}
		
		Contact data= contactService.createContact(contact);
		return new ResponseEntity<Contact>(data,HttpStatus.CREATED );
	}
	
	@PutMapping
	ResponseEntity<Contact> updateContact(@RequestBody @Valid Contact contact,BindingResult result) throws InvalidContactDetailsException, ContactNotFoundException{
		if(result.hasErrors()) {
			String errMsg = result.getAllErrors().stream().map(ObjectError::getDefaultMessage).reduce((m1,m2) -> m1+","+m2).orElse("");
			throw new InvalidContactDetailsException(errMsg);
		}
		
		Contact data= contactService.updateContact(contact);
		return new ResponseEntity<Contact>(data,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{cid}")
	ResponseEntity<Void> deleteContact(@PathVariable("cid") long id) throws ContactNotFoundException{
		contactService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(ContactNotFoundException.class)
	ResponseEntity<String> handleContactNotFoundException(ContactNotFoundException exp){
		return new ResponseEntity<String>(exp.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidContactDetailsException.class)
	ResponseEntity<String> handleInvalidContactDetailsException(InvalidContactDetailsException exp){
		return new ResponseEntity<String>(exp.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<String> handleAnyOtherException(Exception exp){
		return new ResponseEntity<String>(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
