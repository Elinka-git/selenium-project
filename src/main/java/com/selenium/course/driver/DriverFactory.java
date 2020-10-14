package com.selenium.course.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    private static final String IGNORE_CERTIFICATE = "--ignore-certificate-errors";

    public static WebDriver getFirefoxDriver(int implicitWait){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(IGNORE_CERTIFICATE);

        WebDriverManager.getInstance(FirefoxDriver.class).setup();
        WebDriver driver = new FirefoxDriver(options);
        setImplicitWait(driver, implicitWait);

        return driver;
    }

    public static WebDriver getChromeDriver(int implicitWait){
        ChromeOptions options = new ChromeOptions();
        options.addArguments(IGNORE_CERTIFICATE);

        WebDriverManager.getInstance(ChromeDriver.class).setup();
        WebDriver driver = new ChromeDriver(options);
        setImplicitWait(driver, implicitWait);

        return driver;
    }

    private static void setImplicitWait(WebDriver driver, int implicitWait){
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
    }
}
