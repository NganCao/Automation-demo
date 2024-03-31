package com.fnb.web.store.theme1.scenario_test.home.flashsale;

import com.fnb.utils.api.admin.helpers.JsonAPIAdminReader;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.login.DataTest;
import com.fnb.web.store.theme1.pages.myOrder.MyOrderPage;
import com.fnb.web.store.theme1.pages.product_details.EditOrder;
import com.fnb.web.store.theme1.pages.product_details.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FlashsaleScenariosPart3 extends BaseTest {
    //Check product details, checkout, edit order
    // Testcase:https://mediastep1-my.sharepoint.com/:x:/g/personal/ngan_cao_thi_kim_gosell_vn/EQtsv7XuR7VOuGBoZpHOKRkBCdTct0yNUZ74Q5qIB96Bsg?e=CUOyYp&nav=MTVfe0QxN0U3NDg1LTM0RjUtNDk0MC1BRDgwLTg0Rjg1NjZBRjZERn0
    private HomePage homePage;
    private ProductDetailsPage productDetailsPage;
    private EditOrder editOrder;
    private CheckoutPage checkoutPage;
    private HomeDataTest homeDataTest;
    private DataTest loginDataTest;
    private MyOrderPage myOrderPage;
    private Helper helper;
    private int addComingStartMinute = 40;
    private int addComingEndMinute = 20;
    private int addEndAfterStartMinute = 1;
    private int addEndAfterEndMinute = 90;
    private int addEndedStartMinute = 1;
    private int addEndedEndMinute = 1;
    private String productName = "";
    private String productDetailURL = "";
    private String currentWindow = "";
    private String checkoutHandle = "";
    private String cartHandle = "";
    private String orderHandle = "";
    private int quantity = 0;
    private int maximumLimit = 0;
    private int cartQuantity = 0;
    private int okayFlashSaleEndedDialogIndex = 1;
    private List<Integer> toppingTotalPrice = new ArrayList<>();
    private String sizeName = "";
    private List<String> productFlashSale = new ArrayList<>();
    private List<String> productNotFlashSale = new ArrayList<>();
    private String currentLanguage = "";
    private JsonAPIAdminReader.FlashSaleProduct flashSaleProduct;
    private String createFlashSaleObj = "createFlashSale";

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = storePage().navigateToHomePage();
        checkoutPage = new CheckoutPage(getDriver());
        myOrderPage = new MyOrderPage(getDriver());
        productDetailsPage = new ProductDetailsPage(getDriver());
        editOrder = new EditOrder(getDriver());
        helper = storePage().helper;
    }

    @Test(priority = 1, testName = "Check flash sale on product details when flash sale is coming")
    public void FB1091() {
        maximumLimit = 2;
        quantity = 10;
        cartQuantity = 5;
        currentWindow = helper.getCurrentWindow();
        homePage.createFlashSaleNotFullVariations(addComingStartMinute, addComingEndMinute);
        helper.refreshPage();
        productFlashSale = homePage.clickProductFlashSale(0);
        productName = productFlashSale.get(0);
        String productDetailURL = helper.getCurrentURL();
        storePage().navigateToHomePage();
        helper.openNewTab(productDetailURL);
        cartHandle = helper.getCurrentWindow();
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 2, testName = "Check price on product details when flash sale is coming")
    public void FB1092() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    @Test(priority = 3, testName = "Check flash sale on product details when flash sale is end after before reload", description = "Update coming flash sale to end after")
    public void FB1101() {
        helper.switchToWindowHandle(currentWindow);
        homePage.updateTimeFlashSale(homePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        helper.switchToWindowHandle(cartHandle);
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 4, testName = "Check price on product details when flash sale is coming")
    public void FB1102() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    @Test(priority = 4, testName = "Check flash sale on related product when flash sale is coming")
    public void FB225() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(homePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    @Test(priority = 5, testName = "Check flash sale on product details when flash sale is end after after reload")
    public void FB111() {
        helper.refreshPage();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 6, testName = "Check price on product details when flash sale is end after after reload")
    public void FB1112() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    @Test(priority = 6, testName = "Check flash sale on related product when flash sale is end after after reload")
    public void FB226() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    @Test(priority = 7, testName = "Check flash sale on product details on new tab when flash sale is out of time before reload")
    public void FB1131() {
        helper.switchToWindowHandle(currentWindow);
        homePage.updateTimeFlashSale(homePage.flashSaleName, addEndedStartMinute, addEndedEndMinute);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndedStartMinute + addEndedEndMinute);
        helper.refreshPage();
        helper.switchToWindowHandle(cartHandle);
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 8, testName = "Check price on product details on new tab when flash sale is out of time before reload")
    public void FB1132() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    @Test(priority = 8, testName = "Check flash sale on related product on new tab when flash sale is out of time before reload")
    public void FB228() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    @Test(priority = 9, testName = "Check flash sale on product details on new tab when flash sale is out of time after reload")
    public void FB1141() {
        helper.refreshPage();
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 10, testName = "Check price on product details when flash sale is coming")
    public void FB1142() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    @Test(priority = 10, testName = "Check flash sale on related product when flash sale is coming")
    public void FB229() {
        Assert.assertTrue(productDetailsPage.checkDisplayFlashSaleRelatedProduct(homePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    //Product and included topping
    @Test(priority = 11, testName = "Check flash sale on product details with product has many variations")
    public void FB1161() {
        helper.closeAllOpenTabExceptMainTab(currentWindow);
        homePage.stopFlashSale(homePage.flashSaleName);
        storePage().navigateToHomePage();
        maximumLimit = 2;
        quantity = 10;
        cartQuantity = 5;
        homePage.createFlashSaleWithVariation(addEndAfterStartMinute, addEndAfterEndMinute, "M", maximumLimit, quantity);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndedStartMinute);
        helper.refreshPage();
        productFlashSale = homePage.clickProductFlashSale(1);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 12, testName = "Check price on product details with product has many variations")
    public void FB1162() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 13, testName = "Check flash sale on edit cart with product has many variations")
    public void FB1411() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        helper.sleep(1);
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 14, testName = "Check price on edit cart with product has many variations")
    public void FB1412() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 15, testName = "Check flash sale on edit order checkout with product has many variations")
    public void FB1421() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, true, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 16, testName = "Check price on edit order checkout with product has many variations")
    public void FB1422() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 17, testName = "Check flash sale on product details with product has many variations - incorrect size")
    public void FB1171() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        sizeName = "L";
        try {
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        }
        productName = productNotFlashSale.get(0);
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 18, testName = "Check price on product details with product has many variations - incorrect size")
    public void FB1172() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleSizeCondition(homePage.flashSaleName, productName, false, sizeName), productDetailsPage.actualRS);
    }

    @Test(priority = 19, testName = "Check flash sale on edit cart with product has many variations - incorrect size")
    public void FB1431() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 20, testName = "Check price on edit cart with product has many variations - incorrect size")
    public void FB1432() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleSizeCondition(homePage.flashSaleName, productName, false, cartQuantity, sizeName), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 21, testName = "Check flash sale on edit order checkout with product has many variations - incorrect size")
    public void FB1441() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 22, testName = "Check price on edit order checkout with product has many variations - incorrect size")
    public void FB1442() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleSizeCondition(homePage.flashSaleName, productName, false, cartQuantity, sizeName), editOrder.actualRS);
    }

    @Test(priority = 23, testName = "Check flash sale on product details with product has topping - no select topping")
    public void FB1181() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickProductFlashSale(0);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 24, testName = "Check price on product details with product has topping - no select topping")
    public void FB1182() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 25, testName = "Check flash sale on edit cart with product has topping - no select topping")
    public void FB1451() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 26, testName = "Check price on edit cart with product has topping - no select topping")
    public void FB1452() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 27, testName = "Check flash sale on edit order checkout with product has topping - no select topping")
    public void FB1461() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 28, testName = "Check price on edit order checkout with product has topping - no select topping")
    public void FB1462() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 29, testName = "Check flash sale on product details with product has topping - select topping")
    public void FB1183() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 30, testName = "Check price on product details with product has topping - select topping")
    public void FB1184() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, toppingTotalPrice, ""), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 31, testName = "Check flash sale on edit cart with product has topping - select topping")
    public void FB1453() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 32, testName = "Check price on edit cart with product has topping - select topping")
    public void FB1454() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 33, testName = "Check flash sale on edit order checkout with product has topping - select topping")
    public void FB1463() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 34, testName = "Check price on edit order checkout with product has topping - select topping")
    public void FB1464() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    @Test(priority = 35, testName = "Check flash sale on product details with product has option and topping - no select topping")
    public void FB1201() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickProductFlashSale(2);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 36, testName = "Check price on product details with product has option and topping - no select topping")
    public void FB1202() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 36, testName = "Check flash sale on edit cart with product has option and topping - no select topping")
    public void FB1491() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 36, testName = "Check price on edit cart with product has option and topping - no select topping")
    public void FB1492() {
        productName = productFlashSale.get(0);
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 36, testName = "Check flash sale on edit order checkout with product has option and topping - no select topping")
    public void FB1501() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 36, testName = "Check price on edit order checkout with product has option and topping - no select topping")
    public void FB1502() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 37, testName = "Check flash sale on product details with product has option and topping - select topping")
    public void FB1203() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 37, testName = "Check price on product details with product has option and topping - select topping")
    public void FB1204() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, toppingTotalPrice, ""), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 37, testName = "Check flash sale on edit cart with product has option and topping - select topping")
    public void FB1493() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 38, testName = "Check price on edit cart with product has option and topping - select topping")
    public void FB1494() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 39, testName = "Check flash sale on edit order checkout with product has option and topping - select topping")
    public void FB1503() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 40, testName = "Check price on edit order checkout with product has option and topping - select topping")
    public void FB1504() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    @Test(priority = 41, testName = "Check flash sale on product details with product has option and size")
    public void FB1211() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickProductFlashSale(3);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 42, testName = "Check price on product details with product has option and size")
    public void FB1212() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 43, testName = "Check flash sale on edit cart with product has has option and size")
    public void FB1511() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 44, testName = "Check price on edit cart with product has option and size")
    public void FB1512() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 45, testName = "Check flash sale on edit order checkout with product has option and size")
    public void FB1521() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 46, testName = "Check price on edit order checkout with product has option and size")
    public void FB1522() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 47, testName = "Check price on product details with product has option and size - incorrect size")
    public void FB1221() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        sizeName = "L";
        try {
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        }
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 48, testName = "Check price on product details with product has option and size - incorrect size")
    public void FB1222() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleSizeCondition(homePage.flashSaleName, productName, false, sizeName), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 49, testName = "Check flash sale on edit cart with product has option and size - incorrect size")
    public void FB1531() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 50, testName = "Check price on edit cart with product has option and size - incorrect size")
    public void FB1532() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleSizeCondition(homePage.flashSaleName, productName, false, cartQuantity, sizeName), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 51, testName = "Check flash sale on edit order checkout with product has option and size - incorrect size")
    public void FB1541() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 52, testName = "Check price on edit order checkout with product has option and size - incorrect size")
    public void FB1542() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleSizeCondition(homePage.flashSaleName, productName, false, cartQuantity, sizeName), editOrder.actualRS);
    }

    @Test(priority = 53, testName = "Check flash sale on product details with product has size and topping")
    public void FB1231() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickProductFlashSale(4);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 54, testName = "Check price on product details with product has size and topping")
    public void FB1232() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 55, testName = "Check flash sale on edit cart with product has size and topping")
    public void FB1551() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 56, testName = "Check price on edit cart with product has size and topping")
    public void FB1552() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 57, testName = "Check flash sale on edit order checkout with product has size and topping")
    public void FB1561() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 58, testName = "Check price on edit order checkout with product has size and topping")
    public void FB1562() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 59, testName = "Check flash sale on product details with product has size and topping - incorrect size select topping")
    public void FB1241() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        sizeName = "S";
        try {
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        }
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 60, testName = "Check price on product details with product has size and topping - incorrect size select topping")
    public void FB1242() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, false, toppingTotalPrice, sizeName), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 61, testName = "Check flash sale on edit cart with product has option and size - incorrect size select topping")
    public void FB1571() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 62, testName = "Check price on edit cart with product has option and size - incorrect size select topping")
    public void FB1572() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 63, testName = "Check flash sale on edit order checkout with product has option and size - incorrect size select topping")
    public void FB1581() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 64, testName = "Check price on edit order checkout with product has option and size - incorrect size select topping")
    public void FB1582() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    @Test(priority = 65, testName = "Check flash sale on product details with product has only option")
    public void FB1243() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickProductFlashSale(5);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 66, testName = "Check price on product details with product has only option")
    public void FB1244() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 67, testName = "Check flash sale on edit cart with product has only option")
    public void FB1471() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 68, testName = "Check price on edit cart with product only option")
    public void FB1472() {
        productName = productFlashSale.get(0);
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 69, testName = "Check flash sale on edit order checkout with product has only option")
    public void FB1481() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 70, testName = "Check price on edit order checkout with product has only option")
    public void FB1482() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 71, testName = "Check flash sale on product details with product has option, size and topping - no select topping")
    public void FB1245() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickProductFlashSale(6);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 72, testName = "Check price on product details with product has option, size and topping - no select topping")
    public void FB1246() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 73, testName = "Check flash sale on edit cart with product has option, size and topping - no select topping")
    public void FB1591() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 74, testName = "Check price on edit cart with product has option, size and topping - no select topping")
    public void FB1592() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 75, testName = "Check flash sale on edit order checkout with product size and topping")
    public void FB1601() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 76, testName = "Check price on edit order checkout with product has option, size and topping - no select topping")
    public void FB1602() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 77, testName = "Check flash sale on product details with product has option, size and topping - select topping")
    public void FB1247() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 78, testName = "Check price on product details with product has option, size and topping - select topping")
    public void FB1248() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, toppingTotalPrice, ""), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 79, testName = "Check flash sale on edit cart with product has option, size and topping - select topping")
    public void FB1593() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 80, testName = "Check price on edit cart with product has option, size and topping - select topping")
    public void FB1594() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 81, testName = "Check flash sale on edit order checkout with product has option, size and topping - select topping")
    public void FB1603() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 82, testName = "Check price on edit order checkout with product has option, size and topping - select topping")
    public void FB1604() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    @Test(priority = 83, testName = "Check flash sale on product details with product has option, size and topping - incorrect size select topping")
    public void FB1249() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        sizeName = "S";
        try {
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        }
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 84, testName = "Check price on product details with product has option, size and topping - incorrect size select topping")
    public void FB12491() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, false, toppingTotalPrice, sizeName), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 85, testName = "Check flash sale on edit cart with product has option, size and topping - incorrect size select topping")
    public void FB1611() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 86, testName = "Check price on edit cart with product has option, size and topping - incorrect size select topping")
    public void FB1612() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 87, testName = "Check flash sale on edit order checkout with product has option, size and topping - incorrect size select topping")
    public void FB1621() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 88, testName = "Check price on edit order checkout with product has option, size and topping - incorrect size select topping")
    public void FB162() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    @Test(priority = 89, testName = "Stop flash sale")
    public void FB37() {
        homePage.stopFlashSale(homePage.flashSaleName);
    }

    @Test(priority = 90, testName = "Check flash sale on product detail page on new tab when flash sale is out of time before Clicking complete button")
    public void FB1651() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        quantity = 10;
        maximumLimit = 2;
        cartQuantity = 1;
        int endMinus = 1;
        storePage().navigateToHomePage();
        homePage.createFlashSale(addEndedStartMinute, endMinus, false);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndedStartMinute);
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
        homePage.waitTimeChangeStatus(addEndedStartMinute + endMinus);
        Assert.assertTrue(editOrder.checkFlipCounterWhenFlashSaleEnded(), "flip zero did not display");
    }

    @Test(priority = 91, testName = "Click update button on edit order checkout")
    public void FB1652() {
        editOrder.clickUpdateBtn();
        helper.sleep(2);
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), checkoutPage.actualRS);
    }

    @Test(priority = 92, testName = "Click okay button then check the number of dialog is displaying")
    public void FB1653() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(okayFlashSaleEndedDialogIndex), checkoutPage.actualRS);
    }

    @Test(priority = 93, testName = "Check price change from flash sale price to original price on checkout")
    public void FB1654() {
        helper.sleep(1);
        Assert.assertTrue(checkoutPage.checkPriceOfProduct(false, flashSaleProduct), checkoutPage.actualRS);
    }

    @Test(priority = 94, testName = "Check display of flip countdown zero on edit cart")
    public void FB1641() {
        helper.switchBackCurrentTab(orderHandle);
        Assert.assertTrue(editOrder.checkFlipCounterWhenFlashSaleEnded(), "Flip zero did not display");
    }

    @Test(priority = 94, testName = "Click update button on edit cart to check flash sale ended notification dialog")
    public void FB1642() {
        editOrder.clickUpdateBtn();
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), checkoutPage.actualRS);
    }

    @Test(priority = 95, testName = "Click okay button then check the number of dialog is displaying")
    public void FB1643() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(okayFlashSaleEndedDialogIndex), checkoutPage.actualRS);
    }

    @Test(priority = 96, testName = "Check price change from flash sale price to original price on cart")
    public void FB1644() {
        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, false, false), homePage.actualRS);
    }

    @Test(priority = 97, testName = "Check display of flip countdown zero on product details")
    public void FB1631() {
        helper.switchBackCurrentTab(currentWindow);
        Assert.assertTrue(productDetailsPage.checkFlipCounterWhenFlashSaleEnded(), "Flip zero did not display");
    }

    @Test(priority = 97, testName = "Click add to cart button on product details")
    public void FB1632() {
        productDetailsPage.clickAddToCart();
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), checkoutPage.actualRS);
    }

    @Test(priority = 98, testName = "Click okay button then check the number of dialog is displaying")
    public void FB1633() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(okayFlashSaleEndedDialogIndex), checkoutPage.actualRS);
    }

    @Test(priority = 99, testName = "Check price change from flash sale price to original price on product details")
    public void FB1634() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, false), productDetailsPage.actualRS);
    }

    //maximum limit
    //checkout
    @Test(priority = 112, testName = "Check notification on Edit order Checkout when quantity less than maximum limit")
    public void FB178() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        quantity = 5;
        maximumLimit = 3;
        cartQuantity = 2;
        storePage().navigateToHomePage();
        homePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        productFlashSale = homePage.clickProductFlashSale(0);
        productDetailURL = helper.getCurrentURL();
        //product details
        currentWindow = helper.getCurrentWindow();
        //edit cart
        helper.openNewTab(productDetailURL);
        orderHandle = helper.getCurrentWindow();
        productDetailsPage.addProductToCartWithQuantity(cartQuantity);
        productDetailsPage.openEditCartFromCart();
        //checkout
        helper.openNewTab(productDetailURL);
        checkoutHandle = helper.getCurrentWindow();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        currentLanguage = homePage.getCurrentLanguage();
        Assert.assertFalse(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    @Test(priority = 113, testName = "Check notification on Edit order Checkout when quantity equals maximum limit")
    public void FB169() {
        editOrder.clickUpQuantityWithElement(maximumLimit);
        Assert.assertFalse(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    @Test(priority = 113, testName = "Check notification on Edit order Checkout when user increase quantity more than maximum limit")
    public void FB1721() {
        editOrder.clickUpQuantityWithElement(maximumLimit + 1);
        Assert.assertTrue(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    @Test(priority = 113, testName = "Check content of notification")
    public void FB1722() {
        currentLanguage = homePage.getCurrentLanguage();
        Assert.assertTrue(editOrder.checkContentOfMaximumLimitNotify(currentLanguage, maximumLimit), editOrder.actualRS);
    }

    @Test(priority = 113, testName = "Check notification on Edit order Checkout when user reduces quantity more than maximum limit")
    public void FB175() {
        editOrder.clickDownQuantityWithElement(maximumLimit);
        helper.sleep(1);
        Assert.assertFalse(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    //cart
    @Test(priority = 114, testName = "Check notification on Edit cart when quantity less than maximum limit")
    public void FB179() {
        helper.switchToWindowHandle(orderHandle);
        Assert.assertFalse(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    @Test(priority = 115, testName = "Check notification on Edit cart when quantity equals maximum limit")
    public void FB170() {
        editOrder.clickUpQuantityWithElement(maximumLimit);
        Assert.assertFalse(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    @Test(priority = 115, testName = "Check notification on Edit cart when user increases quantity more than maximum limit")
    public void FB1731() {
        editOrder.clickUpQuantityWithElement(maximumLimit + 1);
        Assert.assertTrue(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    @Test(priority = 115, testName = "Check content of notification")
    public void FB1732() {
        Assert.assertTrue(editOrder.checkContentOfMaximumLimitNotify(currentLanguage, maximumLimit), editOrder.actualRS);
    }

    @Test(priority = 115, testName = "Check notification on Edit cart when user reduces quantity more than maximum limit")
    public void FB176() {
        editOrder.clickDownQuantityWithElement(maximumLimit);
        helper.sleep(1);
        Assert.assertFalse(editOrder.checkDisplayOfMaximumLimitNotify(), editOrder.actualRS);
    }

    //product details
    @Test(priority = 116, testName = "Check notification on Product details when quantity less than maximum limit")
    public void FB180() {
        helper.switchToWindowHandle(currentWindow);
        Assert.assertFalse(productDetailsPage.checkDisplayOfMaximumLimitNotify(), productDetailsPage.actualRS);
    }

    @Test(priority = 117, testName = "Check notification on Product details when quantity equals maximum limit")
    public void FB171() {
        productDetailsPage.clickUpQuantityWithElement(maximumLimit);
        Assert.assertFalse(productDetailsPage.checkDisplayOfMaximumLimitNotify(), productDetailsPage.actualRS);
    }

    @Test(priority = 117, testName = "Check notification on Product details when user increases quantity more than maximum limit")
    public void FB1741() {
        productDetailsPage.clickUpQuantityWithElement(maximumLimit + 1);
        Assert.assertTrue(productDetailsPage.checkDisplayOfMaximumLimitNotify(), productDetailsPage.actualRS);
    }

    @Test(priority = 117, testName = "Check content of notification")
    public void FB1742() {
        Assert.assertTrue(productDetailsPage.checkContentOfMaximumLimitNotify(maximumLimit), productDetailsPage.actualRS);
    }

    @Test(priority = 117, testName = "Check notification on Product details when user reduces quantity more than maximum limit")
    public void FB177() {
        productDetailsPage.clickDownQuantityWithElement(maximumLimit);
        helper.sleep(1);
        Assert.assertFalse(productDetailsPage.checkDisplayOfMaximumLimitNotify(), productDetailsPage.actualRS);
    }

    @Test(priority = 117, testName = "Check Localization of the maximum limit notification on Product details")
    public void FB1878() {
        helper.refreshPage();
        Assert.assertFalse(editOrder.checkLanguageOfMaximumLimitNotify(maximumLimit), productDetailsPage.actualRS);
    }

    @Test(priority = 118, testName = "Stop flash sale")
    public void FB118() {
        homePage.stopFlashSale(homePage.flashSaleName);
        helper.closeAllOpenTabExceptMainTab(currentWindow);
    }

    //Product and not included topping
    @Test(priority = 125, testName = "Create flash sale with special variation")
    public void FB123() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        maximumLimit = 2;
        quantity = 10;
        cartQuantity = 5;
        homePage.createFlashSaleWithVariationWithoutTopping(addEndAfterStartMinute, addEndAfterEndMinute, "M", maximumLimit, quantity);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndedStartMinute);
        helper.refreshPage();
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickProductFlashSale(0);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 136, testName = "Check price on product details with product has topping - no select topping")
    public void FB2032() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 137, testName = "Check flash sale on edit cart with product has topping - no select topping")
    public void FB2101() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 138, testName = "Check price on edit cart with product has topping - no select topping")
    public void FB2102() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 139, testName = "Check flash sale on edit order checkout with product has topping - no select topping")
    public void FB2111() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 140, testName = "Check price on edit order checkout with product has topping - no select topping")
    public void FB2112() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 141, testName = "Check flash sale on product details with product has topping - select topping")
    public void FB2033() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 142, testName = "Check price on product details with product has topping - select topping")
    public void FB2034() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, toppingTotalPrice, ""), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 143, testName = "Check flash sale on edit cart with product has topping - select topping")
    public void FB2103() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 144, testName = "Check price on edit cart with product has topping - select topping")
    public void FB2104() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 145, testName = "Check flash sale on edit order checkout with product has topping - select topping")
    public void FB2113() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 146, testName = "Check price on edit order checkout with product has topping - select topping")
    public void FB2114() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    @Test(priority = 147, testName = "Check flash sale on product details with product has option and topping - no select topping")
    public void FB2051() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickProductFlashSale(2);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 148, testName = "Check price on product details with product has option and topping - no select topping")
    public void FB2052() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 149, testName = "Check flash sale on edit cart with product has option and topping - no select topping")
    public void FB2141() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 150, testName = "Check price on edit cart with product has option and topping - no select topping")
    public void FB2142() {
        productName = productFlashSale.get(0);
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 151, testName = "Check flash sale on edit order checkout with product has option and topping - no select topping")
    public void FB2151() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 152, testName = "Check price on edit order checkout with product has option and topping - no select topping")
    public void FB2152() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 153, testName = "Check flash sale on product details with product has option and topping - select topping")
    public void FB2053() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 154, testName = "Check price on product details with product has option and topping - select topping")
    public void FB2054() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, toppingTotalPrice, ""), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 155, testName = "Check flash sale on edit cart with product has option and topping - select topping")
    public void FB2153() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 156, testName = "Check price on edit cart with product has option and topping - select topping")
    public void FB2154() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 157, testName = "Check flash sale on edit order checkout with product has option and topping - select topping")
    public void FB2143() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 158, testName = "Check price on edit order checkout with product has option and topping - select topping")
    public void FB2144() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    @Test(priority = 171, testName = "Check flash sale on product details with product has size and topping")
    public void FB2061() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickProductFlashSale(4);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 172, testName = "Check price on product details with product has size and topping")
    public void FB2062() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 173, testName = "Check flash sale on edit cart with product has size and topping")
    public void FB2161() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 174, testName = "Check price on edit cart with product has size and topping")
    public void FB2162() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 175, testName = "Check flash sale on edit order checkout with product has size and topping")
    public void FB2171() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 176, testName = "Check price on edit order checkout with product has size and topping")
    public void FB2172() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 177, testName = "Check flash sale on product details with product has size and topping - incorrect size select topping")
    public void FB2071() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        sizeName = "S";
        try {
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        }
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 178, testName = "Check price on product details with product has size and topping - incorrect size select topping")
    public void FB2072() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, false, toppingTotalPrice, sizeName), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 179, testName = "Check flash sale on edit cart with product has option and size - incorrect size select topping")
    public void FB2163() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 180, testName = "Check price on edit cart with product has option and size - incorrect size select topping")
    public void FB2164() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 181, testName = "Check flash sale on edit order checkout with product has option and size - incorrect size select topping")
    public void FB2173() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 182, testName = "Check price on edit order checkout with product has option and size - incorrect size select topping")
    public void FB2174() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    @Test(priority = 189, testName = "Check flash sale on product details with product has option, size and topping - no select topping")
    public void FB2081() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickProductFlashSale(6);
        productName = productFlashSale.get(0);
        System.out.println(productName);
        productDetailURL = helper.getCurrentURL();
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 190, testName = "Check price on product details with product has option, size and topping - no select topping")
    public void FB2082() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 191, testName = "Check flash sale on edit cart with product has option, size and topping - no select topping")
    public void FB2201() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 192, testName = "Check price on edit cart with product has option, size and topping - no select topping")
    public void FB2202() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 193, testName = "Check flash sale on edit order checkout with product size and topping")
    public void FB2211() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 194, testName = "Check price on edit order checkout with product has option, size and topping - no select topping")
    public void FB2212() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleCondition(homePage.flashSaleName, productName, true, cartQuantity), editOrder.actualRS);
    }

    @Test(priority = 195, testName = "Check flash sale on product details with product has option, size and topping - select topping")
    public void FB2083() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertTrue(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 196, testName = "Check price on product details with product has option, size and topping - select topping")
    public void FB2084() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, toppingTotalPrice, ""), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 197, testName = "Check flash sale on edit cart with product has option, size and topping - select topping")
    public void FB2203() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 198, testName = "Check price on edit cart with product has option, size and topping - select topping")
    public void FB2204() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 199, testName = "Check flash sale on edit order checkout with product has option, size and topping - select topping")
    public void FB2213() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertTrue(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 200, testName = "Check price on edit order checkout with product has option, size and topping - select topping")
    public void FB2214() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, true, cartQuantity, toppingTotalPrice, ""), editOrder.actualRS);
    }

    @Test(priority = 201, testName = "Check flash sale on product details with product has option, size and topping - incorrect size select topping")
    public void FB2085() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        helper.navigateTo(productDetailURL);
        sizeName = "S";
        try {
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            productNotFlashSale = productDetailsPage.selectSizeOption(sizeName);
        }
        try {
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        } catch (Exception exception) {
            Log.error(exception.getMessage());
            helper.refreshPage();
            toppingTotalPrice = productDetailsPage.getToppingPriceFlashSaleCondition();
        }
        Assert.assertFalse(productDetailsPage.checkDisplayWhenFlashSale(), productDetailsPage.actualRS);
    }

    @Test(priority = 202, testName = "Check price on product details with product has option, size and topping - incorrect size select topping")
    public void FB2086() {
        Assert.assertTrue(productDetailsPage.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, false, toppingTotalPrice, sizeName), productDetailsPage.actualRS);
    }

    //edit order
    @Test(priority = 203, testName = "Check flash sale on edit cart with product has option, size and topping - incorrect size select topping")
    public void FB2221() {
        productDetailsPage.addToCart();
        cartQuantity = 1;
        productDetailsPage.openEditCartFromCart();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 204, testName = "Check price on edit cart with product has option, size and topping - incorrect size select topping")
    public void FB2222() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    //checkout
    @Test(priority = 205, testName = "Check flash sale on edit order checkout with product has option, size and topping - incorrect size select topping")
    public void FB2231() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutPage.openEditOrderFromCheckout();
        Assert.assertFalse(editOrder.checkDisplayWhenFlashSale(), editOrder.actualRS);
    }

    @Test(priority = 206, testName = "Check price on edit order checkout with product has option, size and topping - incorrect size select topping")
    public void FB2232() {
        Assert.assertTrue(editOrder.checkPriceFlashSaleToppingCondition(homePage.flashSaleName, productName, false, cartQuantity, toppingTotalPrice, sizeName), editOrder.actualRS);
    }

    @Test(priority = 207, testName = "Stop flash sale")
    public void FB207() {
        homePage.stopFlashSale(homePage.flashSaleName);
    }
}
