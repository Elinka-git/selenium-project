package com.selenium.course.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvException;
import com.selenium.course.base.TestUtil;
import com.selenium.course.utils.CsvReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleTest extends TestUtil {

	@BeforeTest
    public void setupDriver() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
    }
	
	@DataProvider(name="company-data-file")
    public static Object[][] dataProviderFromFile() throws IOException, CsvException {
        return CsvReader.readCsvFile("src/test/resources/lcompany-data.csv");
    }

    @Test(dataProvider = "company-data-file")
    public void executeTest(String companyname) throws InterruptedException {

    	WebElement searchField = driver.findElement(By.xpath("//*[@class=\"Bdrs(0) Bxsh(n)! Fz(s) Bxz(bb) D(ib) Bg(n) Pend(5px) Px(8px) Py(0) H(30px) Lh(30px) Bd O(n):f O(n):h Bdc($seperatorColor) Bdc($linkColor):f Bdc($c-fuji-punch-a):inv C($negativeColor):in M(0) Pstart(10px) Bxz(bb) Bgc(white) W(100%) H(32px)! Lh(32px)! Ff($yahooSansFinanceFont)\"]"));
    	searchField.sendKeys(companyname);
    	
    	
    	

    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }

}
