package com.buggy.rating.automate;

import com.buggy.rating.automate.beans.RegisterDataBean;
import com.buggy.rating.automate.pages.*;
import com.buggy.rating.automate.utils.TestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BuggyCarsRatingVotingTest extends BaseTest {
    public static Logger log = LogManager.getLogger(BuggyCarsRatingRegisterTest.class);

    @BeforeTest
    public void setUp() {
        driver = initTest();
        topBanner = new BuggyRatingTopBanner(driver);
        registerPage = new BuggyRatingRegisterPage(driver);
        landingPage = new BuggyCarsLandingPage(driver);
        votePage = new LamborghiniAventadorVotePage(driver);
        ratePage = new LamborghiniRatePage(driver);
        String userName = "testuser" + TestUtils.getRandomNumberString();
        registerDataBean = new RegisterDataBean();
        registerDataBean.setLogin(userName);
        registerDataBean.setFirstName("testuser");
        registerDataBean.setLastName("last");
        registerDataBean.setPassword("testUser@1234");
    }

    @Test
    public void navigateToRegisterPage() {
        topBanner.clickRegister();
        registerPage.validateRegistrationPage();
    }

    @Test(dependsOnMethods = "navigateToRegisterPage")
    public void registerNewUser() {

        registerPage.fillUpRegistration(registerDataBean);
        registerPage.submitRegister();
        Assert.assertTrue(registerPage.validateRegistration(), "Registration failed");
    }

    @Test(dependsOnMethods = "registerNewUser")
    public void loginWithNewUser() {
        topBanner.setLoginUserName(registerDataBean.getLogin());
        topBanner.setLoginPassword(registerDataBean.getPassword());
        topBanner.clickLogin();
        Assert.assertTrue(topBanner.validateLogin(registerDataBean.getFirstName()), "Invalid login");
    }

    @Test(dependsOnMethods = "loginWithNewUser")
    public void voteACar() {
        topBanner.goToBuggyRatingPage();
        landingPage.clickLamborghini();
        Assert.assertTrue(ratePage.validateMake("Lamborghini"), "Incorrect make selected");
        int voteCount = Integer.parseInt(ratePage.getAventadorVoteCount());
        ratePage.selectAventadorforVote();
        Assert.assertTrue(votePage.validateMake("AVENTADOR"), "Incorrect make selected");
        Assert.assertTrue(Integer.parseInt(votePage.getVoteCount()) == voteCount,
                "Different vote count");
        votePage.addComment("Nice fast car");
        votePage.clickVote();
        Assert.assertTrue(votePage.validateVoteNote(), "Incorrect vote note");
        Assert.assertTrue(Integer.parseInt(votePage.getVoteCount()) == (voteCount + 1),
                "Incorrect vote count");

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
