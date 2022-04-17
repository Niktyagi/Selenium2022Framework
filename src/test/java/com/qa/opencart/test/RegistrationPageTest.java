package com.qa.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void regpageSetup() {
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmail() {
		Random random = new Random();
		String email= "Janautomation"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	
//	@DataProvider
//	public Object[][] getRegisterData() {
//		return new Object[][] {
//			{"Nitesh","Aggrawal","9845632147","abcs524","yes"},
//			{"Anu","kamath","9845632142","abcds524","no"},
//			{"Nikhil","Tyagi","9845632127","abcs724","yes"}
//		};
//	}
	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		 Assert.assertTrue(registrationPage
				 .accountRegistration(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
		
	}
	
}
