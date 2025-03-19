package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorial.ninja.qa.pages.HomePage;
import com.tutorial.ninja.qa.pages.SearchPage;
import com.tutorialninja.qa.base.Base;
//Updated Comment
public class SearchTest extends Base {
	SearchPage searchPage;
	HomePage homePage;
	public SearchTest() {
		super();
	}
	public WebDriver driver;
	@BeforeMethod
	public void setup() {
		
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		 homePage=new HomePage(driver);
	}
		@AfterMethod
		public void tearDown() {
			driver.quit();
	}
		@Test(priority=1)
		public void verifysearchwithValiDetails() {
			searchPage=homePage.SearchForAProduct(dataProp.getProperty("validProduct"));
			
			Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid product HP is not displayed in the search results");
		}
		
		@Test(priority=2)
		public void verifysearchwithInValiDetails() {
			searchPage=homePage.SearchForAProduct(dataProp.getProperty("invalidProduct"));
			
			
		
			Assert.assertEquals(searchPage.retrieveNoProductMessageText(),"abcd","No Product message in search results is not displayed");
		}
		
		@Test(priority=3,dependsOnMethods= {"verifysearchwithValiDetails","verifysearchwithInValiDetails"})
		public void verifysearchWithoutAnyProduct() {
			
			 searchPage=homePage.clickOnSearchButton();
		
			
			
			Assert.assertEquals(searchPage.retrieveNoProductMessageText(),dataProp.getProperty("NoProductTextInSearchResults"),"No Product message in search results is not displayed");
		}
}
			