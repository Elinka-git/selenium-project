package com.selenium.course.model.loginflow;

import com.selenium.course.model.AbstractWebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends AbstractWebPage {

    @FindBy(id = "createacc")
    private WebElement createAccountButton;

    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CreateAccountPage createAccount() {
        createAccountButton.click();
        return new CreateAccountPage(driver);
    }
}
