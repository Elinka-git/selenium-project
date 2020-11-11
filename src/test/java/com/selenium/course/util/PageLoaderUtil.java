package com.selenium.course.util;

import com.selenium.course.utils.Configuration;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageLoaderUtil {

    public static void waitForPageContentToLoad(WebDriver driver) {

        ExpectedCondition<Boolean> jsContentLoad = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };

        WebDriverWait wait = new WebDriverWait(driver, Configuration.getInstance().get("implicitWait", Integer.class));
        wait.until(jsContentLoad);
    }
}
