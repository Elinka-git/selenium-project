package com.selenium.course.model.loginflow;

import com.selenium.course.model.AbstractWebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;

public class CreateAccountPage extends AbstractWebPage {

    private final String idPrefix = "usernamereg-";
    private final String errorIdPrefix = "reg-error-";

    @FindBy(id = idPrefix + "firstName")
    private WebElement firstName;

    @FindBy(id = idPrefix + "lastName")
    private WebElement lastName;

    @FindBy(id = idPrefix + "yid")
    private WebElement email;

    @FindBy(id = idPrefix + "password")
    private WebElement password;

    @FindBy(id = idPrefix + "phone")
    private WebElement mobilePhone;

    @FindBy(id = idPrefix + "month")
    private WebElement birthMonth;

    @FindBy(id = idPrefix + "day")
    private WebElement day;

    @FindBy(id = idPrefix + "year")
    private WebElement year;

    @FindBy(id = idPrefix + "freeformGender")
    private WebElement gender;

    @FindBy(id = "reg-submit-button")
    private WebElement continueButton;

    //--- ERROR ELEMENTS ---//
    @FindBy(id = errorIdPrefix + "firstName")
    private WebElement firstNameError;

    @FindBy(id = errorIdPrefix + "lastName")
    private WebElement lastNameError;

    @FindBy(id = errorIdPrefix + "yid")
    private WebElement emailError;

    @FindBy(id = errorIdPrefix + "password")
    private WebElement passwordError;

    @FindBy(id = errorIdPrefix + "phone")
    private WebElement phoneError;

    @FindBy(id = errorIdPrefix + "birthDate")
    private WebElement birthDateError;

    public CreateAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String password,
                                     String mobilePhone, String month, String day, String year, String gender) {
        if (firstName != null && !firstName.equals("")) {
            this.firstName.sendKeys(firstName);
        }

        if (lastName != null && !lastName.equals("")) {
            this.lastName.sendKeys(lastName);
        }

        if (email != null && !email.equals("")) {
            this.email.sendKeys(email);
        }

        if (password != null && !password.equals("")) {
            this.password.sendKeys(password);
        }

        if (mobilePhone != null && !mobilePhone.equals("")) {
            this.mobilePhone.sendKeys(mobilePhone);
        }

        Select monthSelect = new Select(this.birthMonth);
        if (month != null && !month.equals("")) {
            monthSelect.selectByVisibleText(month);
        }

        if (day != null && !day.equals("")) {
            this.day.sendKeys(day);
        }

        if (year != null && !year.equals("")) {
            this.year.sendKeys(year);
        }

        if (gender != null && !gender.equals("")) {
            this.gender.sendKeys(gender);
        }
    }

    public CreateAccountPage clickContinue() {
        continueButton.click();
        return new CreateAccountPage(driver);
    }

    public HashMap<String, WebElement> getErrorFields() {
        HashMap<String, WebElement> errorFields = new HashMap<>();

        errorFields.put("First name", firstNameError);
        errorFields.put("Last name", lastNameError);
        errorFields.put("Email address", emailError);
        errorFields.put("Password", passwordError);
        errorFields.put("Mobile phone number", phoneError);
        errorFields.put("Birth date", birthDateError);

        return errorFields;
    }

    public void clearInputFields() {
        firstName.clear();
        lastName.clear();
        email.clear();
        password.clear();
        mobilePhone.clear();
        day.clear();
        year.clear();
        gender.clear();
    }
}
