package com.example.app.utils;

// SeleniumSetup.java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SeleniumSetup {
    public static WebDriver createDriver() {
        // Automatically setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");         // Headless mode
        options.addArguments("--window-size=1920,1080"); // important for headless
        options.addArguments("--no-sandbox");           // required in CI
        options.addArguments("--disable-dev-shm-usage"); 
        options.addArguments("--disable-gpu");           // optional
        options.addArguments("--remote-allow-origins=*"); // Chrome 111+
        options.addArguments("--user-data-dir=/tmp/chrome-user-data"); // avoid conflicts
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
         
        return new ChromeDriver(options);
        //return new ChromeDriver();
    }

    public static void waitForPageLoad(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until(
            webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState")
                    .equals("complete")
        );
    }

}
