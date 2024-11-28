package com.cts.springdidemo.service;

import org.springframework.stereotype.Service;

@Service
public class GreetinServiceSimpleImpl implements GreetingService {

	@Override
	public String greet(String userName) {
		return "Hello "+userName;
	}

}
