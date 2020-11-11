package com.selenium.course.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.course.model.company.CompanyPage;
import com.selenium.course.model.loginflow.SignInPage;

public class YahooFinanceLandingPage extends AbstractWebPage {

    @FindBy(id = "header-signin-link")
    private WebElement signInButton;

    @FindBy(id = "yfin-usr-qry")
    private WebElement searchField;

    @FindBy(id = "header-desktop-search-button")
    private WebElement searchButton;

    public YahooFinanceLandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SignInPage signIn() {
        signInButton.click();
        return new SignInPage(driver);
    }

    public CompanyPage searchForCompany(String company) {
        searchField.sendKeys(company);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        searchButton.click();

        return new CompanyPage(driver);
    }
}
