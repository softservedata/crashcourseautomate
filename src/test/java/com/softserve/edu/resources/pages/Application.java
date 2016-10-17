package com.softserve.edu.resources.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.softserve.edu.resources.apps.ApplicationSources;
import com.softserve.edu.resources.apps.ApplicationSourcesRepository;
import com.softserve.edu.resources.apps.ScreenShot;

public class Application {

	private interface IBrowser {
		WebDriver getBrowser(Application application);
	}

	private static class FirefoxTemporary implements IBrowser {
		public WebDriver getBrowser(Application application) {
			return application.getFirefoxDriver();	
		}
	}

	private static class ChromeTemporary implements IBrowser {
		public WebDriver getBrowser(Application application) {
			return application.getChromeDriver();	
		}
	}

	public static enum Browsers {
		DEFAUL_TEMPORARY("Chrome", new ChromeTemporary()),
		FIREFOX_TEMPORARY("FireFox", new FirefoxTemporary()),
		CHROME_TEMPORARY("Chrome", new ChromeTemporary());
		//
		private String browserName;
		private IBrowser browser;

		private Browsers(String browserName, IBrowser browser) {
			this.browserName = browserName;
			this.browser = browser;
		}

		public WebDriver runBrowser(Application application) {
			return browser.getBrowser(application);
		}

		@Override
		public String toString() {
			return browserName;
		}
	}

	private static volatile Application instance;
	private WebDriver driver;
	private ApplicationSources applicationSources;

	private Application(ApplicationSources applicationSources) {
		this.applicationSources = applicationSources;
	}

	public static Application get() {
		return get(null);
	}

	public static Application get(ApplicationSources applicationSources) {
		if (instance == null) {
			synchronized (Application.class) {
				if (instance == null) {
					if (applicationSources == null) {
						applicationSources = ApplicationSourcesRepository.getDefault();
					}
					instance = new Application(applicationSources);
					instance.init();
				}
			}
		}
		return instance;
	}

	public static void remove() {
		instance.quit();
		instance = null;
	}

	public void init() {
		initWebDriver();
		// TODO
	}

	public LoginPage load() {
		logout();
		driver.get(applicationSources.getLoginUrl());
		return new LoginPage(driver);
	}

	public LoginPage logout() {
		driver.get(applicationSources.getLogoutUrl());
		return new LoginPage(driver);
	}

	public String takeScreenShot() {
		if (getWebDriver() != null) {
			return new ScreenShot().captureScreen(getWebDriver());
		}
		return new String();
	}

	public void quit() {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	private void initWebDriver() {
		Browsers currentBrowser = Browsers.DEFAUL_TEMPORARY;
		for (Browsers browser : Browsers.values()) {
			if (browser.toString().toLowerCase()
					.contains(applicationSources.getBrowserName().toLowerCase())) {
				currentBrowser = browser;
				break;
			}
		}
		driver = currentBrowser.runBrowser(this);
		driver.manage().timeouts().implicitlyWait(applicationSources.getImplicitTimeOut(), TimeUnit.SECONDS);
	}

	private WebDriver getFirefoxDriver() {
		return new FirefoxDriver();
	}

	private WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", applicationSources.getDriverPath());
		return new ChromeDriver();
	}

	private WebDriver getHtmlUnitDriver() {
		// TODO
		// driver = new HtmlUnitDriver(true);
		// ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
		throw new RuntimeException("HtmlUnitDriver not implemented");
		// return driver;
	}

}
