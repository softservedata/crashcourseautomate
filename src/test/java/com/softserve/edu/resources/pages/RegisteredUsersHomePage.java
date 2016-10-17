package com.softserve.edu.resources.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisteredUsersHomePage extends AdminHomePage {

	// Fields
	private WebElement firstname;
	
	public RegisteredUsersHomePage(WebDriver driver) {
        super(driver);
        this.firstname = driver.findElement(By.id("firstName"));
    }

	// PageObject

	// get Data

	public WebElement getFirstnameInput() {
		return this.firstname;
	}

	// Functional

	public String getFirstnameInputText() {
		return getFirstnameInput().getAttribute(ATTRIBUT_VALUE);
	}

	// set Data

	public void setFirstnameInput(String login) {
		getFirstnameInput().sendKeys(login);
	}

	public void setFirstnameInputClear(String login) {
		clearFirstnameInput();
		setFirstnameInput(login);
	}

	public void clearFirstnameInput() {
		getFirstnameInput().clear();
	}

	public void clickFirstname() {
		getFirstnameInput().click();
	}

    // Business Logic

}
