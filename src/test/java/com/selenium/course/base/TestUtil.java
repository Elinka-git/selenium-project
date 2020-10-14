package com.selenium.course.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.selenium.course.driver.DriverFactory;

public class TestUtil {
    private String browser;
    private String url;
    private int implicitWait;
    public WebDriver driver;

    @BeforeSuite
    public void readConfigProperties() {
        try (FileInputStream configFile = new FileInputStream("src/test/resources/config.properties")) {
            Properties config = new Properties();
            config.load(configFile);
            browser = config.getProperty("browser");
            url = config.getProperty("url");
            implicitWait = Integer.parseInt(config.getProperty("implicitWait"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @BeforeTest
    public void initTest(){
        setupBrowserDriver();
        loadUrl();
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }

    private void setupBrowserDriver() {
        if (browser.equals("firefox")) {
            driver = DriverFactory.getFirefoxDriver(implicitWait);
        } else if (browser.equals("chrome")) {
            driver = DriverFactory.getChromeDriver(implicitWait);
        }
    }

    private void loadUrl() {
        driver.get(url);
    }

}
