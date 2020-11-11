package com.selenium.course.model;

import com.selenium.course.utils.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractWebPage {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public AbstractWebPage(WebDriver driver) {
        AbstractWebPage.driver = driver;
        wait = new WebDriverWait(driver, Configuration.getInstance().get("implicitWait", Integer.class));
    }
}
