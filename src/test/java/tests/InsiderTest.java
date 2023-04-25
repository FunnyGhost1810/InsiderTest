package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.driver.BaseDriver;
import com.pages.*;

public class InsiderTest {
    BaseDriver driver;
    private final String InsiderHomePageURL = "https://useinsider.com";
    private final String CareersPageURL = "https://useinsider.com/careers/";
    private final String QualityAssurancePageURL = "https://useinsider.com/careers/quality-assurance/";

    @BeforeTest
    public void setup() {
       driver = new BaseDriver();
    }

    @Test(priority = 1)
    public void testInsiderPage() throws InterruptedException {

        this.driver.get(InsiderHomePageURL);
        InsiderMainPage insiderMainPage = new InsiderMainPage(driver.getDriver(), driver.getWait());
        Response response = RestAssured.get("https://useinsider.com");
        Assert.assertEquals(response.getStatusCode(), 200);
        insiderMainPage.checkIfHomePageIsOpen(InsiderHomePageURL);

        InsiderCareersPage careersPage = insiderMainPage.selectMoreAndCareers();
        response = RestAssured.get("https://useinsider.com/careers/");
        Assert.assertEquals(response.getStatusCode(), 200);
        careersPage.checkIfCareersPageIsOpen(CareersPageURL);
        careersPage.FindYourCallingPresence();
        careersPage.OurLocationsPresence();
        careersPage.LifeAtInsiderPresence();
        careersPage.selectSeeAllTeams();

        QualityAssurancePage qualityAssurancePage = careersPage.selectQualityAssurance();
        qualityAssurancePage.checkIfQualityAssurancePageIsOpen(QualityAssurancePageURL);

        OpenPositionsPage openPositionsPage = qualityAssurancePage.selectAllQAJobs();
        openPositionsPage.setDropdownLocation();
        openPositionsPage.setDropdownDepartment();
        openPositionsPage.setJobList();
        openPositionsPage.setJobListings();
        openPositionsPage.setApplyButton();
    }

  @AfterTest
    public void teardown()
  {
      this.driver.quit();
  }

}
