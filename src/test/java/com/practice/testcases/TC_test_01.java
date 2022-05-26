package com.practice.testcases;

import java.io.IOException;

import javax.lang.model.element.Element;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.practice.pageobjects.HomepageObj;
import com.practice.pageobjects.LoginPage;
import com.practice.pageobjects.RegistrationForm;
import com.practice.utilities.XLUtils;

public class TC_test_01 extends BaseClass {

	
	@Test
	public void signin() throws IOException, InterruptedException {
		
	//Initialization of Page object classes	
		HomepageObj homepageObj=PageFactory.initElements(driver, HomepageObj.class); //Page object of 'Homepage'
		LoginPage LoginPage=PageFactory.initElements(driver, LoginPage.class); //Page objects of 'Login page'
		RegistrationForm RegistrationForm=PageFactory.initElements(driver, RegistrationForm.class); //Page objects of 'Registration Form' 
		
		
	//	Initialization of helper classes
		JavascriptExecutor js = (JavascriptExecutor) driver;  //Page scroll
		
		String UserDataExcelPath= "./src\\test\\java\\com\\practice\\testData\\UserData.xlsx";  //Excel path
		
		logger.info("*********Test Case 'TC_test_01' is started***********");
		
	//1. Click on Navigation icon	
		homepageObj.nav_icon().click();
		logger.info("Clicked on Navigation icon");
		
	//2. Click on Sign in button	
		homepageObj.signin().click();
		logger.info("Clicked on Sign in button");
		
	//3. Click on "Create an Account" button
		wait.until(ExpectedConditions.elementToBeClickable(homepageObj.createAccountSubmit()));
		homepageObj.createAccountSubmit().click();
		logger.info("Clicked on \"Create an Account\" button");
	
	//4. Enter User name	
		wait.until(ExpectedConditions.elementToBeClickable(homepageObj.customer_name_field()));
		homepageObj.customer_name_field().sendKeys(XLUtils.getCellData(UserDataExcelPath, "ValidData", 2, 1)+" "); //Enter First Name
		homepageObj.customer_name_field().sendKeys(XLUtils.getCellData(UserDataExcelPath, "ValidData", 3,1 )); // Enter Last Name
		logger.info("User name is entered");
		
	//5. Enter email id	
		homepageObj.email_field().sendKeys(XLUtils.getCellData(UserDataExcelPath, "ValidData", 1,1 ));
		logger.info("Email id is entered");
	
	//6. Click on country code button	
		homepageObj.country_code().click();
		logger.info("Clicked in country code button");
		
	//7. Select +91 as county code	
		homepageObj.country_code_select().click();
		logger.info("+91 is selected as country code");
		
	//8. Enter Mobile Number
		homepageObj.mobile_field().sendKeys(XLUtils.getCellData(UserDataExcelPath, "ValidData", 20,1 ));
		logger.info("Mobile number is entered");
		
	//9. Enter Password	
		homepageObj.password_field().sendKeys(XLUtils.getCellData(UserDataExcelPath, "ValidData", 4,1 ));
		logger.info("Password is entered");
		
		logger.info("\n");
		logger.info("*********Test Case 'TC_test_01' is end***********");
	}
	
}

