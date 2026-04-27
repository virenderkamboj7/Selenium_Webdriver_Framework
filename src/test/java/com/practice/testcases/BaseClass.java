package com.practice.testcases;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import java.time.Duration;

import com.google.common.io.Files;
import com.practice.utilities.ReadConfig;

public class BaseClass {
	public static WebDriver driver;
	public static Logger logger;

	// Properties file
	ReadConfig readConfig = new ReadConfig(); // Object of properties file
	public String baseURL = readConfig.baseURL(); // Base URL
	public String chromedriverpath = readConfig.chromedriver(); // Chromepath
	public String firefoxpath = readConfig.firefoxdriver(); // Firefox path
	public String mobile = readConfig.mobile(); // Mobile Number
	public String password = readConfig.password(); // Password
	public String email = readConfig.email(); // Email

	public static WebDriverWait wait;

	public static DateTimeFormatter dtf; // Date time formater
	public static LocalDateTime now; // Get local time

//	@BeforeClass
	public void setup(String br) {

		if (br.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		}

		else if (br.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		}

		else if(br.equals("hub_chrome")){
			ChromeOptions options = new ChromeOptions();
			URL gridUrl=null;
			try {
				gridUrl = new URL("http://localhost:4444/wd/hub");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver = new RemoteWebDriver(gridUrl, options);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		}

		else if(br.equals("hub_firefox")){
			FirefoxOptions options = new FirefoxOptions();
			URL gridUrl=null;
			try {
				gridUrl = new URL("http://localhost:4444/wd/hub");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver = new RemoteWebDriver(gridUrl, options);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		}
	}

	 @AfterClass
	public void terDown() {
		driver.quit();
	}

	@Parameters("brr") 
	@BeforeClass
	public void tc(String brr) {
	// public void tc() {
		logger = LogManager.getLogger(getClass());
		new File(System.getProperty("user.dir"), "log").mkdirs();
		BaseClass br = new BaseClass();
		br.setup(brr);
		driver.get(baseURL);
		logger.info("URL Opened");
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		Files.copy(source, target);
		System.out.println("Screenshot taken");
//		C:\Users\orange\eclipse-workspace\Selenium_Test_Framework\Screenshots
	}

//	@AfterMethod
	public void screenShot(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH.mm.ss");
				now = LocalDateTime.now();
				System.out.println("Current Time is: " + dtf.format(now));
//				captureScreen(driver, driver.getTitle() + dtf.format(now));
				captureScreen(driver,  dtf.format(now));

//				System.out.println(
//						"Successfully captured a screenshot named as: " + driver.getTitle() + " " + dtf.format(now));
				System.out.println(
						"Successfully captured a screenshot named as:  "  + dtf.format(now));
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e);
//				.getMessage()
			}
		}
		driver.quit();
	}

}
