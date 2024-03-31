package com.fnb.web.store.theme2.pages.login;

import com.fnb.utils.helpers.ExtentTestManager;
import com.fnb.utils.helpers.Helper;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme2.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme2.pages.home.HomePage;
import com.fnb.web.store.theme2.pages.product_details.ProductDetailsPage;
import com.fnb.web.store.theme2.pages.product_list.ProductListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckOutLoginPage extends Setup {
    private WebDriver driver;
    private HomePage homePage;
    private ProductListPage productListPage;
    private LoginPage loginPage;
    private CheckoutPage checkoutPage;
    private ProductDetailsPage productDetailsPage;
    private DataTest dataTest;
    public String actualRS;
    public String expectedRS;
    public static By loginBtnDialog = By.xpath("//div[@class=\"ant-modal-wrap modal_login_theme2\"]//button");

    public Helper helper;
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
        productListPage = new ProductListPage(driver);
    }

    /**
     * @param productURL   get URL to navigate to details page - will enhance after merging with admin platform
     * @param address      address
     * @param addressIndex index of address option - from 0
     * @return
     */
    public Boolean checkoutWithoutLoginOnProductList(String productURL, String address, int addressIndex) {
        helper.waitUtilElementVisible(driver.findElement(homePage.selectReceiver));
        String oldAddress = helper.getText(homePage.selectReceiver);
        homePage.clickSelectAddress();
        homePage.enterDeliveryAddress(address, addressIndex);
        String newAddress = helper.getText(homePage.selectReceiver);
        productDetailsPage.navigateToProductDetailsWithURL(productURL);
        helper.waitForJStoLoad();
        helper.waitUtilElementVisible(driver.findElement(productDetailsPage.productHeader));
        WebElement e = driver.findElement(productDetailsPage.addToCartBTN);
        helper.waitForClickable(e);
        try {
            helper.clickBtn(e);
            helper.waitUtilElementVisible(helper.getElement(productListPage.addProductSuccessToast));
        } catch (Exception exception) {
            helper.clickByJS(e);
            helper.checkDisplayElement(productListPage.addProductSuccessToast);
        }
        helper.waitForJStoLoad();
        helper.pressPageUpAction();
        helper.waitForClickable(driver.findElement(homePage.cartIcon));
        try {
            helper.waitForClickable(driver.findElement(homePage.cartIcon));
            helper.clickBtn(driver.findElement(homePage.cartIcon));
        } catch (Exception exception) {
            helper.clickByJS(driver.findElement(homePage.cartIcon));
        }
        try {
            helper.waitForJStoLoad();
            helper.waitUtilElementVisible(driver.findElement(homePage.cartCheckout));
            helper.clickBtn(driver.findElement(homePage.cartCheckout));
        } catch (Exception exception) {
            helper.waitUtilElementVisible(driver.findElement(homePage.cartCheckout));
            helper.clickByJS(driver.findElement(homePage.cartCheckout));
        }
        return helper.checkDisplayElement(loginBtnDialog);
    }
}
