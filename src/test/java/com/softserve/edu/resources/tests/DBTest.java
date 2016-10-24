package com.softserve.edu.resources.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.resources.apps.ApplicationSources;
import com.softserve.edu.resources.apps.ApplicationSourcesRepository;
import com.softserve.edu.resources.data.IUser;
import com.softserve.edu.resources.data.UserRepository;
import com.softserve.edu.resources.pages.AdminHomePage;
import com.softserve.edu.resources.pages.Application;
import com.softserve.edu.resources.pages.LoginPage;
import com.softserve.edu.resources.pages.RegisteredUsersHomePage;
import com.softserve.edu.resources.services.ConnectionUtils;
import com.softserve.edu.resources.services.DataSourceRepository;
import com.softserve.edu.resources.services.EmplService;

public class DBTest {

	@BeforeClass
	public void beforeClass() {
		System.out.println("@BeforeClass");
		ConnectionUtils.get(DataSourceRepository.get().getJtdsMsSqlSsu());
	}


	@AfterClass(groups="method1")
	public void afterClass() {
		System.out.println("@AfterClass");
		Application.get().quit();
		ConnectionUtils.closeConnection();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("@BeforeMethod");
		new EmplService().insertUser(UserRepository.getNewUser());
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("@AfterMethod");
		new EmplService().deleteUsersByEmail(UserRepository.getNewUser().getEmail());
	}

	@DataProvider // (parallel = true)
	public Object[][] users() {
		return new Object[][] { { ApplicationSourcesRepository.getChromeHeroku(), UserRepository.getAdmin(),
				UserRepository.getNewUser() },
				// { ApplicationSourcesRepository.getChromeHeroku(),
				// UserRepository.getRegistrator() }
		};
	}

	@Test(dataProvider = "users")
	public void check(ApplicationSources applicationSources, IUser admin, IUser newUser) throws Exception {
		// Precondition
		// TODO Remove from test
		// WebDriver driver = new FirefoxDriver();
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Program Files
		// (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// driver.get(url);
		// SoftAssert softAssert = new SoftAssert();
		//
		// Steps
		LoginPage loginPage = Application.get(applicationSources).load();
		AdminHomePage adminHomePage = loginPage.successAdminLogin(admin);
		//
		// Check
		Assert.assertEquals(adminHomePage.getLoginAccountText(), admin.getLogin());
		Thread.sleep(2000);
		//
		// Steps
		RegisteredUsersHomePage registeredUsersHomePage = adminHomePage.gotoRegisteredUsers();
		registeredUsersHomePage.setFirstnameInput(newUser.getFirstname());
		//
		// Check
		Assert.assertEquals(registeredUsersHomePage.getFirstnameInputText(), newUser.getFirstname());
		Thread.sleep(2000);
		//
		// Return to previous state
		loginPage = registeredUsersHomePage.logout();
		Assert.assertTrue(loginPage.getLogoAttributeSrcText().contains(LoginPage.NAME_IMAGE));
		//
		Thread.sleep(2000);
		//Application.get().quit();
		// softAssert.assertAll();
		System.out.println("firstname = "+new EmplService().getEmplByEmail(newUser.getEmail()).getFirstname());
	}

}
