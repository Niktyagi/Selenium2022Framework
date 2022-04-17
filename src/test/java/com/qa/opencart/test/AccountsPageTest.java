package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 200 - Design Accounts page for open cart application")
@Story("US 101 - design login page features")
@Story("US 102 - design accounts page features")
public class AccountsPageTest extends BaseTest {
	
	@Description("Pre Login for accounts page tests")
	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	@Description("accounts Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	public void accountsPageTitleTest() {
		String actAccountPageTitle = accPage.getAccountsPageTitle();
		System.out.println("Acc page title :" + actAccountPageTitle);
		Assert.assertEquals(actAccountPageTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	@Description("account Page Header Test")
	@Severity(SeverityLevel.NORMAL)
	public void accPageHeaderTest() {
		Assert.assertTrue(accPage.isAccountsPageHeaderExist());
	}

	@Test
	@Description("Search exist Test")
	@Severity(SeverityLevel.CRITICAL)
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}

	@Test
	@Description("Account Sections Test")
	@Severity(SeverityLevel.NORMAL)
	public void accSectionsTest() {
		List<String> actSecList = accPage.getAccountsPageSectionsList();
		System.out.println("This is my Account Section List = " + actSecList);
		Assert.assertEquals(actSecList, Constants.ACCOUNTS_SECTIONS_LIST);
	}

	@Test
	@Description("Search header Test")
	@Severity(SeverityLevel.NORMAL)
	public void searchHeaderTest() {
		searchResultsPage = accPage.doSearch("Macbook");
		String actSearchHeader = searchResultsPage.getResultsPageHeaderValue();
		Assert.assertTrue(actSearchHeader.contains("Macbook"));
	}

	@Test
	@Description("check product count test after search")
	@Severity(SeverityLevel.CRITICAL)
	public void searchProductCountTest() {
		searchResultsPage = accPage.doSearch("iMac");
		int actProductCount = searchResultsPage.getProductSearchCount();
		Assert.assertEquals(actProductCount, Constants.IMAC_PRODUCT_COUNT);
	}

	@Test
	@Description("check product list test after search")
	@Severity(SeverityLevel.CRITICAL)
	public void getSearchProductListTest() {
		searchResultsPage = accPage.doSearch("iMac");
		List<String> actProductList = searchResultsPage.getProductResultsList();
		System.out.println(actProductList);
	}
}
