package com.cts.sbd.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Counter {

	@Value("${counter.seed:100}")
	private int seed;
	
	public int next() {
		return ++seed;
	}
}
