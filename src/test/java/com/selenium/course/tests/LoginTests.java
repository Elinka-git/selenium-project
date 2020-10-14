package com.selenium.course.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvException;
import com.selenium.course.base.TestUtil;
import com.selenium.course.utils.CsvReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginTests extends TestUtil {

    @DataProvider(name="login-data-file")
    public static Object[][] dataProviderFromFile() throws IOException, CsvException {
        return CsvReader.readCsvFile("src/test/resources/login-data.csv");
    }

    @Test(dataProvider = "login-data-file")
    public void executeTest(String user, String userlast, String email, String pass, String phonenumber, String userday, String useryear) throws InterruptedException {

    	WebElement signInButton = driver.findElement(By.xpath("//*[@id=\"header-signin-link\"]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", signInButton);
        
        WebElement createAccButton = driver.findElement(By.id("createacc"));
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("arguments[0].click();", createAccButton);
    	    	
    	WebElement usernameFirst = driver.findElement(By.xpath("//*[@id=\"usernamereg-firstName\"]"));
    	usernameFirst.sendKeys(user);
    	
    	WebElement usernameLast = driver.findElement(By.xpath("//*[@id=\"usernamereg-lastName\"]"));
    	usernameLast.sendKeys(userlast);
    	
    	WebElement usernameEmail = driver.findElement(By.xpath("//*[@id=\"usernamereg-yid\"]"));
    	usernameEmail.sendKeys(email);

        WebElement password = driver.findElement(By.xpath("//*[@id=\"usernamereg-password\"]"));
        password.sendKeys(pass);
        
        WebElement phone = driver.findElement(By.xpath("//*[@id=\"usernamereg-phone\"]"));
        phone.sendKeys(phonenumber);
        
        Select birthMonth = new Select(driver.findElement(By.xpath("//*[@id=\"usernamereg-month\"]")));
        Thread.sleep(1000);
        birthMonth.selectByVisibleText("March");
        
        WebElement day = driver.findElement(By.xpath("//*[@id=\"usernamereg-day\"]"));
        day.sendKeys(userday);
        
        WebElement year = driver.findElement(By.xpath("//*[@id=\"usernamereg-year\"]"));
        year.sendKeys(useryear);

        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"reg-submit-button\"]"));
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("arguments[0].click();", submitButton);

        usernameFirst.clear();
        usernameLast.clear();
        usernameEmail.clear();
        password.clear();
        phone.clear();
        day.clear();
        year.clear();

        Thread.sleep(1000);

    }

}
