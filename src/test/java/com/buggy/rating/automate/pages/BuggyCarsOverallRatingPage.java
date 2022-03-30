package com.buggy.rating.automate.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BuggyCarsOverallRatingPage {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/my-app/div/main/my-overall/div/div/table/thead/tr/th[2]/a")
    WebElement makeTxt;

    public BuggyCarsOverallRatingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean validateOvarallRatingPageNavigation() {
        return makeTxt.isDisplayed();
    }

}
