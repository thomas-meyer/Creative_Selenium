package builds;

import org.openqa.selenium.WebDriver;

import NMTC.Contact;
import NMTC.Reviewer;

public class NMTC_Demo2 {

	public static void run(WebDriver driver, String URL, Contact reviewer,double pausetime) {
		System.out.println("Begin: Running NMTC Demo 2");
		Reviewer rPage=new Reviewer(driver, URL, reviewer);
		rPage.createRevProfile(reviewer,pausetime);
		rPage.logout();
		System.out.println("End: Running NMTC Demo 2");
	}
}
