package com.practice.testcases;

import org.testng.annotations.Test;

import com.practice.utilities.ReadConfig;

public class Testing {

	
	@Test
	public void tes() {
		System.out.println(System.getProperty("user.dir"));
		ReadConfig readConfig = new ReadConfig();
		readConfig.chromedriver();
		System.out.println(readConfig.chromedriver());
	}
}
