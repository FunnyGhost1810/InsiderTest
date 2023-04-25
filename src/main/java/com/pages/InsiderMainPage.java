package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class InsiderMainPage extends AbstractPageObject {

    public InsiderMainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//li/a[@class=\"nav-link dropdown-toggle\"]//span[text()='More']")
    private WebElement moreBtn;

    @FindBy(xpath = "//h5[text() = \"Careers\"]")
    private WebElement careersBtn;

    public void checkIfHomePageIsOpen(String currentUrl) {
        Assert.assertEquals(currentUrl, "https://useinsider.com", "Page not open");
    }

    public InsiderCareersPage selectMoreAndCareers() {
        Assert.assertTrue(moreBtn.isDisplayed());
        moreBtn.click();
        getWait().until(ExpectedConditions.visibilityOf(careersBtn));
        Assert.assertTrue(careersBtn.isDisplayed());
        careersBtn.click();
        return new InsiderCareersPage(this.getDriver(), this.getWait());
    }

}
