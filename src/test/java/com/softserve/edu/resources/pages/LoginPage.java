package com.softserve.edu.resources.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.resources.data.IUser;

public class LoginPage extends ATopPage {
	public final static String ATTRIBUTE_SRC = "src";
	public final static String NAME_IMAGE = "ukraine_logo2.gif";
	
	public static enum LoginPageL10n {
        LOGIN_LABEL("Логін","Логин","Login"),
        PASSWORD_LABEL("Пароль","Пароль","Password"),
		SUBMIT_BUTTON("Увійти","Войти","Sign in");
        //
        private HashMap<ChangeLanguageFields, String> field;

        private LoginPageL10n(String... localization) {
        	this.field = new HashMap<ChangeLanguageFields, String>();
        	int i = 0;
        	for (ChangeLanguageFields language : ChangeLanguageFields.values()) {
        		this.field.put(language, localization[i]);
        		i++;
        	}
        }

        public String getLocalization(ChangeLanguageFields language) {
            return this.field.get(language).trim();
        }
    }

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	// Fields
	
    private WebElement loginLabel;
    private WebElement loginInput;
    private WebElement passwordLabel;
    private WebElement passwordInput;
    private WebElement signin;
    private WebElement logo;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.loginLabel = driver.findElement(By.xpath("//label[contains(@for,'inputEmail')]"));
		this.loginInput = driver.findElement(By.id("login"));
		this.passwordLabel = driver.findElement(By.xpath("//label[contains(@for,'inputPassword')]"));
		this.passwordInput = driver.findElement(By.id("password"));
		this.signin = driver.findElement(By.cssSelector("button.btn.btn-primary"));
		this.logo = driver.findElement(By.cssSelector("img.login_logo.col-md-8.col-xs-12"));
	}

    // PageObject

	// get Data

	public WebElement getLoginLabel() {
		return this.loginLabel;
	}

	public WebElement getLoginInput() {
		return this.loginInput;
	}

	public WebElement getPasswordLabel() {
		return this.passwordLabel;
	}

	public WebElement getPasswordInput() {
		return this.passwordInput;
	}

	public WebElement getSignin() {
		return this.signin;
	}

	public WebElement getLogo() {
		return this.logo;
	}

	// Functional
	
	public String getLoginLabelText() {
		return getLoginLabel().getText().trim();
	}

	public String getLoginInputText() {
		return getLoginInput().getAttribute(ATTRIBUT_VALUE);
	}

	public String getPasswordLabelText() {
		return getPasswordLabel().getText().trim();
	}

	public String getPasswordInputText() {
		return getPasswordInput().getAttribute(ATTRIBUT_VALUE);
	}

	public String getSignintText() {
		return getSignin().getText().trim();
	}
	
	public String getLogoAttributeText(String attribute) {
		return getLogo().getAttribute(attribute).trim();
	}

	public String getLogoAttributeSrcText() {
		return getLogoAttributeText(ATTRIBUTE_SRC);
	}

	// set Data

	public void setLoginInput(String login) {
		getLoginInput().sendKeys(login);
	}

	public void setLoginInputClear(String login) {
		clearLoginInput();
		setLoginInput(login);
	}

	public void setPasswordInput(String password) {
		getPasswordInput().sendKeys(password);
	}

	public void setPasswordInputClear(String password) {
		clearPasswordInput();
		setPasswordInput(password);
	}

	public void clearLoginInput() {
		getLoginInput().clear();
	}

	public void clearPasswordInput() {
		getPasswordInput().clear();
	}

	public void clickLogin() {
		getLoginInput().click();
	}

	public void clickPassword() {
		getPasswordInput().click();
	}

	public void clickSignin() {
		getSignin().click();
	}

    // Business Logic

    public LoginPage changeLanguage(ChangeLanguageFields language) {
    	setChangeLanguage(language);
        // Return a new page object representing the destination.
        return new LoginPage(driver);
    }

    // TODO Develop User class
    private void setLoginData(IUser user) {
    //private void setLoginData(String login, String password) {
		setLoginInputClear(user.getLogin());
		setPasswordInputClear(user.getPassword());
		clickSignin();
	}

//    public HomePage successUserLogin(IUser user) {
//        setLoginData(user);
//        // Return a new page object representing the destination.
//        return new HomePage();
//    }

    public AdminHomePage successAdminLogin(IUser admin) {
    //public AdminHomePage successAdminLogin(String login, String password) {
		setLoginData(admin);
		//setLoginData(login, password);
		// Return a new page object representing the destination.
		return new AdminHomePage(driver);
	}

//	public RegistratorHomePage successRegistratorLogin(IUser registrator) {
//		setLoginData(registrator);
//		// Return a new page object representing the destination.
//		return new RegistratorHomePage();
//	}
//
    // TODO Develop User class
    public LoginValidatorPage unsuccessfulLogin(IUser invalidUser) {
	//public LoginValidatorPage unsuccessfulLogin(String login, String password) {
    	setLoginData(invalidUser);
		//setLoginData(login, password);
		return new LoginValidatorPage(driver); // return this;
	}

}