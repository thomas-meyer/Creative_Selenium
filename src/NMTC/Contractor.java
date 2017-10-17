package NMTC;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import framework.Page;
import framework.SystemCommands;

public class Contractor extends Page{

	//Navigating Contractor Page
	public By home=By.linkText("Home");
	public String homeTitle="Applicant";
	
	public By organizations=By.linkText("Organizations");
	public String organizationsTitle="Organizations: Home ~ Applicant";
	
	public By contacts=By.linkText("Contacts");
	public String contactsTitle="Contacts: Home ~ Applicant";
	
	public By reviewerProfiles=By.linkText("Reviewer Profiles");
	public String reviewerProfilesTitle="Reviewer Profiles: Home ~ Applicant";
	
	public By applicationReviewTeams=By.linkText("Application Review Teams");
	public String applicationReviewTeamsTitle="Application Review Teams: Home ~ Applicant";
	
	//From Contacts Tab
	public By newContact=By.xpath("//*[@title=\"New Contact\"]");
	public By newBut=By.xpath("//*[@title=\"New\"]");
	public String newTitle="Contact Edit: New Contact ~ Applicant";
	
	//From New Contact Page
	public By go=By.xpath("//*[@title=\"Go!\"]");
	public String contactsListTitle="Contacts ~ Applicant";
	//
	//filling out reviewer Profile
	public By firstNameField=By.id("name_firstcon2");
	public By lastNameField=By.id("name_lastcon2");
	public By emailField=By.id("con15");
	public By orgNameField=By.id("con4");
	//
	//Misc Buttons
	public By save=By.xpath("//*[@title=\"Save\"]");
	public By enableParnterUse=By.partialLinkText("Enable Partner");
	public By profile=By.id("Profile");
	public By portal=By.id("workWithPortalButton");
	public By userLog=By.linkText("Log in to Community as User");
	public By user=By.id("userNavLabel");
	public By logout=By.xpath("//*[@title=\"Logout\"]");
	
	public Contractor(WebDriver driverBeingUsed, String contractorURL){
		Page.driver=driverBeingUsed;
		//ensure fresh login
		String[] loginInfo= {contractorURL};
		login(loginInfo);
	}
	
	@Override
	protected void login(Object[] loginInfo) {
		// TODO Auto-generated method stub
		if(loginInfo instanceof String[]) {
			new Admin(driver, (String) loginInfo[0]);
			//Contractor Login
			SystemCommands.pause(1);
			click(portal);
			SystemCommands.pause(1);
			click(userLog);
		}else {
			System.out.println("ERROR: Program is corrupted");
		}
	}

	public void createContact(Contact newContact, double pauseTime) {
		click(contacts);
		SystemCommands.pause(pauseTime);
		click(newBut);
		SystemCommands.pause(pauseTime);
		type(firstNameField,newContact.getFirstName());
		type(lastNameField,newContact.getLastName());
		type(emailField,newContact.getEmail());
		type(orgNameField,"F2 Solutions LLC");
		SystemCommands.pause(pauseTime);
		click(save);
		SystemCommands.pause(pauseTime);
		click(portal);
		SystemCommands.pause(pauseTime);
		click(enableParnterUse);
		SystemCommands.pause(pauseTime);
		select(profile,"Reviewer");
		SystemCommands.pause(pauseTime);
		click(save);
		SystemCommands.pause(pauseTime);
	}
	
	@Override
	public void logout() {
		click(user);
		click(logout);
		
	}

}
