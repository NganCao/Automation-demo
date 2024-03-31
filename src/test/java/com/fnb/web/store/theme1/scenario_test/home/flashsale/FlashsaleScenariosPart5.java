package com.fnb.web.store.theme1.scenario_test.home.flashsale;

import com.fnb.utils.api.admin.helpers.JsonAPIAdminReader;
import com.fnb.utils.helpers.Helper;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.login.DataTest;
import com.fnb.web.store.theme1.pages.product_details.EditOrder;
import com.fnb.web.store.theme1.pages.product_details.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FlashsaleScenariosPart5 extends BaseTest {
    //Check flash sale with special branch
    //Check stop flash sale by admin on Edit order, product list, checkout
    // Testcase:https://mediastep1-my.sharepoint.com/:x:/g/personal/ngan_cao_thi_kim_gosell_vn/EQtsv7XuR7VOuGBoZpHOKRkBCdTct0yNUZ74Q5qIB96Bsg?e=CUOyYp&nav=MTVfe0QxN0U3NDg1LTM0RjUtNDk0MC1BRDgwLTg0Rjg1NjZBRjZERn0
    private HomePage homePage;
    private ProductDetailsPage productDetailsPage;
    private EditOrder editOrder;
    private CheckoutPage checkoutPage;
    private HomeDataTest homeDataTest;
    private DataTest loginDataTest;
    private Helper helper;
    private int addEndAfterStartMinute = 1;
    private int addEndAfterEndMinute = 90;
    private String currentWindow = "";
    private String checkoutHandle = "";
    private String orderHandle = "";
    private int quantity = 0;
    private int maximumLimit = 0;
    private String productDetailURL = "";
    private List<String> productFlashSale = new ArrayList<>();
    private String productName = "";
    private int cartQuantity = 0;
    private int okayFlashSaleEndedDialogIndex = 1;
    private String currentLanguage;
    private JsonAPIAdminReader.FlashSaleProduct flashSaleProduct;
    private String createFlashSaleObj = "createFlashSale";

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = storePage().navigateToHomePage();
        productDetailsPage = new ProductDetailsPage(getDriver());
        editOrder = new EditOrder(getDriver());
        checkoutPage = new CheckoutPage(getDriver());
        helper = storePage().helper;
    }

    @Test(priority = 1, testName = "Verify flash sale tag on Product details when flash sale is not in the selected branch on product details page")
    public void FB2331() {
        cartQuantity = 1;
        homePage.createFlashSaleSpecialBranch(addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        productFlashSale = homePage.clickProductFlashSale(0);
        productName = productFlashSale.get(0);
        productDetailsPage.addProductToCartWithQuantity(cartQuantity);
        helper.refreshPage();
        homePage.selectBranchWithName(homePage.getBranchNameMissingProductByEnv());
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);

    }

    @Test(priority = 2, testName = "Verify price on Product details when flash sale is not in the selected branch on product details page")
    public void FB2332() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    @Test(priority = 3, testName = "Verify flash sale tag on Product details when flash sale is not in the selected branch on related  product")
    public void FB234() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(homePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    //edit cart
    @Test(priority = 4, testName = "Verify flash sale tag when flash sale is not in the selected branch on edit cart")
    public void FB2351() {
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), homePage.actualRS);
    }

    @Test(priority = 5, testName = "Verify price when flash sale is not in the selected branch on edit cart")
    public void FB2352() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, false, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 6, testName = "Check flash sale on edit order checkout with product has many variations - incorrect size")
    public void FB2361() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, true, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 7, testName = "Check price on edit order checkout with product has many variations - incorrect size")
    public void FB2362() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, false, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 8, testName = "Verify flash sale tag on Product list when stop flash sale then reloaded ")
    public void FB12015() {
        homePage.stopFlashSale(homePage.flashSaleName);
    }

    //flash sale has stopped by admin - click update button on edit order
    @Test(priority = 100, testName = "Check flash sale on product detail page on new tab when flash sale has stopped by admin before Clicking complete button")
    public void FB1681() {
        quantity = 10;
        maximumLimit = 2;
        cartQuantity = 1;
        storePage().navigateToHomePage();
        homePage.createFlashSale(addEndAfterStartMinute, addEndAfterEndMinute, false);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        productFlashSale = homePage.clickProductFlashSale(0);
        productName = productFlashSale.get(0);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        productDetailURL = helper.getCurrentURL();
        //product details
        currentWindow = helper.getCurrentWindow();
        //edit cart
        helper.openNewTab(productDetailURL);
        orderHandle = helper.getCurrentWindow();
        productDetailsPage.addToCart();
        productDetailsPage.openEditCartFromCart();
        //checkout
        helper.openNewTab(productDetailURL);
        checkoutHandle = helper.getCurrentWindow();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        currentLanguage = homePage.getCurrentLanguage();
        homePage.stopFlashSale(homePage.flashSaleName);
        Assert.assertTrue(editOrder.checkFlipCounterWhenFlashSaleEnded(), "flip zero did not displayed");
    }

    @Test(priority = 101, testName = "Click update button on edit order checkout")
    public void FB1682() {
        editOrder.clickUpdateBtn();
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), checkoutPage.actualRS);
    }

    @Test(priority = 102, testName = "Click okay button then check the number of dialog is displaying")
    public void FB1683() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(okayFlashSaleEndedDialogIndex), checkoutPage.actualRS);
    }

    @Test(priority = 103, testName = "Check price change from flash sale price to original price on checkout")
    public void FB1684() {
        Assert.assertTrue(checkoutPage.checkPriceOfProduct(false, flashSaleProduct), checkoutPage.actualRS);
    }

    @Test(priority = 104, testName = "Check display of flip countdown zero on edit cart")
    public void FB1671() {
        helper.switchBackCurrentTab(orderHandle);
        Assert.assertFalse(editOrder.checkFlipCounterWhenFlashSaleEnded(), "Flip zero displayed");
    }

    @Test(priority = 105, testName = "Click update button on edit cart")
    public void FB1672() {
        editOrder.clickUpdateBtn();
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), checkoutPage.actualRS);
    }

    @Test(priority = 106, testName = "Click okay button then check the number of dialog is displaying")
    public void FB1673() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(okayFlashSaleEndedDialogIndex), checkoutPage.actualRS);
    }

    @Test(priority = 107, testName = "Check price change from flash sale price to original price on cart")
    public void FB1674() {
        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, false, false), homePage.actualRS);
    }

    @Test(priority = 108, testName = "Check display of flip countdown zero on product details")
    public void FB1661() {
        helper.switchBackCurrentTab(currentWindow);
        Assert.assertFalse(productDetailsPage.checkFlipCounterWhenFlashSaleEnded(), "Flip zero displayed");
    }

    @Test(priority = 109, testName = "Click add to cart button on product details")
    public void FB1662() {
        productDetailsPage.clickAddToCart();
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), checkoutPage.actualRS);
    }

    @Test(priority = 110, testName = "Click okay button then check the number of dialog is displaying")
    public void FB1663() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(okayFlashSaleEndedDialogIndex), checkoutPage.actualRS);
    }

    @Test(priority = 111, testName = "Check price change from flash sale price to original price on product details")
    public void FB1664() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    //Check flash sale tag when stopped by admin
    @Test(priority = 119, testName = "Verify flash sale tag on Edit Cart when stop flash sale without reloaded")
    public void FB181() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        quantity = 10;
        maximumLimit = 2;
        cartQuantity = 1;
        storePage().navigateToHomePage();
        homePage.createFlashSale(addEndAfterStartMinute, addEndAfterEndMinute, false);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        productFlashSale = homePage.clickProductFlashSale(0);
        productName = productFlashSale.get(0);
        productDetailURL = helper.getCurrentURL();
        //product details
        currentWindow = helper.getCurrentWindow();
        //edit cart
        helper.openNewTab(productDetailURL);
        orderHandle = helper.getCurrentWindow();
        productDetailsPage.addToCart();
        productDetailsPage.openEditCartFromCart();
        //checkout
        helper.openNewTab(productDetailURL);
        checkoutHandle = helper.getCurrentWindow();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        currentLanguage = homePage.getCurrentLanguage();
        homePage.stopFlashSale(homePage.flashSaleName);
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), "Flash sale did not display before reloading Checkout page");
    }

    @Test(priority = 120, testName = "Verify flash sale tag on Edit Cart when stop flash sale then reloaded")
    public void FB182() {
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), "Flash sale still display after reloading Checkout page");
    }

    @Test(priority = 121, testName = "Verify flash sale tag on Edit Order checkout when stop flash sale without reloaded")
    public void FB183() {
        helper.switchBackCurrentTab(orderHandle);
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), "Flash sale did not display before reloading edit cart");
    }

    @Test(priority = 121, testName = "Verify flash sale tag on Edit Order checkout when stop flash sale then reloaded")
    public void FB184() {
        helper.refreshPage();
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), "Flash sale still display after reloading edit cart");
    }

    @Test(priority = 122, testName = "Verify flash sale tag on Product details when stop flash sale without reloaded")
    public void FB12008() {
        helper.switchBackCurrentTab(currentWindow);
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), "Flash sale did not display before reloading edit cart");
    }

    @Test(priority = 122, testName = "Verify flash sale tag on related product when stop flash sale without reloaded")
    public void FB231() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    @Test(priority = 123, testName = "Verify flash sale tag on Product details when stop flash sale then reloaded")
    public void FB12013() {
        helper.refreshPage();
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), "Flash sale still display after reloading edit cart");
    }

    @Test(priority = 124, testName = "Verify flash sale tag on related product when stop flash sale without reloaded")
    public void FB233() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(homePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }
}
