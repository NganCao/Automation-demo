package com.fnb.web.store.theme2.pages.product_details;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class ProductDetailsPage extends Setup {
    public static By productHeader = By.id("productDetailHeader");
    public static By addToCartBTN = By.xpath("//div[@class=\"btn-add-to-cart\"]");

    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public ProductDetailsPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    public void navigateToProductDetailsWithURL(String url) {
        String urlFull = configObject.getUrlBase() + url;
        System.out.println(urlFull);
        helper.navigateTo(urlFull);
    }
}
