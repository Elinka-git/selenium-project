package com.selenium.course.model.company;

import com.selenium.course.model.YahooFinanceLandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CompanyPage extends YahooFinanceLandingPage {

    @FindBy(xpath = "//*[@id=\"quote-summary\"]/div[2]/table/tbody/tr[6]")
    private WebElement forwardDividendAndYieldsRow;

    @FindBy(linkText = "Statistics")
    private WebElement statisticsTab;

    @FindBy(xpath = "//*[@id=\"Col1-0-KeyStatistics-Proxy\"]/section/div[3]/div[1]/div[2]/div/div[1]/div[1]/table/tbody/tr[7]/td[2]")
    private WebElement priceBookMrq;

    public CompanyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * @return List<WebElement> - children of forwardDividendAndYieldsRow
            * [0] - 'Forward Dividend & Yield' text
            * [1] - dividends rate
     */
    public List<WebElement> getDividendAndYieldData() {
        return forwardDividendAndYieldsRow.findElements(By.tagName("td"));
    }

    public CompanyPage navigateToStatistics() {
        statisticsTab.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        return new CompanyPage(driver);
    }

    public WebElement getPriceBookMrq() {
        return priceBookMrq;
    }
}
