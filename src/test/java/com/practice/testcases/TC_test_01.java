package com.practice.testcases;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import com.practice.pageobjects.HomepageObj;
import com.practice.utilities.TokenCryptoUtils;
import com.practice.utilities.XLUtils;

public class TC_test_01 extends BaseClass {

	@Test(description = "Amazon registration")
	public void signin() throws IOException {
		
	//Initialization of Page object classes	
		HomepageObj homepageObj=PageFactory.initElements(driver, HomepageObj.class); //Page object of 'Homepage'
		String UserDataExcelPath= "./src\\test\\java\\com\\practice\\testData\\UserData.xlsx";  //Excel path
		
		logger.info("*********Test Case 'TC_test_01' is started***********");
		
	//1. Click on Navigation icon	
		wait.until(ExpectedConditions.elementToBeClickable(homepageObj.nav_icon())).click();
		logger.info("Clicked on Navigation icon");
		
	//2. Enter email id	
		homepageObj.emailField().sendKeys(XLUtils.getCellData(UserDataExcelPath, "ValidData", 1,1 ));
		logger.info("Clicked on Sign in button");

	//3. Click on Continue button
		wait.until(ExpectedConditions.elementToBeClickable(homepageObj.continueButton())).click();
		logger.info("Clicked on Continue button");
	
	//5. Click on Proceeed to Create ans Account Button
		homepageObj.proceedButton().click();
	
	//6. Enter mobile number
	wait.until(ExpectedConditions.visibilityOf(homepageObj.mobile())).sendKeys(XLUtils.getCellData(UserDataExcelPath, "ValidData", 20,1 ));
	
	//7. Enter User name
	String username = XLUtils.getCellData(UserDataExcelPath, "ValidData", 2,1 ) + " "+XLUtils.getCellData(UserDataExcelPath, "ValidData", 3,1 );
	homepageObj.userName().sendKeys(username);

	//8. Enter password
		String password = null;
		try {
			password = TokenCryptoUtils.decrypt(XLUtils.getCellData(UserDataExcelPath, "ValidData", 4,1 ), System.getenv("FRAMEWORK_ENCRYPTION_KEY"));
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		homepageObj.passwordField().sendKeys(password);
		logger.info("Password is entered");
	
		logger.info("\n");
		logger.info("*********Test Case 'TC_test_01' is end***********");
	}
	
}

