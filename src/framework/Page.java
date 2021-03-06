package framework;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.Select;

import framework.SystemCommands;

//Page is an abstract class that serves as the foundations for all of our Selenium integrated classes.
//The functions here are applied to all of our class and have built in debugging.
//NOTE: Page itself is not a stand alone class and can't be called itself
public abstract class Page {
	//Once defined, enables web-browser interactions
	public static WebDriver driver;

	//A login method is required for all pages.  However since the login method isn't universal
	//it is not explicitly define in this class, but rather left up to the other classes to define.
	protected abstract void login(Object[] loginInfo);

	public abstract void logout();
	
	//Returns the header of a webpage
	//This is useful for verifying that
	//you have navigated to the proper webpage
	public String getTitle(){
	        return Page.driver.getTitle();
	}
	
	//Verifies that the thing you are trying to access
	//actually exists on the page.  If it doesn't exist,
	//the program will write to the System.out source 
	//and return false.
	public boolean elementExists(By element) {
		try {
			Page.driver.findElement(element);
			return true;
		}catch(NoSuchElementException noEl) {
			System.out.print("FAILED TO FIND: ("+element.toString()+")");
			return false;
		}catch(ElementNotVisibleException hidEl) {
			System.out.print("HIDDEN ELEMENT: ("+element.toString()+") ");
			return false;
		}
	}
	
	//Function to click button
	//Returns true if the button exists
	//and was able to be clicked
	public boolean click(By button) {
		if(this.elementExists(button)) {
			try {
				driver.findElement(button).click();
				return true;
			}catch(UnsupportedCommandException e) {
				try {
					driver.findElement(button).sendKeys(Keys.ENTER);
					return true;
				}catch(UnsupportedCommandException e2) {
					System.out.println("FAILED TO CLICK: ("+button.toString()+")");
					return false;
				}catch(WebDriverException w2) {
					System.out.println("FAILED TO CLICK: ("+button.toString()+")");
					return false;
				}
			}catch(WebDriverException w) {
				try {
					driver.findElement(button).sendKeys(Keys.ENTER);
					return true;
				}catch(UnsupportedCommandException e2) {
					System.out.println("FAILED TO CLICK: ("+button.toString()+")");
					return false;
				}catch(WebDriverException w2) {
					System.out.println("FAILED TO CLICK: ("+button.toString()+")");
					return false;
				}
			}
		}else {
			System.out.println(" \"clickable button\"");
			return false;
		}
	}
	
	//Function to enter information into a field
	//Returns true if the field exists and information
	//was able to be entered into it
	public boolean type(By field, String input) {
		if(this.elementExists(field)) {
			try {
				driver.findElement(field).clear();
				driver.findElement(field).sendKeys(input);
				return true;
			}catch(UnsupportedCommandException e) {
				System.out.println("FAILED TO ENTER information into the field: ("+field.toString()+")");
				return false;
			}catch(WebDriverException w) {
				System.out.println("FAILED TO ENTER information into the field: ("+field.toString()+")");
				return false;
			}
		}else {
			System.out.println(" \"field\"");
			return false;
		}
	}
	
	//Function to select option from a picklist
	//Returns true if the picklist exists and
	//the option is able to be selected
	public boolean select(By selectMenu,String selectOption) {
		if(this.elementExists(selectMenu)) {
			try {
				Select menu=new Select(driver.findElement(selectMenu));
				menu.selectByVisibleText(selectOption);
				return true;
			}catch(NoSuchElementException e) {
				System.out.println("FAILED TO SELECT VALUE: \""+selectOption+"\" from the picklist ("+selectMenu.toString()+")");
				return false;
			}catch(UnsupportedCommandException e) {
				System.out.println("FAILED TO SELECT: ("+selectMenu.toString()+")");
				return false;
			}catch(WebDriverException w) {
				System.out.println("FAILED TO SELECT: ("+selectMenu.toString()+")");
				return false;
			}
		}else {
			System.out.println(" \"picklist\"");
			return false;
		}
	}
	
	//**This lazier and should be avoided**
	public boolean select(By selectMenu,int selectOption) {
		if(this.elementExists(selectMenu)) {
			try{
				Select menu=new Select(driver.findElement(selectMenu));
				menu.selectByIndex(selectOption);
				return true;
			}catch(NoSuchElementException e) {
				System.out.println("FAILED TO SELECT the "+Integer.toString(selectOption)+" value from the picklist ("+selectMenu.toString()+")");
				return false;
			}
		}else {
			System.out.println(" \"picklist\"");
			return false;
		}
	}
	
	
	public boolean webPage(String websiteURL, String expectedHeading) {
		if(driver!=null) {
			try{
				driver.navigate().to(websiteURL);
				if(expectedHeading.equals("skip")) {
					return true;
				}
				if(driver.getTitle().equals(expectedHeading))  {
					return true;
				}else {
					System.out.println("FAILED TO NAVIGATE to \""+expectedHeading+"\".  TRYING AGAIN");
					SystemCommands.pause(2);
					if(driver.getTitle().equals(expectedHeading)) {
						return true;
					}else {
						System.out.println("FAILED TO NAVIGATE to \""+expectedHeading+"\".  TRYING AGAIN");
						SystemCommands.pause(2);
						if(driver.getTitle().equals(expectedHeading)) {
						return true;
						}else {
							System.out.println("FAILED TO NAVIGATE to \""+expectedHeading+"\".");
							return false;
						}
					}
				}
			}catch(WebDriverException e) {
				System.out.println("URL ERROR: verify that URL starts with http:\\\\");
				return false;
			}catch(NullPointerException e) {
				System.out.println("URL ERROR: verify that URL starts with http:\\\\");
				return false;
			}
		}else {
			System.out.print("BROWSER NOT AVALIBLE");
			return false;
		}
	}
	
	//Accepts any pop-up
	public void accept() {
		try{
			driver.switchTo().alert().accept();
		}catch(NoAlertPresentException e) {
			System.out.println("EXPECTED ALERT: no alert detected");
		}
	}
	
	//public boolean tab(String tabTitle) {
		
	//}
	
	public String linkLocation(By link) {
		if(elementExists(link)) {
			try{
				String result=driver.findElement(link).getAttribute("hef");
				if(result!=null) {
					return result;
				}else {
					System.out.println("LINK ERROR: couldn't retrieve link address");
					return " ";
				}
			}catch(WebDriverException e) {
				System.out.println("LINK ERROR: couldn't retrieve link address");
				return " ";
			}
		}
		return " ";
	}
	
}