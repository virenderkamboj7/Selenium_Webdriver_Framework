package com.practice.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.practice.testcases.BaseClass;

public class Categories extends BaseClass{

	
	//Cameras button
	@FindBy(xpath="//div[@id='nav-subnav']//a[6]//span[1]")
	//@CacheLookup
	WebElement cameras_button;
		
	public WebElement cameras_button() {
	return cameras_button;	
	}
	
	
	//Nikon button
	@FindBy(xpath="//a[contains(text(),'Nikon')]")
	//@CacheLookup
	WebElement nikon_button;
		
	public WebElement nikon_button() {
	return nikon_button;	
	}		
	
	
	//First Product
	@FindBy(xpath="//div[@id='anonCarousel1']//div[@id='acs-product-block-0']//a[@class='a-color-base a-spacing-none a-link-normal acs-product-block__product-title']//span[@class='a-truncate-cut']")
	//@CacheLookup
	WebElement first_product;
		
	public WebElement first_product() {
	return first_product;	
	}
	
	//"Buy Now" Button
	@FindBy(xpath="//input[@id='buy-now-button']")
	//@CacheLookup
	WebElement Buy_Now_button;
		
	public WebElement Buy_Now_button() {
	return Buy_Now_button;	
	}	
	
	
	
	
}
