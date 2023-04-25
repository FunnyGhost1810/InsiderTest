package com.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class OpenPositionsPage extends AbstractPageObject {

    public OpenPositionsPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}

    @FindBy (id = "select2-filter-by-location-container")
    private WebElement dropdownLocation;

    @FindBy(id = "select2-filter-by-department-container")
    private WebElement dropdownDepartment;

    @FindBy (xpath = "//ul[@id = 'select2-filter-by-location-results']")
    private WebElement ulDropDown;

    @FindBy(xpath = "/html/body/span/span/span[2]/ul/li[contains(text(), 'Istanbul, Turkey')]")
    private WebElement istanbulOption;
    //This isn't an optimized xpath, however I'd to use full xpath as the element becomes visible after performing script to enable dropdown.
    //According to such behaviour, selenium couldn't find this element when I used not full xpath

    @FindBy(xpath = "//span[contains(text(), 'Quality Assurance')]")
    private WebElement qaOption;

    @FindBy(id = "career-position-list")
    private WebElement jobList;

    @FindBy (id = "career-position-list")
    private List<WebElement> jobListings;

    @FindBy (xpath = "//div/p[@class = 'position-title font-weight-bold']")
    private WebElement jobTitle;

    @FindBy (xpath = "//div/span[@class = 'position-department text-large font-weight-600 text-primary']")
    private WebElement jobDepartment;

    @FindBy (xpath = "//div/div[@class = 'position-location text-large']")
    private WebElement jobLocation;

    @FindBy (xpath = "//div[@class = 'position-list-item-wrapper bg-light']")
    private WebElement jobBlock;

    @FindBy (xpath = "/html/body/section[3]/div/div/div[2]/div[1]/div/a")
    private WebElement applyButton;
    // Here I used also full xpath as selenium couldn't find it by not full xpath probably because it gets visible only after howeving over it.

    public void setDropdownLocation() throws InterruptedException {
        Thread.sleep(5000);

        // Because dropdown list is provided by JS script which isn't loaded immediately, I'd to add hard timeout as a workaround.
        // I'd prefer to do so using wait(). however, it didn't work as well. So, here is the only workaround I's able to find is "Tread.sleep"

        Actions actions = new Actions(getDriver());
        actions.moveToElement(dropdownLocation).click().perform();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", jobList);

        // I had to add scroll as selenium selected "All" instead of "Istanbul, Turkey" and scrolling down somehow fixed it.

        Thread.sleep(3000);
        if (ulDropDown.getAttribute("aria-hidden").equals("true")) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute('aria-hidden', 'false');", ulDropDown);
        }

        // I've added check if 'ul' is hidden or no, if 'no' enable it, just to make sure selenium will find element in dropdown
        // And to exclude that "ul" may not get displayed after the script is processed

        getWait().until(ExpectedConditions.visibilityOf(istanbulOption));
        actions.click(istanbulOption).perform();
    }

    public void setDropdownDepartment(){
        Assert.assertTrue(qaOption.isDisplayed(), "QA option is not selected");
    }

    public void setJobList(){
        Assert.assertTrue(jobList.isDisplayed(), "Section Job List is not present");
    }

    public void setJobListings(){
        for (WebElement jobListing : jobListings) {
            Assert.assertTrue(jobTitle.getText().contains("Quality Assurance"), "Position does not contain 'Quality Assurance'");
            Assert.assertTrue(jobDepartment.getText().equals("Quality Assurance"), "Department does not contain 'Quality Assurance'");
            Assert.assertTrue(jobLocation.getText().equals("Istanbul, Turkey"), "Location does not contain 'Istanbul, Turkey'");
            Assert.assertTrue(applyButton.isEnabled(), "'Apply Now' button isn't present");
        }
    //Honestly, I'm not sure that this works

    }

   public void setApplyButton(){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(jobBlock).perform();
        getWait().until((ExpectedConditions.visibilityOf(applyButton)));
        actions.click(applyButton).perform();
    }
}
