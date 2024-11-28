package com.cts.springdidemo.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cts.springdidemo.service.GreetingService;
import com.cts.springdidemo.util.Counter;

@Component
public class HomeScreen {
	
	@Value("${app.title:Untitled Application}")
	private String title;
	
	@Autowired
	private Scanner scan;
	
	@Autowired
	@Qualifier("greetingServiceTimeBasedImpl")
	private GreetingService gs;
	
	@Autowired
	private Counter c1;
	
	@Autowired
	private Counter c2;
	
	@Autowired
	private Counter c3;

	public void run() {
		System.out.println(title);
		System.out.println("----------------------------------------------------");
	
		System.out.println(c1.next()); 
		System.out.println(c1.next());
		System.out.println(c2.next());
		System.out.println(c2.next());
		System.out.println(c3.next());
		System.out.println(c3.next());
		
		System.out.println("What is your name?");
		String unm = scan.nextLine();
		System.out.println(gs.greet(unm));
	}
}
