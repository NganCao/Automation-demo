package com.fnb.web.store.theme2.pages.checkout;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckoutPage extends Setup {
    private CheckoutDataTest dataTest;
    private WebDriver driver;
    private String actualRS;
    private String expectedRS;
    //header
    public static By checkoutHeader = By.id("themeHeaderCheckout");
    //product info
    public static By totalQuantity = By.xpath("//div[@class=\"total\"]/div[@class=\"quantity\"]");
    //user info
    public By userName = By.xpath("(//div[@class=\"detail-receiver\"])[1]");
    public By phoneNumber = By.xpath("(//div[@class=\"detail-receiver\"])[2]");

    public Helper helper;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public CheckoutPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    public Boolean checkDisplayOfUserName() {
        try {
            helper.waitUtilElementVisible(driver.findElement(userName));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public Boolean checkDisplayOfPhoneNumber() {
        try {
            helper.waitUtilElementVisible(driver.findElement(userName));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public void checkDisplayTotalQuantity () {
        helper.checkDisplayElement(totalQuantity);
    }

    public Boolean checkValueTotalQuantity(String expected) {
        helper.waitUtilElementVisible(driver.findElement(totalQuantity));
        actualRS = driver.findElement(totalQuantity).getText().trim();
        return actualRS.contains(expected);
    }

    public Boolean checkValueOfUsername () {
        return driver.findElement(userName).getAttribute("value").trim().equals(dataTest.USERNAME);
    }
}
