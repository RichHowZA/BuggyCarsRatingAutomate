package com.buggy.rating.automate.pages;

import com.buggy.rating.automate.beans.RegisterDataBean;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BuggyRatingRegisterPage {
    private WebDriver driver;

    @FindBy(id = "username")
    WebElement loginTxtBox;

    @FindBy(id = "firstName")
    WebElement firstNameTxtBox;

    @FindBy(id = "lastName")
    WebElement lastNameTxtBox;

    @FindBy(id = "password")
    WebElement passwordTxtBox;

    @FindBy(id = "confirmPassword")
    WebElement confirmPasswordTxtBox;

    @FindBy(xpath = "//my-register/div/div/form/button")
    WebElement registerBtn;

    @FindBy(xpath = "//my-register/div/div/form/div[6]")
    WebElement registrationFeedback;

    @FindBy(xpath = "//my-register/div/div/h2")
    WebElement registrationPageLabel;
    public BuggyRatingRegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillUpRegistration(RegisterDataBean registerDataBean) {
        loginTxtBox.sendKeys(registerDataBean.getLogin());
        firstNameTxtBox.sendKeys(registerDataBean.getFirstName());
        lastNameTxtBox.sendKeys(registerDataBean.getLastName());
        passwordTxtBox.sendKeys(registerDataBean.getPassword());
        confirmPasswordTxtBox.sendKeys(registerDataBean.getPassword());
    }

    public void submitRegister() {
        registerBtn.click();
    }

    public boolean validateRegistration()
    {
        if(registrationFeedback.getText().equals("Registration is successful"))
        {return true;}
        else return false;
    }

    public boolean validateRegistrationPage()
    {
        if(registrationPageLabel.getText().equals("Register with Buggy Cars Rating"))
        {return true;}
        else return false;
    }


}
