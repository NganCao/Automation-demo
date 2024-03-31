package com.fnb.web.store.theme1.pages.login;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme1.pages.checkout.CheckoutDataTest;
import com.fnb.web.store.theme1.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.product_details.ProductDetailsPage;
import com.fnb.web.store.theme1.pages.product_list.ProductListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CheckOutLoginPage extends Setup {
    private HomePage homePage;
    private ProductListPage productListPage;
    private LoginPage loginPage;
    private CheckoutPage checkoutPage;
    private ProductDetailsPage productDetailsPage;
    private DataTest dataTest;
    private HomeDataTest homeDataTest;
    private JsonReader jsonReader;
    public String actualRS;
    public String expectedRS;
    public static By loginBtnDialog = By.xpath("//div[contains(@class,\"confirmation-modal\")]//div[contains(@class,\"ant-modal-footer\")]//button");
    public static By loginDialog = By.xpath("//div[contains(@class,\"confirmation-modal\")]//div[contains(@class,\"ant-modal-footer\")]");

    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;

    public CheckOutLoginPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        checkoutPage = new CheckoutPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
    }

    public Boolean checkoutWithoutLoginOnProductList() {
        homePage.addToCartFromBestsellingProduct();
        try {
            helper.waitForJStoLoad();
            helper.waitUtilElementVisible(driver.findElement(homePage.cartCheckout));
            helper.clickBtn(driver.findElement(homePage.cartCheckout));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.waitUtilElementVisible(driver.findElement(homePage.cartCheckout));
            helper.clickByJS(driver.findElement(homePage.cartCheckout));
        }
        helper.waitForPresence(loginDialog);
        return helper.checkDisplayElement(loginBtnDialog);
    }

    public void checkoutWithLogin(String currentLanguage, String phone, String password, Boolean isEnterAddress, String address, int addressIndex, boolean checkout) {
        try {
            helper.waitForPresence(loginBtnDialog);
            try {
                helper.waitUtilElementVisible(driver.findElement(loginDialog));
            } catch (Exception ex) {
                Log.info(ex.getMessage());
                helper.visibleOfLocated(loginBtnDialog);
            }
            try {
                helper.clickBtn(driver.findElement(loginBtnDialog));
            } catch (Exception ex) {
                Log.info(ex.getMessage());
                helper.clickByJS(driver.findElement(loginBtnDialog));
            }
            loginPage.submitLoginByPassword(phone, password);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            System.out.println("NO LOGIN");
        }
        if (checkout) checkoutPage.checkoutAction(currentLanguage, isEnterAddress, address, addressIndex);
    }

    public String viewOrderAfterCheckoutWithLogin(String currentLanguage, String phone, String password, Boolean isEnterAddress, String address, int addressIndex) {
        checkoutWithLogin(currentLanguage, phone, password, isEnterAddress, address, addressIndex, true);
        try {
            helper.waitForPresence(checkoutPage.viewOrder);
        } catch (Exception ex) {
            Log.info(ex.getMessage());
        }
        try {
            helper.clickBtn(driver.findElement(checkoutPage.viewOrder));
        } catch (Exception ex) {
            Log.info(ex.getMessage());
            try {
                helper.clickByJS(driver.findElement(checkoutPage.viewOrder));
            } catch (Exception e) {
                Log.info(e.getMessage());
                helper.actionScrollAndClickToElement(driver.findElement(checkoutPage.viewOrder));
            }
        }
        System.out.println(driver.getCurrentUrl());
        if (!helper.waitForURLContains("my-profile/2/")) {
            try {
                helper.clickBtn(driver.findElement(checkoutPage.viewOrder));
            } catch (Exception ex) {
                Log.info(ex.getMessage());
                helper.clickByJS(driver.findElement(checkoutPage.viewOrder));
            }
            System.out.println("click again");
            helper.waitForURLContains("my-profile/2/");
        }
        return helper.getCurrentURL();
    }

    public void checkoutWithoutLogin(String currentLanguage, Boolean isEnterAddress, String address, int addressIndex) {
        checkoutPage.checkoutAction(currentLanguage, isEnterAddress, address, addressIndex);
    }
}