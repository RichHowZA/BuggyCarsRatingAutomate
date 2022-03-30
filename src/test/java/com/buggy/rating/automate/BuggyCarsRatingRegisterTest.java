package com.buggy.rating.automate;

import com.buggy.rating.automate.beans.RegisterDataBean;
import com.buggy.rating.automate.pages.BuggyRatingTopBanner;
import com.buggy.rating.automate.pages.BuggyRatingRegisterPage;
import com.buggy.rating.automate.utils.TestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BuggyCarsRatingRegisterTest extends BaseTest {
    public static Logger log = LogManager.getLogger(BuggyCarsRatingRegisterTest.class);
    public static WebDriver driver;
    BuggyRatingTopBanner topBanner;
    BuggyRatingRegisterPage registerPage;
    RegisterDataBean registerDataBean;

    @BeforeClass
    public void setUp() {
        driver = initTest();
        topBanner = new BuggyRatingTopBanner(driver);
        registerPage = new BuggyRatingRegisterPage(driver);
        String userName = "testuser" + TestUtils.getRandomNumberString();
        registerDataBean = new RegisterDataBean();
        registerDataBean.setLogin(userName);
        registerDataBean.setFirstName("testuser");
        registerDataBean.setLastName("last");
        registerDataBean.setPassword("testUser@1234");
    }

    @Test(description= "Navigate to register page")
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

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
