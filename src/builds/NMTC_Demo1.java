package builds;

import org.openqa.selenium.WebDriver;

import NMTC.Contact;
import NMTC.Contractor;

public class NMTC_Demo1 {
	
	public static void run(WebDriver driver, String URL, Contact reviewer,double pausetime) {
		System.out.println("Begin: Running NMTC Demo 1");
		Contractor cPage=new Contractor(driver,URL);
		cPage.createContact(reviewer,pausetime);
		cPage.logout();
		System.out.println("End: Running NMTC Demo 1");
	}
}
