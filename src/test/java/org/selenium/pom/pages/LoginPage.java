package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "user-name") private static WebElement userNameField;
    @FindBy(how = How.ID, using = "password") private static WebElement userPassField;
    @FindBy(how = How.ID, using = "login-button") private static WebElement loginButton;
    @FindBy(how = How.XPATH , using = "//h3[@data-test='error']") private static WebElement loginErrorMessage;
    private static final By overlay = By.cssSelector("Put here the overlay css");

    public static void setUserName(){
        shortWait.until(ExpectedConditions.visibilityOf(userNameField)).clear();
        userNameField.sendKeys("standard_user");
    }

    public static void setWrongUserName(){
        shortWait.until(ExpectedConditions.visibilityOf(userNameField)).clear();
        userNameField.sendKeys("WrongUserName");
    }

    public static void setUserPass(){
        shortWait.until(ExpectedConditions.visibilityOf(userPassField)).clear();
        userPassField.sendKeys("secret_sauce");
    }

    public static void setWrongUserPass(){
        shortWait.until(ExpectedConditions.visibilityOf(userPassField)).clear();
        userPassField.sendKeys("WrongUserPass");
    }

    public static ProductsPage clickLoginButton(){
        waitForOverlaysToDisappear(overlay);
        shortWait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new ProductsPage(driver);
    }

    public static String getTextLoginErrorMessage() {
        return shortWait.until(ExpectedConditions.visibilityOf(loginErrorMessage)).getText();
    }

    public void loadLoginPage(){
        loadBaseURL("/");
    }
}
