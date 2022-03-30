package com.buggy.rating.automate.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuggyCarsLandingPage {

    private WebDriver driver;

    @FindBy(xpath = "//my-home/div/div[1]/div/a/img")
    WebElement lamborghiniImg;

    @FindBy(xpath = "//my-home/div/div[1]/div/h2")
    WebElement popularMakeLbl;

    @FindBy(xpath = "//my-home/div/div[2]/div/a/img")
    WebElement popularModelImg;

    @FindBy(xpath = "//my-home/div/div[3]/div/a/img")
    WebElement overallRatingImg;

    public BuggyCarsLandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLamborghini() {
        lamborghiniImg.click();
    }

    public void goToPopularModel() {
        popularModelImg.click();
    }

    public void goToOverAllRating() {
        overallRatingImg.click();
    }

    public boolean verifyLandingPage() {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//my-home/div/div[1]/div/h2")));
        if (popularMakeLbl.getText().equals("Popular Make")) {
            return true;
        } else return false;
    }

}
