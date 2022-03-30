package com.buggy.rating.automate.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LamborghiniAventadorVotePage {
    private WebDriver driver;

    @FindBy(xpath = "//my-model/div/div[1]/div[3]/div[2]/div[2]/div/button")
    WebElement voteButton;

    @FindBy(id = "comment")
    WebElement commentTxtBox;

    @FindBy(xpath = "//my-model/div/div[2]/h3")
    WebElement makeBanner;

    @FindBy(xpath = "//my-model/div/div[1]/div[3]/div[2]/div[1]/h4/strong")
    WebElement voteCount;

    @FindBy(xpath = "//my-model/div/div[1]/div[3]/div[2]/div[2]/p")
    WebElement thankYouNote;

    public LamborghiniAventadorVotePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addComment(String comment) {
        commentTxtBox.sendKeys(comment);
    }

    public void clickVote() {
        voteButton.click();
    }

    public String getVoteCount() {
        return voteCount.getText();
    }

    public boolean validateMake(String make) {
        if (makeBanner.getText().contains(make)) {
            return true;
        } else return false;
    }

    public boolean validateVoteNote() {
        if (thankYouNote.getText().contains("Thank you for your vote!")) {
            return true;
        } else return false;
    }

}
