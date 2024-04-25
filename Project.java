package org.end;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Project {
	
	WebDriver driver;
	long starttime;
	long endtime;
	Select select;
	String parentwindow;
	Set<String> childwindow;
	Alert alert;
	Actions action;

	@BeforeTest
	@Parameters("url")
	public void launchBrowser(String Url) { 
		starttime = System.currentTimeMillis();
		System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver(); // Launch the Browser
		driver.manage().window().maximize(); // Maximize window
		driver.get(Url); // Enter the URL
	}

	@Test(priority = 0)
	@Parameters({"Name","Email","Phone","Address"})
	public void fillTextBox(String name, String mail,String phone, String address) { // Verify the Text box
		
		WebElement TextName = driver.findElement(By.id("name")); // Find the Name text box, enter value
		TextName.sendKeys(name);

		WebElement TextEmail = driver.findElement(By.id("email")); // Find the Email text box, enter value
		TextEmail.sendKeys(mail);

		WebElement TextPhone = driver.findElement(By.id("phone")); // Find the Phone text box, enter value
		TextPhone.sendKeys(phone);

		WebElement TextAddress = driver.findElement(By.id("textarea")); // Find the Address text box, enter value
		TextAddress.sendKeys(address);
	}

	@Test(priority = 1)
	public void radioButton() {// Verify Radio button	
		WebElement SelectMale = driver.findElement(By.id("male"));
		SelectMale.click();
	}

	@Test(priority = 2)
	public void checkBox() {// Verify Multiple check box		
		List<WebElement> element = driver.findElements(By.xpath("//input[@type='checkbox' and contains(@id,'day')]"));
		for (WebElement allElements : element) {
		allElements.click();
		}
	}

	@Test(priority = 3)
	@Parameters("Country")
	public void dropDown(String country) {// Verify DropDown list	
		WebElement dropdown = driver.findElement(By.id("country"));
		select = new Select(dropdown);
		select.selectByValue(country);
	}

	@Test(priority = 4)
	public void selectable() {// Verify Multiple colors select
		List<WebElement> selectable = driver.findElements(By.xpath("//select[@id='colors']/option"));
		for (WebElement allcolor : selectable) {
		allcolor.click();
		}
	}

	@Test(priority = 5)
	public void calendarDate() {// Verify the date Calendar
		WebElement date = driver.findElement(By.id("datepicker"));
		date.click();
		WebElement selectdate = driver.findElement(By.xpath("(//a[@class='ui-state-default'])[18]")); 
		selectdate.click(); // select the date - 18/03/2024
	}

	@Test(priority = 6)
	@Parameters("OC Url")
	public void opencartLink(String url) { // Verify Oprncart
		WebElement opencart = driver.findElement(By.xpath("//a [@href='https://demo.opencart.com/']")); // click opencart
		opencart.click();
		String opencarturl =url;
		Assert.assertEquals(opencarturl, driver.getCurrentUrl()); // Verify the opencart url
		driver.navigate().back();
	}

	@Test(priority = 7)
	@Parameters("OHRM Url")
	public void orangeHrmLink(String URL) { // Verify Orange HRM
		WebElement HRM = driver.findElement(By.xpath("//a[@href='https://opensource-demo.orangehrmlive.com/web/index.php/auth/login']"));
		HRM.click();
		String OrangeHRMurl =URL;
		Assert.assertEquals(OrangeHRMurl, driver.getCurrentUrl()); // Verify the OrangeHRM url
		driver.navigate().back();
	}

	@Test(priority = 8)
	public void postsAtomLink() {// Verify Posts Atom Link		
		WebElement clkpostsatom = driver.findElement(By.className("feed-link"));
		clkpostsatom.click();

		parentwindow = driver.getWindowHandle(); // parent window
		childwindow = driver.getWindowHandles(); // child window

		for (String chlid : childwindow) { // Store all windows
			driver.switchTo().window(chlid);
		}
		driver.close();
		driver.switchTo().window(parentwindow); // Switch to parent window
	}

	@Test(priority = 9)
	public void table() { 
		// Count Table column and get the table all columns
		List<WebElement> columntable = driver.findElements(By.tagName("th"));
		int columncount = columntable.size();
		System.out.println("Number of column is " + columncount);

		// Count table Row
		List<WebElement> Rowtable = driver.findElements(By.tagName("tr"));
		int rowcount = Rowtable.size();
		System.out.println("Number of Row is " + rowcount);

		// Get all row datas
		List<WebElement> allrow = driver.findElements(By.tagName("tr"));
		for (WebElement allrows : allrow) {
			String data = allrows.getText();
			System.out.println(data);
		}
	}

	@Test(priority = 10)
	public void paginationTable() {// Select Pagination Table		
		WebElement click3rdoption = driver.findElement(By.xpath("(//a[@href='#'])[3]")); // find the 3rd page
		click3rdoption.click(); 

		List<WebElement> allbox = driver.findElements(By.xpath("(//input[@type='checkbox'])[8]")); // select product 11
		for (WebElement allboxs : allbox) {
		allboxs.click();
		}
	}

	@Test(priority = 11)
	@Parameters("Tabs")
	public void tabsLink(String tab) {// Verify the Tabs	
		WebElement Tabs = driver.findElement(By.id("Wikipedia1_wikipedia-search-input"));
		Tabs.sendKeys(tab + Keys.ENTER);

		WebElement seleniumbiology = driver.findElement(By.xpath("(//a[@target='_blank'])[4]"));
		seleniumbiology.click();

		parentwindow = driver.getWindowHandle(); // parent window
		childwindow = driver.getWindowHandles(); // child window

		for (String childwindows : childwindow) {
			driver.switchTo().window(childwindows); 
		}
		driver.switchTo().window(parentwindow); // Switch to parent window
	}

	@Test(priority = 12)
	public void newBrowserWindow() { // Verify the New Browser Window
		WebElement clicknewbrowser = driver.findElement(By.xpath("//button[contains(text(),'New Browser Window')]"));
		clicknewbrowser.click();

		parentwindow = driver.getWindowHandle(); // Parent Window
		childwindow = driver.getWindowHandles(); //Child Window

		for (String newchildwindows : childwindow) {
			driver.switchTo().window(newchildwindows); // Switch to new Window
		}
		driver.close(); 
		driver.switchTo().window(parentwindow); // Switch to parent window
	}

	@Test(priority = 13)
	@Parameters("Prompt Value")
	public void jsAlert(String value){
		// click Alert Button
		WebElement clickalert = driver.findElement(By.xpath("//button[@onclick='myFunctionAlert()']"));
		clickalert.click();

		alert = driver.switchTo().alert();
		alert.accept();

		// Click Confirm Button
		WebElement confirmbtn = driver.findElement(By.xpath("//button[contains(text(),'Confirm Box')]"));
		confirmbtn.click();
		alert.dismiss();

		// Click Prompt Button
		WebElement promptbtn = driver.findElement(By.xpath("//button[@onclick='myFunctionPrompt()']"));
package org.end;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Project {
	
	WebDriver driver;
	long starttime;
	long endtime;
	Select select;
	String parentwindow;
	Set<String> childwindow;
	Alert alert;
	Actions action;

	@BeforeTest
	@Parameters("url")
	public void launchBrowser(String Url) { 
		starttime = System.currentTimeMillis();
		System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver(); // Launch the Browser
		driver.manage().window().maximize(); // Maximize window
		driver.get(Url); // Enter the URL
	}

	@Test(priority = 0)
	@Parameters({"Name","Email","Phone","Address"})
	public void fillTextBox(String name, String mail,String phone, String address) { // Verify the Text box
		
		WebElement TextName = driver.findElement(By.id("name")); // Find the Name text box, enter value
		TextName.sendKeys(name);

		WebElement TextEmail = driver.findElement(By.id("email")); // Find the Email text box, enter value
		TextEmail.sendKeys(mail);

		WebElement TextPhone = driver.findElement(By.id("phone")); // Find the Phone text box, enter value
		TextPhone.sendKeys(phone);

		WebElement TextAddress = driver.findElement(By.id("textarea")); // Find the Address text box, enter value
		TextAddress.sendKeys(address);
	}

	@Test(priority = 1)
	public void radioButton() {// Verify Radio button	
		WebElement SelectMale = driver.findElement(By.id("male"));
		SelectMale.click();
	}

	@Test(priority = 2)
	public void checkBox() {// Verify Multiple check box		
		List<WebElement> element = driver.findElements(By.xpath("//input[@type='checkbox' and contains(@id,'day')]"));
		for (WebElement allElements : element) {
		allElements.click();
		}
	}

	@Test(priority = 3)
	@Parameters("Country")
	public void dropDown(String country) {// Verify DropDown list	
		WebElement dropdown = driver.findElement(By.id("country"));
		select = new Select(dropdown);
		select.selectByValue(country);
	}

	@Test(priority = 4)
	public void selectable() {// Verify Multiple colors select
		List<WebElement> selectable = driver.findElements(By.xpath("//select[@id='colors']/option"));
		for (WebElement allcolor : selectable) {
		allcolor.click();
		}
	}

	@Test(priority = 5)
	public void calendarDate() {// Verify the date Calendar
		WebElement date = driver.findElement(By.id("datepicker"));
		date.click();
		WebElement selectdate = driver.findElement(By.xpath("(//a[@class='ui-state-default'])[18]")); 
		selectdate.click(); // select the date - 18/03/2024
	}

	@Test(priority = 6)
	@Parameters("OC Url")
	public void opencartLink(String url) { // Verify Oprncart
		WebElement opencart = driver.findElement(By.xpath("//a [@href='https://demo.opencart.com/']")); // click opencart
		opencart.click();
		String opencarturl =url;
		Assert.assertEquals(opencarturl, driver.getCurrentUrl()); // Verify the opencart url
		driver.navigate().back();
	}

	@Test(priority = 7)
	@Parameters("OHRM Url")
	public void orangeHrmLink(String URL) { // Verify Orange HRM
		WebElement HRM = driver.findElement(By.xpath("//a[@href='https://opensource-demo.orangehrmlive.com/web/index.php/auth/login']"));
		HRM.click();
		String OrangeHRMurl =URL;
		Assert.assertEquals(OrangeHRMurl, driver.getCurrentUrl()); // Verify the OrangeHRM url
		driver.navigate().back();
	}

	@Test(priority = 8)
	public void postsAtomLink() {// Verify Posts Atom Link		
		WebElement clkpostsatom = driver.findElement(By.className("feed-link"));
		clkpostsatom.click();

		parentwindow = driver.getWindowHandle(); // parent window
		childwindow = driver.getWindowHandles(); // child window

		for (String chlid : childwindow) { // Store all windows
			driver.switchTo().window(chlid);
		}
		driver.close();
		driver.switchTo().window(parentwindow); // Switch to parent window
	}

	@Test(priority = 9)
	public void table() { 
		// Count Table column and get the table all columns
		List<WebElement> columntable = driver.findElements(By.tagName("th"));
		int columncount = columntable.size();
		System.out.println("Number of column is " + columncount);

		// Count table Row
		List<WebElement> Rowtable = driver.findElements(By.tagName("tr"));
		int rowcount = Rowtable.size();
		System.out.println("Number of Row is " + rowcount);

		// Get all row datas
		List<WebElement> allrow = driver.findElements(By.tagName("tr"));
		for (WebElement allrows : allrow) {
			String data = allrows.getText();
			System.out.println(data);
		}
	}

	@Test(priority = 10)
	public void paginationTable() {// Select Pagination Table		
		WebElement click3rdoption = driver.findElement(By.xpath("(//a[@href='#'])[3]")); // find the 3rd page
		click3rdoption.click(); 

		List<WebElement> allbox = driver.findElements(By.xpath("(//input[@type='checkbox'])[8]")); // select product 11
		for (WebElement allboxs : allbox) {
		allboxs.click();
		}
	}

	@Test(priority = 11)
	@Parameters("Tabs")
	public void tabsLink(String tab) {// Verify the Tabs	
		WebElement Tabs = driver.findElement(By.id("Wikipedia1_wikipedia-search-input"));
		Tabs.sendKeys(tab + Keys.ENTER);

		WebElement seleniumbiology = driver.findElement(By.xpath("(//a[@target='_blank'])[4]"));
		seleniumbiology.click();

		parentwindow = driver.getWindowHandle(); // parent window
		childwindow = driver.getWindowHandles(); // child window

		for (String childwindows : childwindow) {
			driver.switchTo().window(childwindows); 
		}
		driver.switchTo().window(parentwindow); // Switch to parent window
	}

	@Test(priority = 12)
	public void newBrowserWindow() { // Verify the New Browser Window
		WebElement clicknewbrowser = driver.findElement(By.xpath("//button[contains(text(),'New Browser Window')]"));
		clicknewbrowser.click();

		parentwindow = driver.getWindowHandle(); // Parent Window
		childwindow = driver.getWindowHandles(); //Child Window

		for (String newchildwindows : childwindow) {
			driver.switchTo().window(newchildwindows); // Switch to new Window
		}
		driver.close(); 
		driver.switchTo().window(parentwindow); // Switch to parent window
	}

	@Test(priority = 13)
	@Parameters("Prompt Value")
	public void jsAlert(String value){
		// click Alert Button
		WebElement clickalert = driver.findElement(By.xpath("//button[@onclick='myFunctionAlert()']"));
		clickalert.click();

		alert = driver.switchTo().alert();
		alert.accept();

		// Click Confirm Button
		WebElement confirmbtn = driver.findElement(By.xpath("//button[contains(text(),'Confirm Box')]"));
		confirmbtn.click();
		alert.dismiss();

		// Click Prompt Button
		WebElement promptbtn = driver.findElement(By.xpath("//button[@onclick='myFunctionPrompt()']"));
		promptbtn.click();
		alert.sendKeys(value);
		alert.accept();
	}

	@Test(priority = 14)
	public void doubleClick() {// Verify Double Click button	
		WebElement doubleclick = driver.findElement(By.xpath("//button[contains(text(),'Copy Text')]"));
		action = new Actions(driver);
		action.doubleClick(doubleclick).build().perform();
	}

	@Test(priority = 15)
	public void dragAndDrop() {// Verify the DragandDrop	
		WebElement src = driver.findElement(By.id("draggable"));
		WebElement desc = driver.findElement(By.id("droppable"));
		action.dragAndDrop(src, desc).build().perform();
	}

	@Test(priority = 16)
	public void sliderBar() {// Verify the Slider Button
		WebElement slider = driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));
		action.dragAndDropBy(slider, 100, 0).perform();
	}

	@Test(priority = 17)
	@Parameters("Name1")
	public void frames(String name1) {// Verify the frame model
		driver.switchTo().frame(0); 

		WebElement name = driver.findElement(By.xpath("//input[@id='RESULT_TextField-0']")); // Find and enter the Name
		name.sendKeys(name1);

	    WebElement ClickMale = driver.findElement(By.xpath("//label[@for='RESULT_RadioButton-1_0']")); // Select Male Gender
	    ClickMale.click();

		WebElement Element = driver.findElement(By.xpath("//span[@class='icon_calendar']")); // select the date
		Element.click();

		WebElement nextmonth = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
		nextmonth.click(); // Click the Next month
		
		WebElement Date = driver.findElement(By.className("ui-state-default"));
		Date.click(); // select the date 04/14/2024

		// Find the Job DropAndDown
		WebElement Job = driver.findElement(By.id("RESULT_RadioButton-3"));

		select = new Select(Job);
		select.selectByVisibleText("Automation Engineer"); // Select the Automation Engineer DropDown

		WebElement Submit = driver.findElement(By.id("FSsubmit")); // Click the Submit button
		Submit.click();

		driver.switchTo().defaultContent(); // Switch to default window
	}

	@Test(priority = 18)
	public void reSize() {// Resize the Box
		WebElement resize = driver.findElement(By.xpath("(//div[@style='z-index: 90;'])[3]"));
		action.dragAndDropBy(resize, 200, -350).perform(); 
	}

	@Test(priority = 19)
	public void homeButton() {// Click the Home Button
		WebElement Homebtn = driver.findElement(By.xpath("//a[@class='home-link']")); 
		Homebtn.click();
	}

	@AfterTest
	public void closeBrowser() {// Close the Browser
		driver.quit(); 

		endtime = System.currentTimeMillis();
		long totaltime = endtime - starttime;
		System.out.println("Total Time is " + totaltime);

	}
}￼Enter		promptbtn.click();
		alert.sendKeys(value);
		alert.accept();
	}

	@Test(priority = 14)
	public void doubleClick() {// Verify Double Click button	
		WebElement doubleclick = driver.findElement(By.xpath("//button[contains(text(),'Copy Text')]"));
		action = new Actions(driver);
		action.doubleClick(doubleclick).build().perform();
	}

	@Test(priority = 15)
	public void dragAndDrop() {// Verify the DragandDrop	
		WebElement src = driver.findElement(By.id("draggable"));
		WebElement desc = driver.findElement(By.id("droppable"));
		action.dragAndDrop(src, desc).build().perform();
	}

	@Test(priority = 16)
	public void sliderBar() {// Verify the Slider Button
		WebElement slider = driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));
		action.dragAndDropBy(slider, 100, 0).perform();
	}

	@Test(priority = 17)
meters("Name1")
	public void frames(String name1) {// Verify the frame model
		driver.switchTo().frame(0); 

		WebElement name = driver.findElement(By.xpath("//input[@id='RESULT_TextField-0']")); // Find and enter the Name
		name.sendKeys(name1);

	    WebElement ClickMale = driver.findElement(By.xpath("//label[@for='RESULT_RadioButton-1_0']")); // Select Male Gender
	    ClickMale.click();

		WebElement Element = driver.findElement(By.xpath("//span[@class='icon_calendar']")); // select the date
		Element.click();

		WebElement nextmonth = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
		nextmonth.click(); // Click the Next month
		
		WebElement Date = driver.findElement(By.className("ui-state-default"));
		Date.click(); // select the date 04/14/2024

		// Find the Job DropAndDown
		WebElement Job = driver.findElement(By.id("RESULT_RadioButton-3"));

		select = new Select(Job);
		select.selectByVisibleText("Automation Engineer"); // Select the Automation Engineer DropDown

		WebElement Submit = driver.findElement(By.id("FSsubmit")); // Click the Submit button
