package com.selenium.course.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YahooGDPR extends AbstractWebPage {

    @FindBy(name = "agree")
    private WebElement agreeButton;

    public YahooGDPR(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public YahooFinanceLandingPage agreeToGDPR() {
        agreeButton.click();
        return new YahooFinanceLandingPage(driver);
    }
}
