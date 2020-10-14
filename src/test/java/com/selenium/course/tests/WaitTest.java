package com.selenium.course.tests;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitTest {
    WebDriver driver = null;

    @BeforeTest
    public void setupDriver() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void executeTestWithWait() {
        driver.get("https://www.saucedemo.com");
        WebDriverWait wait = new WebDriverWait(driver, 20);

        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.className("btn_action"));
        loginButton.click();

        WebElement productLabel = driver.findElement(By.className("product_label"));
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(productLabel));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.invisibilityOf(loginButton));

    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }

}
