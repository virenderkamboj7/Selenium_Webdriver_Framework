 Project description:
 	This is a automation test project to test "https://amazon.in/". which have two test cases written for demo purpose.
 	Test Cases: 
 				1. User registration
 				2. Order journey flow
 	
 	This project include 4 packages under "src/test/java" based on the different categories, which are explained below:
 	
 	1. com.practice.pageobject: This package includes the classes where page elements are stored in terms of xpaths, 
 	which are being utilized in actual test cases file of "com.practice.testcases".
 	
 	2. com.practice.testcases: "BaseClass" includes all the properties which are common for all the test cases. 
 	So this class is directly inherited in all the test cases classes.
 	
 	
 	3. com.practice.testData: This package contains the files which have stores the test data to be utilized in test cases.
 	
 	4. com.practice.utilities: This package includes all the helper classes to read the data or reporting configurations. 
 	
 	
 Steps to run the project:
 1. Eclipse should be installed
 2. testng should be installed in eclipse
 4. Now take the pull of framework
 5. Right click on testng.xml file and run as TestNGSuite
 
 You can run the project using cmd also:
 1. Download maven from "https://maven.apache.org/download.cgi" based on your operating system
 2. Set up environment variables
 3. Run cmd and cd the project home directory where you have downloaded 
 4. Execute the command: "mvn clean test -DtestngFile=testng.xml"    (This command will run the testng.xml file)
 	Hence your test can be run without eclipse, which helps to schedule the build using Jenkins.
 	bat file file the there under project home directory. double click on the "run.bat" file. It will execute the testng.xml file.
 5. Logger will tell the steps we followed in every test cases.
 
 
 
 **Please feel to write me "virender.kamboj@yahoo.com" in case on any query or clarification.