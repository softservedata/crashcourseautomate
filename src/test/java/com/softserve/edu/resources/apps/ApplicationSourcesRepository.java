package com.softserve.edu.resources.apps;

public final class ApplicationSourcesRepository {
	
	private ApplicationSourcesRepository() {
	}

	public static ApplicationSources getDefault() {
		return getChromeHeroku();
	}

	public static ApplicationSources getFirefoxHeroku() {
		return new ApplicationSources("FireFox", "", 5L,
				"http://registrator.herokuapp.com/login",
				"http://registrator.herokuapp.com/logout");
	}
	
	public static ApplicationSources getChromeHeroku() {
		return new ApplicationSources("Chrome",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe", 5L,
				"http://registrator.herokuapp.com/login",
				"http://registrator.herokuapp.com/logout");
	}

	public static ApplicationSources getHtmlUnitHeroku() {
		return new ApplicationSources("HtmlUnit", "", 5L,
				"http://registrator.herokuapp.com/login",
				"http://registrator.herokuapp.com/logout");
	}

}
