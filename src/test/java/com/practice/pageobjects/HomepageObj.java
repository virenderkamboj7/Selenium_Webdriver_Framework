package com.practice.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.practice.testcases.BaseClass;

public class HomepageObj extends BaseClass {

	/////////////////////////////////////////Amazon///////////////////////////////////////////
			
	
			//Nav Icon
			@FindBy(xpath="//i[@class='hm-icon nav-sprite']")
			//@CacheLookup
			WebElement nav_icon;
				
			public WebElement nav_icon() {
			return nav_icon;	
			}
			
			
			//Sign In
			@FindBy(id="hmenu-customer-name")
			//@CacheLookup
			WebElement signin;
				
			public WebElement signin() {
			return signin;	
			}
			
			//Create an account
			@FindBy(id="createAccountSubmit")
			//@CacheLookup
			WebElement createAccountSubmit;
				
			public WebElement createAccountSubmit() {
			return createAccountSubmit;	
			}	
			
			// Name Field
			@FindBy(id="ap_customer_name")
			//@CacheLookup
			WebElement customer_name_field;
				
			public WebElement customer_name_field() {
			return customer_name_field;	
			}
			
			//County Code buttom
			@FindBy(xpath="//span[@class='a-button-text a-declarative']")
			//@CacheLookup
			WebElement country_code;
				
			public WebElement country_code() {
			return country_code;	
			}
			
			//Select Country code
			@FindBy(id="auth-country-picker_90")
			//@CacheLookup
			WebElement country_code_select;
				
			public WebElement country_code_select() {
			return country_code_select;	
			}
			
			
			//Mobile
			@FindBy(id="ap_phone_number")
			//@CacheLookup
			WebElement mobile_field;
				
			public WebElement mobile_field() {
			return mobile_field;	
			}
			
			//Email
			@FindBy(id="ap_email")
			//@CacheLookup
			WebElement email_field;
				
			public WebElement email_field() {
			return email_field;	
			}
			
			
			
			//Password Field
			@FindBy(id="ap_password")
			//@CacheLookup
			WebElement password_field;
				
			public WebElement password_field() {
			return password_field;	
			}
			
			//Coumpute button
			@FindBy(xpath="//a[contains(text(),'Computers')]")
			//@CacheLookup
			WebElement computer_button;
				
			public WebElement computer_button() {
			return computer_button;	
			}	
			
					
}
