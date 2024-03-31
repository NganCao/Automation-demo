package com.fnb.web.store.theme2.pages.product_list;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme1.pages.checkout.CheckoutDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.login.CheckOutLoginPage;
import com.fnb.web.store.theme1.pages.login.DataTest;
import com.fnb.web.store.theme1.pages.login.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class ProductListPage extends Setup {
    private HomePage homePage;
    private CheckOutLoginPage checkOutLoginPage;
    private CheckoutDataTest checkoutDataTest;
    private LoginPage loginPage;
    private DataTest dataTest;
    private By banner = By.xpath("//div[@class=\"banner-top-product-list\"]");
    private By category = By.id("wrapper-sticky-slider-category-product-list-theme1-id");
    private By sectionProduceList = By.xpath("//div[@class=\"section-product-list\"]");
    private By sectionProductListItem = By.xpath("//div[starts-with(@id,\"list-products-section-id\")]");
    private By sectionProductTitle = By.xpath("//div[starts-with(@class,\"product-list__title\")]/span[@class=\"title-name\"]");
    private By sectionProductQuantity = By.xpath("//div[starts-with(@class,\"product-list__title\")]/span[@class=\"quantity-products\"]");
    public static By productCardContainer = By.xpath("//div[starts-with(@id,\"list-products-section-id-\")]");
    public static String productContainerPath = "//div[starts-with(@id,\"list-products-section-id-\")]";  //"//div[starts-with(@class,\"product-list__container\")]";
    public static String productCardPath = "/parent::div/following-sibling::div/div"; //get following product container xpath
    public static String productAddToCartPath = "//div[@class=\"product-card__btn-add-to-cart\"]";
    public static By productCardItem = By.xpath("//div[contains(@class,\"product-card-theme1\")]");
    private By productTitle = By.xpath("//div[contains(@class,\"product-card-theme1\")]//div[@class=\"product-card__title\"]");
    private By priceSell = By.xpath("//div[contains(@class,\"product-card-theme1\")]//span[@class=\"product-card__price-sell\"]");
    private By priceDiscount = By.xpath("//div[contains(@class,\"product-card-theme1\")]//span[@class=\"product-card__price-discount\"]");
    public static By productAddToCart = By.xpath("//div[contains(@class,\"product-card-theme1\")]//div[@class=\"product-card__btn-add-to-cart\"]");
    //Notification popup
    public static By notificationOkayBTN = By.xpath("//div[contains(@class,\"notification-container-theme1\")]//button");
    //Message
    public static By addProductSuccessToast = By.xpath("//div[@class=\"toast-message-add-update-to-cart\"]");
    private By addProductSuccessToastSpan = By.xpath("//div[@class=\"toast-message-add-update-to-cart\"]");

    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public ProductListPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }
}
