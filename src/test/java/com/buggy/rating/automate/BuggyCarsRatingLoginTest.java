package com.buggy.rating.automate;

import com.buggy.rating.automate.beans.RegisterDataBean;
import com.buggy.rating.automate.pages.BuggyRatingTopBanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BuggyCarsRatingLoginTest extends BaseTest {
    public static Logger log = LogManager.getLogger(BuggyCarsRatingLoginTest.class);
    public static WebDriver driver;
    BuggyRatingTopBanner topBanner;
    RegisterDataBean registerDataBean;

    @BeforeClass
    public void setUp() {
        driver = initTest();
        topBanner = new BuggyRatingTopBanner(driver);
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
        Assert.assertTrue(landingPage.validateLogin(registerDataBean.getFirstName()), "Invalid login");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
