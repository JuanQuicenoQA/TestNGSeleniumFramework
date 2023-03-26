package org.selenium.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.selenium.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("API Feature")
public class ApiTest extends BaseTest {

    @Story("Get Browser Cookies Successfully")
    @Description("This is a description 3")
    @Test(description = "Should be able to get Browser cookies")
    public void get_Cookies_From_Browser() throws InterruptedException {
        String BrowserCookies = new SignUpApi().fetchRegisterNonceValueUsingGroovy();
        int BrowserCookiesLength = BrowserCookies.length();
        Assert.assertNotNull(BrowserCookies);
        Assert.assertEquals(BrowserCookiesLength,10);
    }
}
