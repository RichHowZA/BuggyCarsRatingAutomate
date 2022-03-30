package com.buggy.rating.automate;

import com.buggy.rating.automate.beans.ContextBean;
import com.buggy.rating.automate.beans.RegisterDataBean;
import com.buggy.rating.automate.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static Logger log = LogManager.getLogger(BaseTest.class);
    public WebDriver driver;
    BuggyRatingTopBanner topBanner;
    BuggyRatingRegisterPage registerPage;
    BuggyCarsLandingPage landingPage;
    RegisterDataBean registerDataBean;
    LamborghiniAventadorVotePage votePage;
    LamborghiniRatePage ratePage;
    BuggyRatingAdditionalInfoPage additionalInfoPage;
    BuggyCarsOverallRatingPage overallRatingPage;

    public WebDriver initTest()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(contextRead().getUrl());
        return driver;
    }

    public ContextBean contextRead()
    {
        ContextBean contextBean =new ContextBean();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("automation.properties").getFile());

        try (InputStream input = new FileInputStream(file.getAbsolutePath())) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            contextBean.setUserName(prop.getProperty("buggy.rating.user"));
            contextBean.setPassword(prop.getProperty("buggy.rating.password"));
            contextBean.setUrl(prop.getProperty("buggy.rating.url"));
            contextBean.setFirstName(prop.getProperty("buggy.rating.firstname"));
            contextBean.setLastName(prop.getProperty("buggy.rating.lastname"));

        } catch (IOException ex) {
            log.error(ex + "Error in reading Property file");
        }
return contextBean;

    }
}
