package com.example.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


public class HomePage {

    private WebDriver driver;

    private By addContactButton = By.partialLinkText("Add Contact");
    private By viewContactsButton = By.partialLinkText("View Contacts");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddContactButton() {
        driver.findElement(addContactButton).click();
    }
    
    public void clickViewContactsButton() {
        driver.findElement(viewContactsButton).click();
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
