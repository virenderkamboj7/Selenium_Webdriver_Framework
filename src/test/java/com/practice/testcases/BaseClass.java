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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import com.google.common.io.Files;
import com.practice.utilities.ReadConfig;

public class BaseClass {
	// public static WebDriver driver;
	protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    protected static ThreadLocal<WebDriverWait> threadLocalWait = new ThreadLocal<>();
	public static Logger logger;

	// Properties file
	ReadConfig readConfig = new ReadConfig(); // Object of properties file
	public String baseURL = readConfig.baseURL(); // Base URL
	public String mobile = readConfig.mobile(); // Mobile Number
	public String password = readConfig.password(); // Password
	public String email = readConfig.email(); // Email
	private String browser = readConfig.getBrowser();

	// public static WebDriverWait wait;

	public static DateTimeFormatter dtf; // Date time formater
	public static LocalDateTime now; // Get local time
	private String gridUrlAsParam = System.getProperty("gridBaseUrlProperty");

	public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static WebDriverWait getWait() {
        return threadLocalWait.get();
    }

	public void setup(String br) {

		WebDriver driver = null; // Local variable for initialization

        if (br.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } 
        else if (br.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } 
        else if(br.equals("hub_chrome")){
            ChromeOptions options = new ChromeOptions();
            URL gridUrl = null;
            logger.debug("Grid url from parameter: " + gridUrlAsParam);
            try {
                if(null == gridUrlAsParam){
                    gridUrl = new URL("http://localhost:4444/wd/hub");
                } else {
                    gridUrl = new URL(gridUrlAsParam);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            driver = new RemoteWebDriver(gridUrl, options);
            logger.info("Grid url: " + gridUrl);
        }

		else if(br.equals("hub_firefox")){
            FirefoxOptions options = new FirefoxOptions();
            URL gridUrl = null;
            logger.debug("Grid url from parameter: " + gridUrlAsParam);
            try {
                if(null == gridUrlAsParam){
                    gridUrl = new URL("http://localhost:4444/wd/hub");
                } else {
                    gridUrl = new URL(gridUrlAsParam);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            driver = new RemoteWebDriver(gridUrl, options);
            logger.info("Grid url: " + gridUrl);
        }

        // Set the ThreadLocal values for the current thread
        if (driver != null) {
            driver.manage().window().maximize();
            threadLocalDriver.set(driver);
            threadLocalWait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(20)));
        }
	}

	 @AfterClass
	public void terDown() {
		if (getDriver() != null) {
            getDriver().quit(); // Close the browser
            threadLocalDriver.remove(); // Clear the thread memory
            threadLocalWait.remove(); // Clear the wait memory
        }
	}

	@BeforeClass
	public void tc() {
		logger = LogManager.getLogger(getClass());
		BaseClass br = new BaseClass();
		String bname = System.getProperty("brwoserName");
		if(null == bname){
			br.setup(browser);
			logger.info("Selecting brwoser from config.properties file ");
		}
		else{
			br.setup(bname);
			logger.info("Selecting brower from mvn param");
		}
		getDriver().get(baseURL);
		logger.info("URL Opened");
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		Files.copy(source, target);
		System.out.println("Screenshot taken");
	}

	@AfterMethod
	public void screenShot(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH.mm.ss");
				now = LocalDateTime.now();
				System.out.println("Current Time is: " + dtf.format(now));
				captureScreen(getDriver(),  dtf.format(now));
				logger.debug(result.getName()+
						"test case failed! Successfully captured a screenshot named as:  "  + dtf.format(now));
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e);
			}
		}
		getDriver().quit();
	}

}
