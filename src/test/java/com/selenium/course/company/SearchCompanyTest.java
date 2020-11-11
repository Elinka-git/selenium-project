package com.selenium.course.company;

import com.opencsv.exceptions.CsvException;
import com.selenium.course.model.YahooFinanceLandingPage;
import com.selenium.course.model.YahooGDPR;
import com.selenium.course.model.company.CompanyPage;
import com.selenium.course.utils.Configuration;
import com.selenium.course.utils.CsvReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static com.selenium.course.util.PageLoaderUtil.waitForPageContentToLoad;
import static org.junit.Assert.*;

public class SearchCompanyTest {

    private Configuration config = Configuration.getInstance();
    private WebDriver driver = config.getSeleniumDriver();
    private static final Logger LOGGER = Logger.getLogger(SearchCompanyTest.class.getName());

    private Object[][] csvData;

    @Before
    public void readCsvData() {
        try {
            csvData = CsvReader.readCsvFile("src/test/resources/company-data.csv");
        } catch (IOException | CsvException ignored) {
        }
    }
    
    @After
    public void closeBrowserSession() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        driver.close();
    }

    @Test
    public void testCompanySearch() throws InterruptedException {
        driver.navigate().to(config.get("url"));
        TimeUnit.SECONDS.sleep(5);
        
        YahooGDPR yahooGDPR = new YahooGDPR(driver);
        YahooFinanceLandingPage landingPage = yahooGDPR.agreeToGDPR();
        waitForPageContentToLoad(driver);
        TimeUnit.SECONDS.sleep(5);

        //APPLE
        CompanyPage apple = landingPage.searchForCompany((String) csvData[0][0]);
        waitForPageContentToLoad(driver);
        TimeUnit.SECONDS.sleep(10);

        List<WebElement> dividendAndYieldData = apple.getDividendAndYieldData();
        String appleDividend = dividendAndYieldData.get(0).getText().trim();
        String appleDividendRate = dividendAndYieldData.get(1).getText().trim();

        if (appleDividend.contains("N/A") || appleDividendRate.contains("N/A")) {
            LOGGER.info("Apple is not giving dividends.");
        }

        apple = apple.navigateToStatistics();
        waitForPageContentToLoad(driver);
        TimeUnit.SECONDS.sleep(10);

        String applePriceBook = apple.getPriceBookMrq().getText().trim();
        String expectedAppleDividend = (String)csvData[0][1];

        assertEquals(expectedAppleDividend, appleDividendRate);
        
        //AMAZON
        CompanyPage amazon = apple.searchForCompany((String) csvData[1][0]);
        waitForPageContentToLoad(driver);
        TimeUnit.SECONDS.sleep(10);

        dividendAndYieldData = amazon.getDividendAndYieldData();
        String amazonDividend = dividendAndYieldData.get(0).getText().trim();
        String amazonDividendRate = dividendAndYieldData.get(1).getText().trim();

        if (amazonDividend.contains("N/A") || amazonDividendRate.contains("N/A")) {
            LOGGER.info("Amazon is not giving dividends.");
        }

        amazon = amazon.navigateToStatistics();
        waitForPageContentToLoad(driver);
        TimeUnit.SECONDS.sleep(10);

        String amazonPriceBook = amazon.getPriceBookMrq().getText().trim();

        String expectedAmazonDividend = (String)csvData[1][1];
        
        assertEquals(expectedAmazonDividend, amazonDividendRate);
        
        assertTrue(appleDividend != null && !appleDividend.equals("")
                && appleDividendRate != null && !appleDividendRate.equals("")
                && applePriceBook != null && !applePriceBook.equals("")
                && amazonDividend != null && !amazonDividend.equals("")
                && amazonDividendRate != null && !amazonDividendRate.equals("")
                && amazonPriceBook != null && !amazonPriceBook.equals("")
        );
    }

}
