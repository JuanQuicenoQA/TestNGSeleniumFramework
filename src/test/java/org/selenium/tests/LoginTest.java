package org.selenium.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.LoginPage;
import org.selenium.pom.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Login Feature")
public class LoginTest extends BaseTest {

    @Story("Failed Login")
    @Description("This is a description 1")
    @Test(description = "Should NOT be able to Login to the App")
    public void failed_Login_into_the_WebApp() throws InterruptedException {
        new LoginPage(driver).loadLoginPage();
        LoginPage.setWrongUserName();
        LoginPage.setUserPass();
        LoginPage.clickLoginButton();
        Assert.assertEquals(LoginPage.getTextLoginErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Story("Successfully Login")
    @Description("This is a description 2")
    @Test(description = "Should be able to Login to the App")
    public void successfully_Login_into_the_WebApp() throws InterruptedException {
        new LoginPage(driver).loadLoginPage();
        LoginPage.setUserName();
        LoginPage.setUserPass();
        ProductsPage productsPage = LoginPage.clickLoginButton();
        Assert.assertEquals(productsPage.getTittlePage(), "Products");
    }
}