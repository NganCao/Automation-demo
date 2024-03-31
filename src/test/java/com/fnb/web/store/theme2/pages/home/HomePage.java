package com.fnb.web.store.theme2.pages.home;

import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme2.pages.login.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class HomePage extends Setup {
    public static By storeLogo = By.xpath("//img[@class=\"ant-image-img\"]");
    public static By languageSwitch = By.xpath("//a[contains(@class,\"link-language\")]");
    public static By languageTxt = By.xpath("//a[contains(@class,\"link-language\")]/span");
    public static By vietnameseOption = By.xpath("(//div[contains(@class,\"language-top-bar\")]//a)[1]");
    public static By englishOption = By.xpath("(//div[contains(@class,\"language-top-bar\")]//a)[2]");
    public static By accountIcon = By.xpath("//a[contains(@class,\"user-icon\")]");
    public static By accountInfo = By.xpath("//div[contains(@class,\"user-profile-contain\")]");
    public static By accountLogout = By.xpath("//div[@class=\"login_content_theme1\"]");
    public static By loginRegisterDiv = By.xpath("//div[@class=\"ant-popover-inner\"]//a");
    //banner
    public static By bannerTrack = By.id("themeBanner");
    //------------------ address
    public static By selectReceiver = By.xpath("//div[@class=\"delivery-address-header-box\"]");
    private By getSelectReceiverTxt = By.xpath("//div[@class=\"delivery-address-header-box\"]//div[@class=\"text-delivery-address\"]/span");
    public By deliverySpan = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//span[text()=\"Delivery\" or text()=\"Giao hàng\"]");
    private By pickupSpan = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//span[text()=\"Pickup\" or text()=\"Tự lấy hàng\"]");
    public By enterAddressField = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//input");
    public By addressBoxParent = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//div[@class=\"delivery-address-popover\"]");
    public By addressBoxItems = By.xpath("//div[contains(@class,\"modal-delivery-address-selector\")]//div[@class=\"address-box\"]");
    public By selectedAddressToast = By.xpath("//div[@class = \"ant-message-notice-content\"]");
    //------------------- cart
    public static By cartIcon = By.xpath("//a[@class=\"pointer\"]");
    public static By cartContainer = By.xpath("//div[contains(@class,\"cart-header-theme-2\")]");
    public static By cartCheckout = By.xpath("//button[contains(@class,\"action-checkout\")]");
    public static By cartQuantity = By.xpath("//span[contains(@class,\"ant-scroll-number-only-unit\")]");

    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;

    public HomePage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    public String getCurrentLanguage() {
        return helper.getCurrentLanguageHelper(languageTxt);
    }

    //--------------------- check display of elements after logged in
    public Boolean checkDisplayDefaultLogoutBtn() {
        return driver.findElement(accountLogout).isDisplayed();
    }

    //--------------------- checkUI
    public Boolean checkDisplayOfStoreLogo() {
        return helper.checkDisplayElement(storeLogo);
    }

    public void clickAccountIcon() {
        try {
            helper.waitForPresence(accountIcon);
            helper.visibleOfLocated(accountIcon);
            helper.waitUtilElementVisible(driver.findElement(accountIcon));
            helper.clickBtn(driver.findElement(accountIcon));
        } catch (Exception exception) {
            helper.clickByJS(helper.getElement(accountIcon));
        }
    }

    /**
     * navigate to Login page
     *
     * @return Login page
     */
    public LoginPage clickLoginRegister() {
        clickAccountIcon();
        WebElement loginRegister = driver.findElement(loginRegisterDiv);
        helper.waitUtilElementVisible(loginRegister);
        loginRegister.click();
        return new LoginPage(driver);
    }

    public void clickLogout() {
        clickAccountIcon();
        WebElement logout = driver.findElement(accountLogout);
        helper.waitUtilElementVisible(logout);
        logout.click();
    }

    public void clickSelectAddress() {
        helper.waitUtilElementVisible(driver.findElement(selectReceiver));
        helper.clickBtn(driver.findElement(selectReceiver));
    }

    /**
     * Type and select address
     *
     * @param address
     * @param addressIndex get from a list so index from 0
     */
    public void enterDeliveryAddress(String address, int addressIndex) {
        helper.waitForPresence(deliverySpan);
        helper.visibleOfLocated(deliverySpan);
        helper.waitUtilElementVisible(driver.findElement(deliverySpan));
        helper.clickBtn(driver.findElement(deliverySpan));
        helper.waitForPresence(deliverySpan);
        helper.visibleOfLocated(deliverySpan);
        helper.waitUtilElementVisible(driver.findElement(enterAddressField));
        helper.enterData(driver.findElement(enterAddressField), address);
        helper.visibleOfLocated(addressBoxParent);
        helper.waitForPresence(addressBoxParent);
        helper.waitUtilElementVisible(driver.findElement(addressBoxParent));
        helper.selectOptionDropdown(addressBoxItems, addressIndex);
    }

    //Banner
    public Boolean checkDisplayBanner() {
        return helper.visibleOfLocated(bannerTrack);
    }
}
