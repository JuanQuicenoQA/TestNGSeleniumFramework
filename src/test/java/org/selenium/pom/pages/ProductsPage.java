package org.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(how = How.CLASS_NAME, using = "title") private WebElement tittlePage;
    @FindBy(how = How.ID,using = "shopping_cart_container") private WebElement yourCartButton;

    public String getTittlePage() {
        return shortWait.until(ExpectedConditions.visibilityOf(tittlePage)).getText();
    }

    public void loadProductsPage(){
        loadBaseURL("/inventory.html");
    }
}
