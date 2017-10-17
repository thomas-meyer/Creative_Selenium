//This package should contain Selenium add-ons
package execute;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import NMTC.Contact;
import builds.NMTC_Demo1;
import builds.NMTC_Demo2;



public class run{
	
	public static void main(String[] args) throws InterruptedException, IOException {
		////Sets up the output file
		DateFormat dateFormat = new SimpleDateFormat("MM.dd_HH.mm");
		Date date = new Date();
		File debugg = null;
		try {
			debugg = new File("QA_Results_"+dateFormat.format(date)+".txt");
	        System.setOut(new PrintStream(debugg));
			dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yy");
			System.out.println("QA Automation BEGIN run at: "+dateFormat.format(date));
	    } catch (Exception e) {
	         e.printStackTrace();
	    }
		////Output file has now been set up
		
		////Sets up the browser for use.  
		//Uncomment the browser you wish to use an make sure
		//the proper .exe file is in the directory
		WebDriver driver=chromeSetup();//Setup for Chrome
		//WebDriver driver = new FirefoxDriver();//Setup for Firefox
		//WebDriver driver=IESetup(); //Setup for Internet Explorer
		////Browser has now been set up
		
		//UAT
		//https://cdfi1--CDFIUAT01.cs33.my.salesforce.com
		
		//QA
		//https://cdfi1--cdfiqa01.cs33.my.salesforce.com
		
		//Dev
		//https://cdfi1--cmfdev.cs32.my.salesforce.com/
		////***THE CODE YOU WISH TO RUN GOES HERE!***
		////
		///
	
		
		
		
		
		
		Contact DemoMan=new Contact(8797);
		double pausetime=2;
		
		//Demo 1
		//NMTC_Demo1.run(driver,"https://cdfi1--cdfiqa01.cs33.my.salesforce.com/00335000003Pu9S",DemoMan,pausetime);
		
		//Demo 2
		NMTC_Demo2.run(driver,"https://cdfi1--cdfiqa01.cs33.my.salesforce.com",DemoMan,pausetime);
		
		
		
		
		
		
		


		

		
		
		

		
		
		
		/////****THE CODE YOU ARE RUNNING HOULD BE OVER****
		////
		////
		

		try {
			//Puts the end signature on the file
			date = new Date();
			dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yy");
			System.out.println("QA Automation END run at: "+dateFormat.format(date));
	        debugg.setReadOnly();
	    } catch (Exception e) {
	         e.printStackTrace();
	    }
	}
	
	//Browser Setup Section
		//Sets up a chrome browser
		public static WebDriver chromeSetup() {
			String chromeDriveLoc="chromedriver.exe";
			System.setProperty("webdriver.chrome.driver",chromeDriveLoc);
			WebDriver driver = new ChromeDriver();
			return driver;
		}
}