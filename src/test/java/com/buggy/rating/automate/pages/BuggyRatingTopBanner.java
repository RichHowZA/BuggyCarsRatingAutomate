package com.buggy.rating.automate.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuggyRatingTopBanner {

    private WebDriver driver;

    @FindBy(name = "login")
    WebElement loginTxtBox;

    @FindBy(name = "password")
    WebElement passwordTxtBox;

    @FindBy(xpath = "//my-login/div/form/button")
    WebElement loginBtn;

    @FindBy(xpath ="//my-login/div/form/a")
    WebElement registerBtn;

    @FindBy(xpath = "//my-login/div/ul/li[1]/span")
    WebElement loginGreeting;

    @FindBy(xpath = "//header/nav/div/my-login/div/ul/li[2]/a")
    WebElement profileLink;

    @FindBy(xpath = "/html/body/my-app/header/nav/div/a")
    WebElement buggyRatingLink;

    public BuggyRatingTopBanner(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setLoginUserName(String userName) {
        loginTxtBox.clear();
        loginTxtBox.sendKeys(userName);
    }

    public void setLoginPassword(String password) {
        passwordTxtBox.clear();
        passwordTxtBox.sendKeys(password);
    }

    public void clickLogin() {
        loginBtn.click();
    }

    public void clickRegister()
    {
        registerBtn.click();
    }

    public boolean validateLogin(String firstName)
    {
        if(loginGreeting.getText().contains(firstName))
        {return true;}
        else return false;
    }

    public void navigateToProfile()
    {
        profileLink.click();
    }

    public void goToBuggyRatingPage()
    {
        buggyRatingLink.click();
    }
}
