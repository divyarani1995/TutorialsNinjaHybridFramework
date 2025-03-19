package com.tutorialsninja.qa.testcases;


import java.time.Duration;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorial.ninja.qa.pages.AccountPage;
import com.tutorial.ninja.qa.pages.HomePage;
import com.tutorial.ninja.qa.pages.LoginPage;
import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utils;
public class LoginTest extends Base{
	LoginPage loginPage;
	public LoginTest() {
		super();
	}
public WebDriver driver;
@BeforeMethod
public void setup() {
	
	driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
	HomePage homePage=new HomePage(driver);
	homePage.clickOnMyAccount();
	
loginPage=homePage.selectLoginOption();
}
	@AfterMethod
	public void tearDown() {
		driver.quit();
}
@Test(priority=1,dataProvider="ValidCredentialsSupplier")
	public  void verifyLoginwithvalidcredentials(String email,String password) {
		// TODO Auto-generated method stub


AccountPage accountPage = loginPage.login(email, password);



Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit Your Account Information option is not displayed");

	}
@DataProvider(name="ValidCredentialsSupplier")
public Object [][] supplyTestData() {
	Object [][]data=Utils.getTestDataFromExcel("Login");
	return data;
}

@Test(priority=2)
public  void verifyLoginwithinvalidcredentials() {
	// TODO Auto-generated method stub
	
	loginPage.login(Utils.generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));
	



Assert.assertTrue(loginPage.retreiveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchingWarning")));


}
@Test(priority=3)
public  void verifyLoginwithinvalidemailandvalidPassword() {
	// TODO Auto-generated method stub
	
	loginPage.login(Utils.generateEmailWithTimeStamp(),prop.getProperty("validPassword"));
	


	Assert.assertTrue(loginPage.retreiveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchingWarning")));

}

@Test(priority=4)
public  void verifyLoginwithvalidemailandinvalidPassword() {
	// TODO Auto-generated method stub
	
	loginPage.login (prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));
	



Assert.assertTrue(loginPage. retreiveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchingWarning")));

}
@Test(priority=5)
public  void verifyLoginwithnocredentials() {
	// TODO Auto-generated method stub
 
	
	loginPage.clickOnLoginButton();
	Assert.assertTrue(loginPage.retreiveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchingWarning")));
}


}

