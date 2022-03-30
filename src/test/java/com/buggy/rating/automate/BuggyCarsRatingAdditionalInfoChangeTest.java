package com.buggy.rating.automate;

import com.buggy.rating.automate.beans.AdditionalInfoBean;
import com.buggy.rating.automate.beans.BasicInfoBean;
import com.buggy.rating.automate.beans.RegisterDataBean;
import com.buggy.rating.automate.pages.BuggyRatingAdditionalInfoPage;
import com.buggy.rating.automate.pages.BuggyRatingTopBanner;
import com.buggy.rating.automate.utils.TestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BuggyCarsRatingAdditionalInfoChangeTest extends BaseTest{
    public static Logger log = LogManager.getLogger(BuggyCarsRatingAdditionalInfoChangeTest.class);
    public static WebDriver driver;
    BuggyRatingTopBanner topBanner;
    BuggyRatingAdditionalInfoPage additionalInfoPage;
    RegisterDataBean registerDataBean;

    @BeforeClass
    public void setUp() {
        driver = initTest();
        topBanner = new BuggyRatingTopBanner(driver);
        additionalInfoPage = new BuggyRatingAdditionalInfoPage(driver);
        registerDataBean = new RegisterDataBean();
        registerDataBean.setLogin(contextRead().getUserName());
        registerDataBean.setFirstName("testuser");
        registerDataBean.setLastName("last");
        registerDataBean.setPassword(contextRead().getPassword());
    }

    @Test
    public void loginWithUser() {
        topBanner.setLoginUserName(registerDataBean.getLogin());
        topBanner.setLoginPassword(registerDataBean.getPassword());
        topBanner.clickLogin();
        Assert.assertTrue(topBanner.validateLogin(registerDataBean.getFirstName()), "Invalid login");
    }

    @Test(dependsOnMethods = "loginWithUser")
    public void fillAdditionalInfo() {
        topBanner.navigateToProfile();
        Assert.assertTrue(additionalInfoPage.verifyAdditionalInfoPage(), "Incorrect page navigation");
        BasicInfoBean basicInfoBean= new BasicInfoBean();
        basicInfoBean.setFirstName("testuser" + TestUtils.getRandomNumberString());
        basicInfoBean.setLastName("User123");
        additionalInfoPage.fillBasicInfo(basicInfoBean);

        AdditionalInfoBean additionalInfoBean = new AdditionalInfoBean();
        additionalInfoBean.setGender("Male");
        additionalInfoBean.setAge("28");
        additionalInfoBean.setAddress("Some Street, Some City");
        additionalInfoBean.setHobby("Biking");
        additionalInfoBean.setPhone("0220345566");
        additionalInfoPage.fillAdditionalInfo(additionalInfoBean);
        additionalInfoPage.saveTheForm();
        additionalInfoPage.verifyASuccessfulSavePage();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
