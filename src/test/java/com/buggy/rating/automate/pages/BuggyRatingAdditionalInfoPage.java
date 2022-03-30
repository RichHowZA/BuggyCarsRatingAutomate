package com.buggy.rating.automate.pages;

import com.buggy.rating.automate.beans.AdditionalInfoBean;
import com.buggy.rating.automate.beans.BasicInfoBean;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BuggyRatingAdditionalInfoPage {
    private WebDriver driver;

    @FindBy(xpath = "//my-profile/div/form/div[1]/div[2]/div/h3")
    WebElement pageLandingLbl;

    @FindBy(name = "firstName")
    WebElement firstNameTxt;

    @FindBy(name = "lastName")
    WebElement lastNameTxt;

    @FindBy(name = "gender")
    WebElement genderTxt;

    @FindBy(name = "age")
    WebElement ageTxt;

    @FindBy(name = "address")
    WebElement addressTxt;

    @FindBy(name = "phone")
    WebElement phoneTxt;

    @FindBy(name = "hobby")
    WebElement hobbySelect;

    @FindBy(xpath = "//my-profile/div/form/div[2]/div/button")
    WebElement saveBtn;

    @FindBy(xpath = "//my-profile/div/form/div[3]/div")
    WebElement saveSuccessMsg;

    public BuggyRatingAdditionalInfoPage(WebDriver driver) {
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public boolean verifyAdditionalInfoPage() {
        if (pageLandingLbl.getText().equals("Additional Info")) {
            return true;
        } else return false;
    }

    public void fillBasicInfo(BasicInfoBean basicInfo) {
        firstNameTxt.clear();
        firstNameTxt.sendKeys(basicInfo.getFirstName());
        lastNameTxt.clear();
        lastNameTxt.sendKeys(basicInfo.getLastName());
    }

    public void fillAdditionalInfo(AdditionalInfoBean additionalInfo) {
        genderTxt.clear();
        genderTxt.sendKeys(additionalInfo.getGender());
        ageTxt.clear();
        ageTxt.sendKeys(additionalInfo.getAge());
        addressTxt.clear();
        addressTxt.sendKeys(additionalInfo.getAddress());
        phoneTxt.clear();
        phoneTxt.sendKeys(additionalInfo.getPhone());
        Select hobbyItemSelect = new Select(hobbySelect);
        hobbyItemSelect.selectByVisibleText(additionalInfo.getHobby());
    }

    public void saveTheForm() {
        saveBtn.click();
    }

    public boolean verifyASuccessfulSavePage() {
        if (saveSuccessMsg.getText().equals("The profile has been saved successful")) {
            return true;
        } else return false;
    }
}
