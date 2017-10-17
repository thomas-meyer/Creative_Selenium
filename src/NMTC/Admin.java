package NMTC;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.Page;
import framework.SystemCommands;

public class Admin extends Page{

	public Admin(WebDriver theDriver, String sandboxURL) {
			Page.driver=theDriver;
			Object[] loginInput= {sandboxURL};
			login(loginInput);
	}


	@Override
	protected void login(Object[] loginInfo) {
		if(loginInfo[0] instanceof String) {
			String sandBoxURL=(String) loginInfo[0];
			//Salesforce Admin Login
			if(!webPage(sandBoxURL,"skip")) {
				System.out.println("LOGIN ERROR: remember to logout, before logging back in");
			}else {
				try {
					String[] creditials=SystemCommands.getLoginCred();
					type(By.id("username"), creditials[0]);
					type(By.id("password"), creditials[1]);
				}catch(IOException f) {
					System.out.println("MISSING FILE: couldn't find \"loginInfo.txt\"");
				}
				click(By.id("Login"));
			}
		}	
	}

	@Override
	public void logout() {
		click(By.id("userNavLabel"));
		click(By.xpath("//*[@title=\"Logout\"]"));
	}

}
