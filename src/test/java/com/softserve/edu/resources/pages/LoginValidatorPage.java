package com.softserve.edu.resources.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginValidatorPage extends LoginPage {

	// Fields
	
    private WebElement invalidLoginValidator;

	public LoginValidatorPage(WebDriver driver) {
		super(driver);
		this.invalidLoginValidator = driver.findElement(By.xpath("//div[contains(@style,'color: red;')]"));
	}
	
    // PageObject

	// get Data
	
	public WebElement getInvalidLoginValidator() {
		return this.invalidLoginValidator;
	}

	// Functional
	
	public String getInvalidLoginValidatorText() {
		return getInvalidLoginValidator().getText().trim();
	}

}
