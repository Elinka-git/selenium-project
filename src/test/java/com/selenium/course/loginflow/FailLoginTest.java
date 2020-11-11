package com.selenium.course.loginflow;

import com.opencsv.exceptions.CsvException;
import com.selenium.course.model.YahooGDPR;
import com.selenium.course.model.loginflow.CreateAccountPage;
import com.selenium.course.model.loginflow.SignInPage;
import com.selenium.course.model.YahooFinanceLandingPage;
import com.selenium.course.utils.Configuration;
import com.selenium.course.utils.CsvReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

import static com.selenium.course.util.PageLoaderUtil.waitForPageContentToLoad;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class FailLoginTest {

    private Configuration config = Configuration.getInstance();
    private WebDriver driver = config.getSeleniumDriver();
    private WebDriverWait wait = new WebDriverWait(driver, config.get("implicitWait", Integer.class));

    private Object[][] csvData;

    @Before
    public void readCsvData() {
        try {
            csvData = CsvReader.readCsvFile("src/test/resources/login-data.csv");
        } catch (IOException | CsvException ignored) {
        }
    }

    @After
    public void closeBrowserSession() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        driver.close();
    }

    @Test
    public void testFailLogin() throws InterruptedException {
        final String expectedMobileError = "That doesn’t look right, please re-enter your phone number.";
        final String expectedPasswordError = "Your password isn’t strong enough, try making it longer.";
        final String expectedEmailError = "This is required.";
        final String expectedNameError = "This is required.";

        String firstName = (String) csvData[0][0];
        String lastName = (String) csvData[0][1];
        String email = (String) csvData[0][2];
        String password = (String) csvData[0][3];
        String mobilePhone = (String) csvData[0][4];
        String month = (String) csvData[0][5];
        String day = (String) csvData[0][6];
        String year = (String) csvData[0][7];
        String gender = (String) csvData[0][8];

        driver.navigate().to(config.get("url"));

        YahooGDPR yahooGDPR = new YahooGDPR(driver);
        YahooFinanceLandingPage landingPage = yahooGDPR.agreeToGDPR();
        waitForPageContentToLoad(driver);

        SignInPage signInPage = landingPage.signIn();
        waitForPageContentToLoad(driver);

        CreateAccountPage createAccountPage = signInPage.createAccount();
        waitForPageContentToLoad(driver);

        createAccountPage.fillRegistrationForm(firstName, lastName, email, password, mobilePhone, month, day, year, gender);
        TimeUnit.SECONDS.sleep(2);

        HashMap<String, WebElement> errorFields = createAccountPage.getErrorFields();

        String mobileError = errorFields.get("Mobile phone number").getText().trim();
        String passwordError = errorFields.get("Password").getText().trim();

        createAccountPage = createAccountPage.clickContinue();
        waitForPageContentToLoad(driver);

        String emailError = createAccountPage.getErrorFields().get("Email address").getText().trim();

        assertEquals(expectedPasswordError, passwordError);
        assertEquals(expectedMobileError, mobileError);
        assertEquals(expectedEmailError, emailError);


        // Second set of negative data
        createAccountPage.clearInputFields();

        firstName = (String) csvData[1][0];
        lastName = (String) csvData[1][1];
        email = (String) csvData[1][2];
        password = (String) csvData[1][3];
        mobilePhone = (String) csvData[1][4];
        month = (String) csvData[1][5];
        day = (String) csvData[1][6];
        year = (String) csvData[1][7];
        gender = (String) csvData[1][8];

        createAccountPage.fillRegistrationForm(firstName, lastName, email, password, mobilePhone, month, day, year, gender);
        TimeUnit.SECONDS.sleep(2);

        createAccountPage = createAccountPage.clickContinue();
        waitForPageContentToLoad(driver);

        String firstNameError = createAccountPage.getErrorFields().get("First name").getText().trim();
        String lastNameError = createAccountPage.getErrorFields().get("Last name").getText().trim();

        assertEquals(expectedNameError, firstNameError);
        assertEquals(expectedNameError, lastNameError);
    }
}
