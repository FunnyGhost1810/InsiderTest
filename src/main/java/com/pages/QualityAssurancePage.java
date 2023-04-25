package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class QualityAssurancePage extends AbstractPageObject {

    public QualityAssurancePage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}

    @FindBy (xpath = "//div/a[contains(text(), 'See all QA jobs')]")
    private WebElement allQAJobsBtn;

    public void checkIfQualityAssurancePageIsOpen(String currentUrl) {
        Assert.assertEquals(currentUrl, "https://useinsider.com/careers/quality-assurance/", "Page not open");
    }

    public OpenPositionsPage selectAllQAJobs(){
        Assert.assertTrue(allQAJobsBtn.isDisplayed());
        allQAJobsBtn.click();
        return new OpenPositionsPage(this.getDriver(), this.getWait());
    }

}
