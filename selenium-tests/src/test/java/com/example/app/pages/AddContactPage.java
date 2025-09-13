package com.example.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;
import com.example.app.utils.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddContactPage {

    private WebDriver driver;

    private By nameInput = By.name("name");
    private By emailInput = By.name("email");
    private By phoneInput = By.name("phone");
    private By saveButton = By.tagName("button");

    private By contactTable = By.tagName("table");

    public AddContactPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public Contact addNewContact(String name, String email, String phone) {
        setName(name);
        setEmail(email);
        setPhone(phone);
        clickSaveButton();

        Contact newContact = new Contact();

        WebElement table = driver.findElement(contactTable);
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            for (int i=0; i< columns.size() ;i++) {
                WebElement column = columns.get(i);
                if (i==0) 
                    newContact.setId(column.getText());
                if (i==1)
                    newContact.setName(column.getText());
                if (i==2)
                    newContact.setEmail(column.getText());
                if (i==3)
                    newContact.setPhone(column.getText());
                
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return newContact;
    }

   


}
