package com.example.app;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;
//import io.github.bonigarcia.wdm.WebDriverManager;

import com.example.app.pages.AddContactPage;
import com.example.app.pages.ViewContactPage;
import com.example.app.utils.Contact;
import com.example.app.utils.SeleniumSetup;


public class ContactManagerTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Automatically download and setup ChromeDriver
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        driver = SeleniumSetup.createDriver();
        //driver.get("http://localhost:8000/");
    }

    @BeforeMethod
    public void navigateToHomePage() {
        driver.get("http://localhost:8000/");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test(priority = 2)
    public void addContactTest() {
        System.out.println("****************** ADD CONTACT ********************");
        AddContactPage addContactPage = new AddContactPage(driver);
        Contact newContact = addContactPage.addNewContact("John Doe", "john.doe@example.com", "1234567890");
        
        ViewContactPage viewContactPage = new ViewContactPage(driver);
        Contact newViewContact = viewContactPage.getContact(newContact.getID());

        System.out.println("****************** ID: " + newContact.getID());
        System.out.println("************** New ID: " + newViewContact.getID());
        Assert.assertEquals(newContact.getID(), newViewContact.getID());
        System.out.println("****************** END ADD CONTACT ********************");
    }

    @Test(priority = 3)
    public void viewContact(){
        System.out.println("****************** VIEW CONTACT ********************");
        AddContactPage addContactPage = new AddContactPage(driver);
        addContactPage.addNewContact("Luis Lara", "luis.lara@example.com", "0987654321");
        Contact newContact = addContactPage.addNewContact("Val Burke", "val.burke@example.com", "1234567890");
        addContactPage.addNewContact("Fernando Lemus", "lemus.fer@example.com", "8798124300");

        ViewContactPage viewContactPage = new ViewContactPage(driver);
        Contact newViewContact = viewContactPage.getContact(newContact.getID());

        Assert.assertEquals(newContact.getID(), newViewContact.getID());
        System.out.println("****************** END VIEW CONTACT ********************");
    }

    @Test(priority = 1)
    public void deleteContact(){
        System.out.println("****************** DELETE CONTACT ********************");
        AddContactPage addContactPage = new AddContactPage(driver);
        Contact newContact = addContactPage.addNewContact("Carlos Smith", "carlos.smith@example.com", "0987654321");

        ViewContactPage viewContactPage = new ViewContactPage(driver);
        Boolean result = viewContactPage.deleteContact(newContact.getID());

        Contact viewContact = viewContactPage.getContact(newContact.getID());

        Assert.assertNull(viewContact);

        Assert.assertTrue(result);
        System.out.println("****************** END DELETE CONTACT ********************");
    }


    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
