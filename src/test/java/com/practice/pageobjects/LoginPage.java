package com.practice.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	//**"AUTHENTICATION" Header
		@FindBy(xpath="//h1[@class='page-heading']")
		//@CacheLookup
		WebElement AuthenticationHeader;
		
		public WebElement AuthenticationHeader() {
			return AuthenticationHeader;	
		}
	
		
	//**Email Text field	
		@FindBy(id="email_create")
		WebElement emailField;
		
		public WebElement emailField() {
			return emailField;
		}
		
		
	//**'Create an account' button
		@FindBy(xpath="//form[@id='create-account_form']//span[1]")
		WebElement CreateAnAccountButton;
		
		public WebElement CreateAnAccountButton() {
			return CreateAnAccountButton;
		}
		
		
		
}
