package com.cts.adb.ui;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cts.adb.entity.Contact;
import com.cts.adb.exception.AdbException;
import com.cts.adb.service.ContactService;

@Component
public class ContactUI implements CommandLineRunner {

	@Value("${spring.application.name:UnTitled}")
	private String title;

	@Autowired
	private Scanner scan;

	@Autowired
	private ContactService contactService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(title);
		System.out.println("-------------------------------");

		boolean quit = false;

		while (!quit) {
			System.out.println("Command (ADD/DELETE/LIST/QUIT) > ");
			String cmd = scan.nextLine();

			switch (cmd.toLowerCase()) {
			case "add":
				doAdd();
				break;
			case "delete":
				doDelete();
				break;
			case "list":
				doList();
				break;
			case "quit":
				quit = true;
				break;
			default:
				System.out.println("Unknown Command");
			}
		}

		System.out.println("Application Terminated");
	}

	void doAdd() {
		Contact contact = new Contact();
		
		System.out.print("Full Name? ");
		contact.setFullName(scan.nextLine());
		System.out.print("Mobile? ");
		contact.setMobile(scan.nextLine());
		System.out.print("Mail Id? ");
		contact.setMailId(scan.nextLine());
		
		try {
			contact = contactService.createContact(contact);
			System.out.println("Contact Saved with id: "+contact.getContactId());
		} catch (AdbException e) {
			System.out.println(e.getMessage());
		}
		
	}

	void doDelete() {
		System.out.print("Contact Id? ");
		long id = scan.nextLong();
		
		try {
			contactService.deleteById(id);
			System.out.println("Cpontact Deleted!");
		} catch (AdbException e) {
			System.out.println(e.getMessage());
		}
	}

	void doList() {
		
		try {
			List<Contact> contacts = contactService.getAll();
			if (contacts.isEmpty()) {
				System.out.println("No Records To Display");
			}else {
				contacts.stream().forEach(System.out::println);
			}
		} catch (AdbException e) {
			System.out.println(e.getMessage());
		}
				
	}

}
