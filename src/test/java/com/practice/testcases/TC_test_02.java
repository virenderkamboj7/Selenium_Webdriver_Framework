package com.practice.testcases;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.practice.pageobjects.Categories;
import com.practice.pageobjects.HomepageObj;

public class TC_test_02 extends BaseClass {
	
	@Test
	public void profuct_flow() throws InterruptedException {
		
		//Initialization of Page object classes	
				HomepageObj homepageObj=PageFactory.initElements(driver, HomepageObj.class); //Page object of 'Homepage'
				Categories categories=PageFactory.initElements(driver, Categories.class); //Page object of 'Categories'
				
				
		//Initialization of helper classes		
				Actions act=new Actions(driver);
				
				logger.info("*********Test Case 'TC_test_02' is started***********");
				
		//1. Click on "Computer" button		
				
//				wait.until(ExpectedConditions.elementToBeClickable(homepageObj.computer_button()));
				act.moveToElement(homepageObj.computer_button()).click().build().perform();
				homepageObj.computer_button().click();
				logger.info("Clicked on \"Electronics\" button");
		
		//2. Hover over "Cameras" button		
				act.moveToElement(categories.cameras_button()).build().perform();
				logger.info("Hover over \"Cameras\" button");
				
		//3. Click on "Nikon" button	
				wait.until(ExpectedConditions.elementToBeClickable(categories.nikon_button())).click();
				logger.info("Clicked on \"Nikon\" button");
				
		//4. Click on first product
				categories.first_product().click();
				logger.info("Clicked on first product");
				
		//5. Click on "Buy Now" button
				categories.Buy_Now_button().click();
				logger.info("Click on \"Buy Now\" button");
				
		
				logger.info("\n");
				logger.info("*********Test Case 'TC_test_02' is end***********");
			
	}

}

