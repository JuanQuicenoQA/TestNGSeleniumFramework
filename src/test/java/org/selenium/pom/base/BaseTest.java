package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.factory.DriverManager;
import org.selenium.utils.CookieUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.selenium.factory.DriverManager.getDriver;

public class BaseTest {
    protected static WebDriver driver;

    @Parameters({"browserName"})
    @BeforeMethod()
    public void startDriver(String browserName) {
        driver = DriverManager.initializeDriver(System.getProperty("browser", browserName));
    }

    @Parameters({"browserName"})
    @AfterMethod
    public void quitDriver(String browserName, ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
           File destScreenshotFile = new File("screenshots" + File.separator + browserName + File.separator +
                   result.getTestClass().getRealClass().getSimpleName() + "_" + result.getMethod().getMethodName() + ".png");
            takeScreenshot(destScreenshotFile);
        }
        driver.quit();
    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for(Cookie cookie: seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }

    private void takeScreenshot(File destScreenshotFile) throws IOException {
        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);
        File scrScreenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrScreenshotFile, destScreenshotFile);
    }
}
