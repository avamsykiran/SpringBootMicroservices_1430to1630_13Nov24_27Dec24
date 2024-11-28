package com.cts.springdidemo;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.cts.springdidemo.ui.HomeScreen;

@Configuration
@ComponentScan("com.cts.springdidemo")
@PropertySource("classpath:external.properties")
public class SpringDIDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringDIDemoApplication.class);
		
		HomeScreen hs = (HomeScreen) context.getBean("homeScreen");
		hs.run();
	}
	
	@Bean
	public Scanner kbin() {
		return new Scanner(System.in);
	}

}
