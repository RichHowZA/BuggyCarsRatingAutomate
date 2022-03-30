package com.buggy.rating.automate.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LamborghiniRatePage {
    private WebDriver driver;

    @FindBy(xpath = "//my-make/div/div[2]/div/div/table/tbody/tr[3]/td[1]/a/img")
    WebElement aventadorImg;

    @FindBy(xpath = "//my-make/div/div[2]/div/div/table/tbody/tr[3]/td[4]")
    WebElement avantadorVoteEle;

    @FindBy(xpath = "//my-make/div/div[1]/div/h3")
    WebElement makeBanner;

    public LamborghiniRatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectAventadorforVote() {
        aventadorImg.click();
    }

    public String getAventadorVoteCount() {
        return avantadorVoteEle.getText();
    }

    public boolean validateMake(String make) {
        if (makeBanner.getText().contains(make)) {
            return true;
        } else return false;
    }
}
