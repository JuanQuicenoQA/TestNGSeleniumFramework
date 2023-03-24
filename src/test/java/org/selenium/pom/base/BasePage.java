package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected static WebDriver driver;
    protected static WebDriverWait shortWait;
    protected static WebDriverWait longWait;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        longWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void loadBaseURL(String endpoint){
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endpoint);
    }

    public static void waitForOverlaysToDisappear(By overlay){
        List<WebElement> overlays = driver.findElements(overlay);
        if(overlays.size() > 0){
            shortWait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            System.out.println("OVERLAYS INVISIBLE");
        } else {
            System.out.println("OVERLAYS NOT FOUND");
        }
    }
}
