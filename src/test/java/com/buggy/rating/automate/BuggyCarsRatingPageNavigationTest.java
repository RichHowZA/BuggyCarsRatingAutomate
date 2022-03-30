package com.buggy.rating.automate;

import com.buggy.rating.automate.beans.RegisterDataBean;
import com.buggy.rating.automate.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class BuggyCarsRatingPageNavigationTest extends BaseTest {
    public static Logger log = LogManager.getLogger(BuggyCarsRatingRegisterTest.class);

    @BeforeClass
    public void setUp() {
        driver = initTest();
        topBanner = new BuggyRatingTopBanner(driver);
        registerPage = new BuggyRatingRegisterPage(driver);
        landingPage = new BuggyCarsLandingPage(driver);
        votePage = new LamborghiniAventadorVotePage(driver);
        ratePage = new LamborghiniRatePage(driver);
        overallRatingPage = new BuggyCarsOverallRatingPage(driver);
        additionalInfoPage = new BuggyRatingAdditionalInfoPage(driver);
        registerDataBean = new RegisterDataBean();
        registerDataBean.setLogin(contextRead().getUserName());
        registerDataBean.setFirstName(contextRead().getFirstName());
        registerDataBean.setLastName(contextRead().getLastName());
        registerDataBean.setPassword(contextRead().getPassword());
    }

    @Test
    public void performLogin() {
        BuggyRatingTopBanner landingPage = new BuggyRatingTopBanner(driver);
        landingPage.setLoginUserName(registerDataBean.getLogin());
        landingPage.setLoginPassword(registerDataBean.getPassword());
        landingPage.clickLogin();
        Assert.assertTrue(landingPage.validateLogin(registerDataBean.getFirstName()),
                "Invalid login");
    }

    @Test(dependsOnMethods = "performLogin")
    public void navigateHomeFromProfilePage() {
        topBanner.navigateToProfile();
        Assert.assertTrue(additionalInfoPage.verifyAdditionalInfoPage(),
                "Incorrect page navigation- profile Page");
        topBanner.goToBuggyRatingPage();
        Assert.assertTrue(landingPage.verifyLandingPage(), "" +
                "Incorrect page navigation - Landing Page");
    }

    @Test(dependsOnMethods = "performLogin")
    public void navigateHomeFromPopularMakePage() {
        topBanner.goToBuggyRatingPage();
        landingPage.clickLamborghini();
        Assert.assertTrue(ratePage.validateMake("Lamborghini"),
                "Incorrect page navigation- Poplar Make Page");
        topBanner.goToBuggyRatingPage();
        Assert.assertTrue(landingPage.verifyLandingPage(),
                "Incorrect page navigation - Landing Page");
    }

    @Test(dependsOnMethods = "performLogin")
    public void navigateHomeFromOverallRatingPage() {
        topBanner.goToBuggyRatingPage();
        landingPage.goToOverAllRating();
        Assert.assertTrue(overallRatingPage.validateOvarallRatingPageNavigation(),
                "Incorrect page navigation - Overall Rating Page");
        topBanner.goToBuggyRatingPage();
        Assert.assertTrue(landingPage.verifyLandingPage(),
                "Incorrect page navigation - Landing Page");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
