package com.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class InsiderCareersPage extends AbstractPageObject {

    public InsiderCareersPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//div[@data-id='b6c45b2'][@data-element_type='section']")
    private WebElement findYourCallingBlock;

    @FindBy(xpath = "//section[@data-id='8ab30be']")
    private WebElement ourLocationsBlock;

    @FindBy(xpath = "//section[@data-id='a8e7b90']")
    private WebElement lifeAtInsiderBlock;

    @FindBy(linkText = "See all teams")
    private WebElement seeAllTeamsBtn;

    @FindBy(xpath = "//div[@class='job-item col-12 col-lg-4 mt-5']//a[h3[contains(text(), 'Quality Assurance')]]")
    private WebElement qualityAssuranceBtn;

    public void checkIfCareersPageIsOpen(String currentUrl) {
        Assert.assertEquals(currentUrl, "https://useinsider.com/careers/", "Page not open");
    }

    public void FindYourCallingPresence(){
        Assert.assertTrue(findYourCallingBlock.isDisplayed(), "\"Find your calling\" block isn't present");
    }

    public void OurLocationsPresence(){
        Assert.assertTrue(ourLocationsBlock.isDisplayed(), "\"Our Locations\" block isn't present");
    }

    public void LifeAtInsiderPresence(){
        Assert.assertTrue(lifeAtInsiderBlock.isDisplayed(), "\"Life at Insider\" block isn't present");
    }

    public void selectSeeAllTeams(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", seeAllTeamsBtn);
    }

    public QualityAssurancePage selectQualityAssurance(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", qualityAssuranceBtn);
        return new QualityAssurancePage(this.getDriver(), this.getWait());
    }
}
