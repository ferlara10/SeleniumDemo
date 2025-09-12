package com.example.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import com.example.app.utils.*;
import java.util.List;
import java.time.Duration;

public class ViewContactPage {

    private WebDriver driver;

    
    private By backHomeButton = By.partialLinkText("Back to Home");
    private By contactTable = By.tagName("table");

    public ViewContactPage(WebDriver driver) {
        this.driver = driver;
    }

    public Contact getContact(String id){
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

            if( newContact.getID() != null && newContact.getID().equals(id))
                return newContact;
        }
        return null;
    }

    public Boolean deleteContact(String id){
        Boolean result = false;
        By deleteButton = By.tagName("a");

        WebElement table = driver.findElement(contactTable);
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            for (int i=0; i< columns.size() ;i++) {
                WebElement column = columns.get(i);
                if (column.getText().equals(id)){
                    columns.get(4).findElement(deleteButton).click();
                    result = true;
                    // Switch to the alert
                     // Wait until the alert is present
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                    alert.accept();
                    break;
                } 
            }
        }
        return result;

    }

    public void clickBackHomeButton() {
        driver.findElement(backHomeButton).click();
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
