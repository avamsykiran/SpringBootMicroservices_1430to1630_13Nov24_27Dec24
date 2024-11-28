package com.cts.sbd.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cts.sbd.service.GreetingService;
import com.cts.sbd.util.Counter;

@Component
public class HomeScreen implements CommandLineRunner {
	
	@Value("${spring.application.name:Untitled Application}")
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

	@Override
	public void run(String... args) throws Exception {
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
