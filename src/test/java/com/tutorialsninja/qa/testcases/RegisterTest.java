package com.tutorialsninja.qa.testcases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorial.ninja.qa.pages.AccountSuccessPage;
import com.tutorial.ninja.qa.pages.HomePage;
import com.tutorial.ninja.qa.pages.RegisterPage;
import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utils;

public class RegisterTest extends Base {
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	public RegisterTest() {
		super();
	}
	public WebDriver driver;
	@BeforeMethod
	public void setup() {
		
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
	}
		@AfterMethod
		public void tearDown() {
			driver.quit();
	}
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryDetails() {
	
	
	registerPage.enterFirstName(dataProp.getProperty("firstName"));
	registerPage.enterlastName(dataProp.getProperty("lastName"));
	registerPage.enterEmailAddress(Utils.generateEmailWithTimeStamp());
	registerPage.enterTelephoneNumber(dataProp.getProperty("telephone no"));
	registerPage.enterPassword(prop.getProperty("validPassword"));
	registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
	registerPage.selectPrivacyPolicy();
	
	accountSuccessPage=new AccountSuccessPage(driver);
	
	String actualSuccessHeading=accountSuccessPage.retrieveAccountSuccessPageHeading();
	Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");

	}

	@Test(priority=2)
	public void verifyRegisteringAnAccountByProvidingAllDetails() {
	
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterlastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utils.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone no"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
	registerPage.selectYesNewsLetterOption();
	registerPage.selectPrivacyPolicy();
	
 accountSuccessPage=new AccountSuccessPage(driver);
	
	String actualSuccessHeading=accountSuccessPage.retrieveAccountSuccessPageHeading();
	Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");

	}
	
	
	
	
	@Test(priority=3)
	public void verifyRegisteringAnAccountWithExistingEmail() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterlastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utils.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone no"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
	registerPage.selectYesNewsLetterOption();
	registerPage.selectPrivacyPolicy();
	 registerPage.clickOnContinueButton();

	String actualWarning=registerPage.retrieveduplicateEmailAdddressWarning();
	Assert.assertTrue(actualWarning.contains (dataProp.getProperty("duplicate EmailWarning")),"Warning message regarding duplicate email address is not displayed");
	
	
}
	@Test(priority=4)
	public void verifyRegisteringAnAccountWithoutfillinganydetails() {
		
		registerPage.clickOnContinueButton();
	Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacy policy warning"),dataProp.getProperty("firstNameWarning"),dataProp.getProperty("lastNameWarning"),dataProp.getProperty("emailWarning"),dataProp.getProperty("telephoneWarning"),dataProp.getProperty("passwordWarning")));
	
		}
}
