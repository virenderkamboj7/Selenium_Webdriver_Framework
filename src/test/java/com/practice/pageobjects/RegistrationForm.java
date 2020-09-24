package com.practice.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationForm {

	
	//**Salutation (Mr. or Mrs. radio buttons)
		@FindBy(xpath="//input[@id='id_gender1']")
		WebElement Salutation_Mr;
	
		public WebElement Salutation_Mr() {
		return Salutation_Mr;
		}
	
	
	//**First Name
		@FindBy(id="customer_firstname")
		WebElement FirstName;
		
		public WebElement CustomerFirstName() {
			return FirstName;
		}
	
	//**Last Name	
		@FindBy(id="customer_lastname")
		WebElement LastName;
		
		public WebElement CustomerLastName() {
			return LastName;
		}
		
	
		
	//**Password Field	
		@FindBy(id="passwd")
		WebElement password;
		
		public WebElement password() {
			return password;
		}
		
		
	//**DOB Select boxes	
		@FindBy(id="days")
		WebElement days;
		
		@FindBy(id="months")
		WebElement months;
		
		@FindBy(id="years")
		WebElement years;
		
		
		public WebElement days() {
			return days;
		}
		
		public WebElement months() {
			return months;
		}
		
		public WebElement years() {
			return years;
		}
		
		
		
		
	//**Address First Name	
		@FindBy(id="firstname")
		WebElement firstname;	
		
		public WebElement firstname_address() {
			return firstname;
		}
		
	//**Address Last Name	
		@FindBy(id="lastname")
		WebElement lastname;	
		
		public WebElement lastname_address() {
			return lastname;
		}
		
	//**Address line 1	
		@FindBy(id="address1")
		WebElement address1;	
		
		public WebElement address1() {
			return address1;
		}
		
	//**City
		@FindBy(id="city")
		WebElement city;	
		
		public WebElement city() {
			return city;
		}
		
	//**State (Select box)			
		@FindBy(id="id_state")
		WebElement id_state;	
				
		public WebElement state() {
			return id_state;
		}	
	
	//**Zip code	
		@FindBy(id="postcode")
		WebElement postcode;	
		
		public WebElement postcode() {
			return postcode;
		}
		
	//**Country (Select box)	
		@FindBy(id="id_country")
		WebElement id_country;	
		
		public WebElement id_country() {
			return id_country;
		}
	
	//**Mobile Phone	
		@FindBy(id="phone_mobile")
		WebElement MobilePhone;	
		
		public WebElement MobilePhone() {
			return MobilePhone;
		}
		
	//**Register button	
		@FindBy(id="//span[contains(text(),'Register')]")
		WebElement Register_button;	
		
		public WebElement Register_button() {
			return MobilePhone;
		}
		
		
		
	
		
}
