package com.practice.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.practice.testcases.BaseClass;

public class HomepageObj extends BaseClass {

	/////////////////////////////////////////Amazon///////////////////////////////////////////
			
	
			//Nav Icon
			@FindBy(css="span[class='nav-line-2 ']")
			//@CacheLookup
			WebElement nav_icon;
				
			public WebElement nav_icon() {
			return nav_icon;	
			}
			
			
			//email field
			@FindBy(id="ap_email_login")
			//@CacheLookup
			WebElement email_field;
				
			public WebElement emailField() {
			return email_field;	
			}
			
			//Cotinue button
			@FindBy(css="input[type='submit']")
			WebElement continue_button;

			public WebElement continueButton() {
			return continue_button;	
			}

			//Proceed to Create an Account button
			@FindBy(css="input[type='submit']")
			WebElement proceed_button;
			public WebElement proceedButton(){
				return this.proceed_button;
			}

			//Mobile number field
			@FindBy(id="ap_phone_number")
			WebElement mobile;
			public WebElement mobile(){
				return this.mobile;
			}

			//password field
			@FindBy(id="ap_password")
			WebElement password_field;
			public WebElement passwordField() {
			return password_field;	
			}

			//User name field
			@FindBy(id="ap_customer_name")
			WebElement user_name;
			public WebElement userName(){
				return this.user_name;
			}

			//Computer button
			@FindBy(xpath = "//a[normalize-space()='Computers']")
			WebElement computer_button;
			public WebElement computerButton(){
				return this.computer_button;
			}
					
}
