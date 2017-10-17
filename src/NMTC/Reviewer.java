package NMTC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.Page;
import framework.SystemCommands;

public class Reviewer extends Page {

	public By home=By.linkText("Home");
	public By appLauncher=By.linkText("App Launcher");
	public String homeTitle="Applicant";
	
	public By reviewerProfiles=By.linkText("Reviewer Profiles");
	public String reviewerProfilesTitle="Reviewer Profiles: Home ~ Applicant";
	
	public By scorecards=By.linkText("Scorecards");
	public String scorecardsTitle="Scorecards: Home ~ Applicant";
	
	public By portal=By.id("workWithPortalLabel");
	public By userLog=By.partialLinkText("Log in to Comm");
	public By newRevPro=By.xpath("//*[@title=\"Create New Reviewer Profile\"]");
	public By continueBut=By.xpath("//*[@title=\"Continue\"]");
	public By save=By.xpath("//*[@title=\"Save\"]");
	public By submit=By.xpath("//*[@title=\"Submit for Approval\"]");
	public By recordType=By.id("p3");
	public By formReviewProg=By.xpath("//*[@title=\"Review Program - Available\"]");
	//Form clickables
	public By add=By.xpath("//*[@title=\"Add\"]");
	public By fiscalYear=By.name("00Nt0000000SWhh");
	public By prevExp=By.name("00N35000000aO61");
	public By name=By.id("CF00Nt0000000SWKG");
	public By terms=By.name("00Nt0000000SgVo");
	public By app=By.partialLinkText("REV-");//This is sloppy
	public By review=By.partialLinkText("SC-");//extremely sloppy
	public By addRevCOI=By.name("new_applicant_list_identify_conflicts");
	public By addLedCOI=By.name("pending_application");
	public By correctCOI=By.name("pgApplicationUpdate:frmApplicationUpdate:pbApplicationUpdate:j_id40:j_id42");
	public By readCOI=By.name("pgApplicationUpdate:frmApplicationUpdate:pbApplicationUpdate:j_id40:j_id41");
	public By exitCOI=By.name("pgApplicationUpdate:frmApplicationUpdate:pbApplicationUpdate:j_id36:j_id38");
	public By saveCOI=By.name("pgApplicationUpdate:frmApplicationUpdate:pbApplicationUpdate:j_id36:j_id37");
	//Misc Buttons
	public By newBut=By.id("createNewButton");
	public By sideBar=By.id("pinIndicator");
	public By go=By.name("go");
	public By startRevi=By.name("new_view_scorecard");
	//OTher
	
	
	public Reviewer(WebDriver driverBeingUsed, String sandboxURL, Contact reviewer){
		Page.driver=driverBeingUsed;
		Object[] loginInfo= {sandboxURL,reviewer};
		//refresh
		login(loginInfo);
	}
	
	@Override
	protected void login(Object[] loginInfo) {
		if(loginInfo[0] instanceof String) {
			new Admin(driver, (String) loginInfo[0]);
		}
		if(loginInfo[1] instanceof Contact) {
			Contact searchFor=(Contact) loginInfo[1];
			String name=searchFor.getFirstName()+", "+searchFor.getLastName();
			SystemCommands.pause(4);
			type(By.id("phSearchInput"),name);
			click(By.id("phSearchButton"));
			SystemCommands.pause(1);
			click(By.partialLinkText(searchFor.getFirstName().substring(0,4)));
			SystemCommands.pause(1);
			click(portal);
			SystemCommands.pause(1);
			click(userLog);
		}
		
	}

	@Override
	public void logout() {
		click(By.id("userNavLabel"));
		click(By.xpath("//*[@title=\"Logout\"]"));
	}
	
	public void createRevProfile(Contact reviewer, double pauseTime) {
		SystemCommands.pause(pauseTime);
		click(reviewerProfiles);
		SystemCommands.pause(pauseTime);
		click(newRevPro);
		SystemCommands.pause(pauseTime);
		select(recordType, 2);
		click(continueBut);
		SystemCommands.pause(pauseTime);
		select(formReviewProg, 0);
		click(add);
		select(fiscalYear, 2);
		if(reviewer.getExp()) {
			select(prevExp,1);
		}else {
			select(prevExp,2);
		}
		type(name,reviewer.getLastName()+" "+reviewer.getFirstName());
		select(terms, 1);
		SystemCommands.pause(pauseTime);
		Boolean processed=false;int infCount=0;
		while(!processed & infCount!=300) {
			SystemCommands.pause(1);
			click(save);
			if(!getTitle().equals("Reviewer Profile Edit: New Reviewer Profile ~ Applicant")) {
				processed=true;
			}
			infCount++;
		}
		if(infCount!=300) {
			SystemCommands.pause(pauseTime);
			click(submit);
			SystemCommands.pause(pauseTime);
			accept();
			SystemCommands.pause(pauseTime);
		}else {
			System.out.println("LOOPING ERROR: System couldn't find Reviewer's name.");
		}
	}
}
