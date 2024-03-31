package com.fnb.web.store.theme1.scenario_test.home.flashsale;

import com.fnb.utils.api.admin.helpers.JsonAPIAdminReader.*;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme1.pages.home.FlashSaleDataTest;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.home.PromotionDataTest;
import com.fnb.web.store.theme1.pages.login.DataTest;
import com.fnb.web.store.theme1.pages.myOrder.MyOrderPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FlashsaleScenariosPart2 extends BaseTest {
    //Check remaining flash sale
    // Testcase: https://mediastep1-my.sharepoint.com/:x:/g/personal/ngan_cao_thi_kim_gosell_vn/EQtsv7XuR7VOuGBoZpHOKRkBCdTct0yNUZ74Q5qIB96Bsg?e=tOxSHh&nav=MTVfe0QxN0U3NDg1LTM0RjUtNDk0MC1BRDgwLTg0Rjg1NjZBRjZERn0
    private HomePage homePage;
    private CheckoutPage checkoutPage;
    private HomeDataTest homeDataTest;
    private FlashSaleDataTest flashSaleDataTest;
    private PromotionDataTest promotionDataTest;
    private DataTest loginDataTest;
    private MyOrderPage myOrderPage;
    private int addComingStartMinute = 40;
    private int addComingEndMinute = 20;
    private int addEndAfterStartMinute = 1;
    private int addEndAfterEndMinute = 90;
    private int addEndedStartMinute = 1;
    private int addEndedEndMinute = 1;
    private String productName = "";
    private String homeURL;
    private String orderURL = "";
    private String checkoutURL = "";
    private String currentWindow = "";
    private String checkoutHandle = "";
    private String cartHandle = "";
    private String orderHandle = "";
    private int quantity = 0;
    private int remainingQuantity = 0;
    private int maximumLimit = 0;
    private int cartQuantity = 0;
    private int minimumPurchaseOrder = 0;
    private List<String> productFlashSale = new ArrayList<>();
    private Helper helper;
    private String currentLanguage;
    private int okayFlashSaleEndedDialogIndex = 1;
    private int price = 0;
    private int flashSalePrice = 0;
    private FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
    private String createFlashSaleObj = "createFlashSale";
    private String createFlashSaleSpecialBranchObj = "createFlashSaleSpecialBranch";
    private String createFlashSaleMinimumPurchaseObj = "createFlashSaleMinimumPurchase";

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = storePage().navigateToHomePage();
        checkoutPage = new CheckoutPage(getDriver());
        myOrderPage = new MyOrderPage(getDriver());
        helper = storePage().helper;
    }

    //Create end after flash sale to check remaining
//    @Test(priority = 1, testName = "Create end after flash sale", description = "Limit flashsale quantity 2\nRemaining quantity 10\nCart quantity 1")
//    public void FB1() {
//        quantity = 10;
//        maximumLimit = 2;
//        remainingQuantity = quantity;
//        cartQuantity = 1;
//        storePage().navigateToHomePage();
//        homePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
//        helper.refreshPage();
//        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
//    }
//
//    @Test(priority = 1, testName = "Add to cart then checkout", description = "Add to cart")
//    public void FB2() {
//        helper.refreshPage();
//        homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, true, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, true);
//        orderURL = checkoutPage.viewOrderAfterCheckout();
//    }
//
//    @Test(priority = 2, testName = "Check remaining quantity on Flash sale campaign when remaining > limit > cart", description = "Create end after flash sale")
//    public void FB12364() {
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 3, testName = "Check remaining when user cancel order when remaining > limit > cart", description = "Cancel order FB12364")
//    public void FB12365() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//        remainingQuantity = quantity;
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), homePage.actualRS);
//    }
//
//    @Test(priority = 4, testName = "Check remaining when user reorder when remaining > limit > cart")
//    public void FB123661() {
//        orderURL = homePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 5, testName = "Cancel Order FB12366")
//    public void FB123662() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) {
//            myOrderPage.cancelOrderWithURL(orderURL);
//        }
//    }
//
//    @Test(priority = 6, testName = "Check remaining quantity on Flash sale campaign when remaining  > cart >= limit", description = "Limit flashsale quantity 2\nRemaining quantity 10\nCart quantity 3")
//    public void FB12367() {
//        quantity = 10;
//        maximumLimit = 2;
//        remainingQuantity = quantity;
//        cartQuantity = 3;
//        storePage().navigateToHomePage();
//        homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        homePage.checkoutWhenFlashSaleWithoutLogin(false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        orderURL = checkoutPage.viewOrderAfterCheckout();
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 7, testName = "Check remaining when user cancel order when remaining  > cart >= limit", description = "Cancel order FB12367")
//    public void FB12368() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//        remainingQuantity = quantity;
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), homePage.actualRS);
//    }
//
//    @Test(priority = 8, testName = "Check remaining when user reorder when remaining  > cart >= limit", description = "Cancel order FB12368")
//    public void FB123691() {
//        orderURL = homePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 9, testName = "Cancel order FB12369")
//    public void FB123692() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//    }
//
//    @Test(priority = 10, testName = "Stop flash sale then create new flash sale", description = "\"Limit flashsale quantity 15\n" +
//            "Remaining quantity 10\n" +
//            "Cart quantity 6\"")
//    public void FB58() {
//        homePage.stopFlashSale(homePage.flashSaleName);
//        maximumLimit = 15;
//        quantity = 10;
//        remainingQuantity = quantity;
//        cartQuantity = 6;
//        storePage().navigateToHomePage();
//        homePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
//        helper.refreshPage();
//        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
//    }
//
//    @Test(priority = 11, testName = "Check remaining quantity on Flash sale campaign when limit > remaining > cart", description = "Limit flashsale quantity 2\nRemaining quantity 10\nCart quantity 3")
//    public void FB12370() {
//        storePage().navigateToHomePage();
//        try {
//            homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        } catch (Exception exception) {
//            Log.info(exception.getMessage());
//            homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        }
//        homePage.checkoutWhenFlashSaleWithoutLogin(false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        orderURL = checkoutPage.viewOrderAfterCheckout();
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 12, testName = "Check remaining when user cancel order when limit > remaining > cart", description = "Cancel order FB12370")
//    public void FB12371() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//        remainingQuantity = quantity;
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), homePage.actualRS);
//    }
//
//    @Test(priority = 13, testName = "Check remaining when user reorder when limit > remaining > cart", description = "Cancel order FB12371")
//    public void FB123721() {
//        orderURL = homePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 14, testName = "Cancel order FB12372")
//    public void FB123722() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//    }
//
//    @Test(priority = 15, testName = "Check remaining quantity on Flash sale campaign when cart > limit > remaining", description = "\"Limit flashsale quantity 15\n" +
//            "Remaining quantity 10\n" +
//            "Cart quantity 20\"")
//    public void FB12373() {
//        maximumLimit = 15;
//        quantity = 10;
//        remainingQuantity = quantity;
//        cartQuantity = 20;
//        storePage().navigateToHomePage();
//        homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        homePage.checkoutWhenFlashSaleWithoutLogin(false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        orderURL = checkoutPage.viewOrderAfterCheckout();
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 16, testName = "Check remaining when user cancel order when cart > limit > remaining ", description = "Cancel order FB12373")
//    public void FB12374() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//        remainingQuantity = quantity;
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), homePage.actualRS);
//    }
//
//    @Test(priority = 17, testName = "Check remaining when user reorder when cart > limit > remaining ", description = "Cancel order FB12374")
//    public void FB123751() {
//        orderURL = homePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 18, testName = "Cancel order FB12375")
//    public void FB123752() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//    }
//
//    @Test(priority = 19, testName = "Check remaining quantity on Flash sale campaign when limit >= cart >= remaining", description = "\"Limit flashsale quantity 15\n" +
//            "Remaining quantity 10\n" +
//            "Cart quantity 10\"")
//    public void FB12376() {
//        maximumLimit = 15;
//        quantity = 10;
//        remainingQuantity = quantity;
//        cartQuantity = 10;
//        storePage().navigateToHomePage();
//        homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        homePage.checkoutWhenFlashSaleWithoutLogin(false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        orderURL = checkoutPage.viewOrderAfterCheckout();
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 20, testName = "Check remaining when user cancel order when limit > remaining > cart", description = "Cancel order FB12376")
//    public void FB12377() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//        remainingQuantity = quantity;
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), homePage.actualRS);
//    }
//
//    @Test(priority = 21, testName = "Check remaining when user reorder when limit > remaining > cart", description = "Cancel order FB12377")
//    public void FB123781() {
//        orderURL = homePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 22, testName = "Cancel order FB12378")
//    public void FB123782() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//    }
//
//    @Test(priority = 23, testName = "Stop flash sale then create new flash sale", description = "\"Limit flashsale quantity 10\n" +
//            "Remaining quantity 10\n" +
//            "Cart quantity 5\"")
//    public void FB62() {
//        homePage.stopFlashSale(homePage.flashSaleName);
//        maximumLimit = 10;
//        quantity = 10;
//        remainingQuantity = quantity;
//        cartQuantity = 5;
//        storePage().navigateToHomePage();
//        homePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
//        helper.refreshPage();
//        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
//    }
//
//    @Test(priority = 24, testName = "Check remaining quantity on Flash sale campaign when limit = remaining and limit > cart", description = "\"Limit flashsale quantity 10\n" +
//            "Remaining quantity 10\n" +
//            "Cart quantity 5\"")
//    public void FB12379() {
//        storePage().navigateToHomePage();
//        homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        homePage.checkoutWhenFlashSaleWithoutLogin(false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        orderURL = checkoutPage.viewOrderAfterCheckout();
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 25, testName = "Check remaining when user cancel order when limit = remaining and limit > cart", description = "Cancel order FB12376")
//    public void FB12380() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//        remainingQuantity = quantity;
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), homePage.actualRS);
//    }
//
//    @Test(priority = 26, testName = "Check remaining when user reorder when limit = remaining and limit > cart", description = "Cancel order FB12377")
//    public void FB123811() {
//        orderURL = homePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 27, testName = "Cancel order FB12381")
//    public void FB123812() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//    }
//
//    @Test(priority = 28, testName = "Check remaining quantity on Flash sale campaign when limit >= cart >= remaining", description = "\"Limit flashsale quantity 10\n" +
//            "Remaining quantity 10\n" +
//            "Cart quantity 11\"")
//    public void FB12382() {
//        maximumLimit = 10;
//        quantity = 10;
//        remainingQuantity = quantity;
//        cartQuantity = 11;
//        storePage().navigateToHomePage();
//        homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        homePage.checkoutWhenFlashSaleWithoutLogin(false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        orderURL = checkoutPage.viewOrderAfterCheckout();
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 29, testName = "Check remaining when user cancel order when limit > remaining > cart", description = "Cancel order FB12382")
//    public void FB12383() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//        remainingQuantity = quantity;
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), homePage.actualRS);
//    }
//
//    @Test(priority = 30, testName = "Check remaining when user reorder when limit > remaining > cart", description = "Cancel order FB12383")
//    public void FB123841() {
//        orderURL = homePage.checkRemainingFlashSaleAfterReorder(orderURL, cartQuantity, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 31, testName = "Cancel order FB12384")
//    public void FB123842() {
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//    }
//
//    @Test(priority = 32, testName = "Stop flash sale then create new flash sale", description = "\"1. Limit flashsale quantity 10. Remaining quantity 200. Cart quantity 5\n" +
//            "2. Limit flashsale quantity 10. Remaining quantity 15. Cart quantity 7\n" +
//            "3. Limit flashsale quantity 10. Remaining quantity 8. Cart quantity 8\"")
//    public void FB32() {
//        homePage.stopFlashSale(homePage.flashSaleName);
//        maximumLimit = 10;
//        quantity = 20;
//        remainingQuantity = quantity;
//        cartQuantity = 5;
//        storePage().navigateToHomePage();
//        homePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
//        helper.refreshPage();
//        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
//    }
//
//    @Test(priority = 33, testName = "Check remaining quantity when user continues to create order", description = "Limit flashsale quantity 10\nRemaining quantity 20\nCart quantity 5")
//    public void FB123851() {
//        storePage().navigateToHomePage();
//        productName = homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        homePage.checkoutWhenFlashSaleWithoutLogin(false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        orderURL = checkoutPage.viewOrderAfterCheckout();
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//        remainingQuantity = quantity - cartQuantity;
//    }
//
//    @Test(priority = 34, testName = "Check remaining quantity when user cancel order on new tab without reload")
//    public void FB123852() {
//        currentWindow = helper.getCurrentWindow();
//        helper.openNewTab(orderURL);
//        helper.waitForURLContains(orderURL);
//        orderHandle = helper.getCurrentWindow();
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//        helper.switchToWindowHandle(currentWindow);
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 35, testName = "Check remaining quantity when user cancel order on new tab after reload")
//    public void FB123853() {
//        helper.refreshPage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, 0), homePage.actualRS);
//        System.out.println(remainingQuantity);
//    }
//
//    @Test(priority = 35, testName = "Add to cart 1")
//    public void FB123854() {
//        cartQuantity = 6;
//        storePage().navigateToHomePage();
//        productName = homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        homePage.checkoutWhenFlashSaleWithoutLogin(false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        orderURL = checkoutPage.viewOrderAfterCheckout();
//        storePage().navigateToHomePage();
//        System.out.println(remainingQuantity);
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(quantity, maximumLimit, cartQuantity), homePage.actualRS);
//        remainingQuantity = quantity - cartQuantity;
//        System.out.println("remaining: " + remainingQuantity); //14
//    }
//
//    @Test(priority = 36, testName = "Add to cart 2")
//    public void FB123855() {
//        cartQuantity = 6;
//        storePage().navigateToHomePage();
//        productName = homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        homePage.checkoutWhenFlashSaleWithoutLogin(false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        orderURL = checkoutPage.viewOrderAfterCheckout();
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(remainingQuantity, maximumLimit, cartQuantity), homePage.actualRS);
//        remainingQuantity = remainingQuantity - cartQuantity; //8
//        System.out.println("remaining: " + remainingQuantity);
//    }
//
//    @Test(priority = 37, testName = "Add to cart 3")
//    public void FB123856() {
//        cartQuantity = 8;
//        storePage().navigateToHomePage();
//        productName = homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        homePage.checkoutWhenFlashSaleWithoutLogin(false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        orderURL = checkoutPage.viewOrderAfterCheckout();
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(remainingQuantity, maximumLimit, cartQuantity), homePage.actualRS); //sold out
//        System.out.println(remainingQuantity);
//    }
//
//    @Test(priority = 38, testName = "Check remaining quantity - sold out when user cancel order on new tab without reload", description = "Open checkout page on new tab")
//    public void FB123857() {
//        currentWindow = helper.getCurrentWindow();
//        helper.openNewTab(orderURL);
//        helper.waitForURLContains(orderURL);
//        orderHandle = helper.getCurrentWindow();
//        while (!myOrderPage.cancelOrderWithURL(orderURL)) myOrderPage.cancelOrderWithURL(orderURL);
//        helper.switchToWindowHandle(currentWindow);
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(remainingQuantity, maximumLimit, cartQuantity), homePage.actualRS);
//        System.out.println(remainingQuantity);
//    }
//
//    @Test(priority = 39, testName = "Check remaining quantity - sold out when user cancle order on new tab after reloading")
//    public void FB123858() {
//        helper.refreshPage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(remainingQuantity, maximumLimit, 0), homePage.actualRS);
//    }
//
//    @Test(priority = 40, testName = "Add to cart 4")
//    public void FB123859() {
//        cartQuantity = 8;
//        storePage().navigateToHomePage();
//        productName = homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        homePage.checkoutWhenFlashSaleWithoutLogin(false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
//        orderURL = checkoutPage.viewOrderAfterCheckout();
//        storePage().navigateToHomePage();
//        Assert.assertTrue(homePage.checkRemainingQuantityFlashSale(0, maximumLimit, cartQuantity), homePage.actualRS);
//    }
//
//    @Test(priority = 41, testName = "Check remaining quantity when user continues to create order")
//    public void FB1238510() {
//        cartQuantity = 10;
//        storePage().navigateToHomePage();
//        productName = homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
//        storePage().navigateToHomePage();
//        Assert.assertFalse(homePage.checkCartWhenFlashSale(productName, true), homePage.actualRS);
//        helper.refreshPage();
//        homePage.checkoutThenClearCartWithoutLogin();
//    }
//
//    @Test(priority = 42, testName = "Clear all data and browser tab")
//    public void FB42() {
//        helper.refreshPage();
//        helper.closeAllOpenTabExceptMainTab(currentWindow);
//        homePage.stopFlashSale(homePage.flashSaleName);
//    }
//
//    @Test(priority = 43, testName = "Check flash sale on cart when flash sale is coming")
//    public void FB12562() {
//        storePage().navigateToHomePage();
//        maximumLimit = 2;
//        quantity = 10;
//        cartQuantity = 5;
//        String homeURL = helper.getCurrentURL();
//        currentWindow = helper.getCurrentWindow();
//        homePage.createFlashSaleNotFullVariations(addComingStartMinute, addComingEndMinute);
//        helper.refreshPage();
//        productFlashSale = homePage.clickAddToCartFlashSaleWithQuantityList(0, 1);
//        productName = productFlashSale.get(0);
//        storePage().navigateToHomePage();
//        helper.openNewTab(homeURL);
//        cartHandle = helper.getCurrentWindow();
//        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productName);
//        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, false, true), homePage.actualRS);
//    }
//
//    @Test(priority = 44, testName = "Check flash sale on cart when flash sale is end after before reload", description = "Update coming flash sale to end after")
//    public void FB12563() {
//        helper.switchToWindowHandle(currentWindow);
//        homePage.updateTimeFlashSale(homePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
//        helper.refreshPage();
//        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
//        helper.refreshPage();
//        helper.switchToWindowHandle(cartHandle);
//        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, false, true), homePage.actualRS);
//    }
//
//    @Test(priority = 45, testName = "Check flash sale on cart when flash sale is end after after reload/close then click again")
//    public void FB12564() {
//        homePage.clickAccountIcon();
//        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, true, true), homePage.actualRS);
//    }
//
//    @Test(priority = 46, testName = "Check flash sale on cart on new tab when flash sale is out of time before reload")
//    public void FB12565() {
//        helper.switchToWindowHandle(currentWindow);
//        homePage.updateTimeFlashSale(homePage.flashSaleName, addEndedStartMinute, addEndedEndMinute);
//        helper.refreshPage();
//        homePage.waitTimeChangeStatus(addEndedStartMinute + addEndedEndMinute);
//        helper.refreshPage();
//        helper.switchToWindowHandle(cartHandle);
//        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, true, true), homePage.actualRS);
//    }
//
//    @Test(priority = 47, testName = "Check flash sale on cart on new tab when flash sale is out of time after reload")
//    public void FB12566() {
//        helper.refreshPage();
//        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, false, true), homePage.actualRS);
//        helper.refreshPage();
//        homePage.checkoutThenClearCartWithoutLogin();
//    }

    @Test(priority = 48, testName = "Check flash sale on cart with product has many variations then add to cart except flash sale size", description = "Add to cart size L")
    public void FB12568() {
//        homePage.stopFlashSale(homePage.flashSaleName);
        storePage().navigateToHomePage();
        maximumLimit = 2;
        quantity = 10;
        cartQuantity = 5;
//        homePage.createFlashSaleWithVariation(addEndAfterStartMinute, addEndAfterEndMinute, "M", maximumLimit, quantity);
//        helper.refreshPage();
//        homePage.waitTimeChangeStatus(addEndedStartMinute);
        helper.refreshPage();
        try {
            productFlashSale = homePage.clickAddToCartFlashSaleSelectSize("L", 1, 1);
        } catch (Exception exception) {
            storePage().navigateToHomePage();
            Log.info(exception.getMessage());
            productFlashSale = homePage.clickAddToCartFlashSaleSelectSize("L", 1, 1);
        }
        System.out.println(productFlashSale);
        productName = productFlashSale.get(0);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productName);
        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, false, true), homePage.actualRS);
    }

    @Test(priority = 49, testName = "Check flash sale on checkout with product has many variations")
    public void FB49() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, false, true, false, false), checkoutPage.actualRS);
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
    }

    @Test(priority = 50, testName = "Check flash sale on cart with product has many variations")
    public void FB12569() {
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickAddToCartFlashSaleWithQuantityList(1, 1);
        System.out.println(productFlashSale);
        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, true, true), homePage.actualRS);
    }

    @Test(priority = 51, testName = "Check flash sale on checkout with product has many variations - correct size")
    public void FB51() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, true, true, false, false), checkoutPage.actualRS);
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
    }

    @Test(priority = 52, testName = "Check flash sale on cart with product has topping")
    public void FB12570() {
        storePage().navigateToHomePage();
        try {
            productFlashSale = homePage.clickAddToCartFlashSaleSelectTopping(0, 1);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = homePage.clickAddToCartFlashSaleSelectTopping(0, 1);
        }
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, true, true), homePage.actualRS);
    }

    @Test(priority = 53, testName = "Check flash sale on checkout with product has only topping")
    public void FB53() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, true, true, false, false), checkoutPage.actualRS);
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
    }

    @Test(priority = 54, testName = "Check flash sale on cart with product has option and topping")
    public void FB12572() {
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickAddToCartFlashSaleWithQuantityList(2, 1);
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, true, true), homePage.actualRS);
    }

    @Test(priority = 55, testName = "Check flash sale on checkout with product has option and topping")
    public void FB55() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, true, true, false, false), checkoutPage.actualRS);
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
    }

    @Test(priority = 56, testName = "Check flash sale on cart with product has option and size then add to cart except flash sale size")
    public void FB12573() {
        storePage().navigateToHomePage();
        try {
            productFlashSale = homePage.clickAddToCartFlashSaleSelectSize("L", 3, 1);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = homePage.clickAddToCartFlashSaleSelectSize("L", 3, 1);
        }
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        System.out.println(productFlashSale);
        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, false, true), homePage.actualRS);
    }

    @Test(priority = 57, testName = "Check flash sale on checkout with product has size and option - incorrect size")
    public void FB57() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, false, true, false, false), checkoutPage.actualRS);
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
    }

    @Test(priority = 58, testName = "Check flash sale on cart with product has option and size")
    public void FB12574() {
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickAddToCartFlashSaleWithQuantityList(3, 1);
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, true, true), homePage.actualRS);
    }

    @Test(priority = 59, testName = "Check flash sale on checkout with product has size and option - correct size")
    public void FB59() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, true, true, false, false), checkoutPage.actualRS);
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
    }

    @Test(priority = 60, testName = "Check flash sale on cart with product has size and topping then add to cart except flash sale size")
    public void FB12575() {
        storePage().navigateToHomePage();
        try {
            productFlashSale = homePage.clickAddToCartFlashSaleSelectSizeAndTopping("S", 4, 1);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = homePage.clickAddToCartFlashSaleSelectSizeAndTopping("S", 4, 1);
        }
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, false, true), homePage.actualRS);
    }

    @Test(priority = 61, testName = "Check flash sale on checkout with product has topping and size - incorrect size")
    public void FB61() {
        storePage().navigateToHomePage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, false, true, false, false), checkoutPage.actualRS);
        helper.refreshPage();
        homePage.checkoutThenClearCartLogin(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
    }

    @Test(priority = 62, testName = "Check flash sale on cart with product has size and topping")
    public void FB12576() {
        storePage().navigateToHomePage();
        try {
            productFlashSale = homePage.clickAddToCartFlashSaleSelectTopping(4, 1);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = homePage.clickAddToCartFlashSaleSelectTopping(4, 1);
        }
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, true, true), homePage.actualRS);
    }

    @Test(priority = 63, testName = "Check flash sale on checkout with product has size and topping - correct size")
    public void FB63() {
        storePage().navigateToHomePage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, true, true, false, false), checkoutPage.actualRS);
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
    }

    @Test(priority = 64, testName = "Check flash sale on cart with product has option")
    public void FB12571() {
        storePage().navigateToHomePage();
        productFlashSale = homePage.clickAddToCartFlashSaleSelectTopping(5, 1);
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, true, true), homePage.actualRS);
    }

    @Test(priority = 65, testName = "Check flash sale on checkout with product has only option")
    public void FB65() {
        storePage().navigateToHomePage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, true, true, false, false), checkoutPage.actualRS);
        helper.refreshPage();
        try {
            homePage.checkoutThenClearCartWithoutLogin();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            homePage.checkoutThenClearCartWithoutLogin();
        }
    }

    @Test(priority = 66, testName = "Check flash sale on cart with product has option and size")
    public void FB12577() {
        storePage().navigateToHomePage();
        try {
            productFlashSale = homePage.clickAddToCartFlashSaleSelectSizeAndTopping("S", 6, 1);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = homePage.clickAddToCartFlashSaleSelectSizeAndTopping("S", 6, 1);
        }
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, false, true), homePage.actualRS);
    }

    @Test(priority = 67, testName = "Check flash sale on checkout with product has topping, option and size - incorrect size")
    public void FB67() {
        storePage().navigateToHomePage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, false, true, false, false), checkoutPage.actualRS);
        helper.refreshPage();
        try {
            homePage.checkoutThenClearCartWithoutLogin();
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            homePage.checkoutThenClearCartWithoutLogin();
        }
    }

    @Test(priority = 68, testName = "Check flash sale on cart with product has option, size and topping")
    public void FB12578() {
        storePage().navigateToHomePage();
        try {
            productFlashSale = homePage.clickAddToCartFlashSaleSelectTopping(6, 1);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = homePage.clickAddToCartFlashSaleSelectTopping(6, 1);
        }
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        Assert.assertTrue(homePage.checkCartPriceWhenFlasSale(flashSaleProduct, true, true), homePage.actualRS);
    }

    @Test(priority = 69, testName = "Check flash sale on checkout with product has topping, option and size - correct size")
    public void FB69() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutPriceWhenFlashSale(flashSaleProduct, true, true, false, false), checkoutPage.actualRS);
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
    }

    @Test(priority = 70, testName = "Check flash sale on cart when users add to cart more than maximum limit, limit <= remaining quantity")
    public void FB12579() {
        storePage().navigateToHomePage();
        try {
            productFlashSale = homePage.clickAddToCartFlashSaleWithQuantityList(1, cartQuantity);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = homePage.clickAddToCartFlashSaleWithQuantityList(1, cartQuantity);
        }
        System.out.println(cartQuantity);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        Assert.assertTrue(homePage.checkCartQuantityWhenFlashSale(flashSaleProduct, cartQuantity, maximumLimit, true), homePage.actualRS);
    }

    @Test(priority = 71, testName = "Add to checkout more than limited flash sale quantity (remaining quantity >= limit", description = "\tRemaining: 10\n" +
            "\tLimit: 2\n" +
            "\tAdd to cart: 5")
    public void FB71() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutQuantityWhenFlashSale(flashSaleProduct, cartQuantity, maximumLimit, false, true, false), checkoutPage.actualRS);
    }

    @Test(priority = 72, testName = "Clear product in cart then stop flash sale")
    public void FB72() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        homePage.stopFlashSale(homePage.flashSaleName);
    }

    @Test(priority = 73, testName = "Check flash sale on cart when users add to cart more than maximum limit, limit >= remaining quantity")
    public void FB12580() {
        maximumLimit = 10;
        quantity = 10;
        cartQuantity = 8;
        storePage().navigateToHomePage();
        homePage.createFlashSaleWithVariation(addEndAfterStartMinute, addEndAfterEndMinute, "M", maximumLimit, quantity);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndedStartMinute);
        storePage().navigateToHomePage();
        productName = homePage.clickAddToCartFromFlashSaleWithQuantity(1, cartQuantity);
        homePage.checkoutWhenFlashSaleWithoutLogin(false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
        orderURL = checkoutPage.viewOrderAfterCheckout();
        storePage().navigateToHomePage();
        System.out.println(homePage.checkRemainingQuantityFlashSaleIndex(1, quantity, maximumLimit, cartQuantity));
        remainingQuantity = quantity - cartQuantity;
        cartQuantity = 5;
        storePage().navigateToHomePage();
        try {
            productFlashSale = homePage.clickAddToCartFlashSaleWithQuantityList(1, cartQuantity);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            storePage().navigateToHomePage();
            productFlashSale = homePage.clickAddToCartFlashSaleWithQuantityList(1, cartQuantity);
        }
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        Assert.assertTrue(homePage.checkCartQuantityWhenFlashSale(flashSaleProduct, cartQuantity, remainingQuantity, true), homePage.actualRS);
    }

    @Test(priority = 74, testName = "Clear product in cart then stop flash sale")
    public void FB74() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkCheckoutQuantityWhenFlashSale(flashSaleProduct, cartQuantity, maximumLimit, false, true, false), checkoutPage.actualRS);
    }

    @Test(priority = 75, testName = "Clear all cart")
    public void FB75() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        homePage.stopFlashSale(homePage.flashSaleName);
        helper.closeAllOpenTabExceptMainTab(currentWindow);
    }

    @Test(priority = 93, testName = "Check flash sale on checkout page on new tab when flash sale is out of time before Clicking complete button")
    public void FB93() {
        quantity = 10;
        maximumLimit = 2;
        remainingQuantity = quantity;
        cartQuantity = 1;
        storePage().navigateToHomePage();
        homePage.createFlashSale(addEndedStartMinute, 1, false);
        helper.refreshPage();
        homeURL = helper.getCurrentURL();
        currentWindow = helper.getCurrentWindow();
        helper.openNewTab(homeURL);
        productName = homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndedStartMinute);
        helper.refreshPage();
        currentLanguage = homePage.getCurrentLanguage();
        productFlashSale = checkoutPage.getProductFlashSaleInformation(productName);
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        homePage.waitTimeChangeStatus(addEndedStartMinute + 1);
    }

    @Test(priority = 94, testName = "Click complete and check content dialog")
    public void FB94() {
        checkoutPage.clickCompleteBtn(homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
        helper.sleep(2);
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), checkoutPage.actualRS);
    }

    @Test(priority = 95, testName = "Click okay button then check the number of dialog is displaying")
    public void FB95() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(okayFlashSaleEndedDialogIndex), checkoutPage.actualRS);
    }

    @Test(priority = 96, testName = "Check price change from flash sale price to original price")
    public void FB96() {
        Assert.assertFalse(checkoutPage.checkPriceOfProduct(false, flashSaleProduct), checkoutPage.actualRS);
    }

    @Test(priority = 97, testName = "Check flash sale on checkout page on new tab when flash sale has stopped by admin before Clicking complete button")
    public void FB97() {
        helper.closeAllOpenTabExceptMainTab(currentWindow);
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        cartQuantity = 1;
        storePage().navigateToHomePage();
        homePage.createFlashSale(addEndAfterStartMinute, addEndAfterEndMinute, false);
        helper.refreshPage();
        homeURL = helper.getCurrentURL();
        currentWindow = helper.getCurrentWindow();
        helper.openNewTab(homeURL);
        productName = homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
        checkoutHandle = helper.getCurrentWindow();
        checkoutURL = helper.getCurrentUrl();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        currentLanguage = homePage.getCurrentLanguage();
//        checkoutPage.changeAddress(homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
        productFlashSale = checkoutPage.getProductFlashSaleInformation(productName);
        System.out.println("FB97 " + productFlashSale.size());
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        homePage.stopFlashSale(homePage.flashSaleName);
    }

    @Test(priority = 98, testName = "Check content of dialog")
    public void FB98() {
        checkoutPage.clickCompleteBtn(homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), checkoutPage.actualRS);
    }

    @Test(priority = 99, testName = "Click okay button then check the number of dialog is displaying")
    public void FB99() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(okayFlashSaleEndedDialogIndex), checkoutPage.actualRS);
    }

    @Test(priority = 100, testName = "Check price change from flash sale price to original price")
    public void FB1000() {
        Assert.assertFalse(checkoutPage.checkPriceOfProduct(false, flashSaleProduct), checkoutPage.actualRS);
    }

    @Test(priority = 100, testName = "Check flash sale on checkout page on new tab when flash sale is out of time before Clicking complete button")
    public void FB1001() {
        cartQuantity = 1;
        homePage.createFlashSale(addEndAfterStartMinute, addEndAfterEndMinute, false);
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        currentLanguage = homePage.getCurrentLanguage();
        helper.refreshPage();
        productFlashSale = checkoutPage.getProductFlashSaleInformation(productName);
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
    }

    @Test(priority = 100, testName = "Stop flash sale")
    public void FB1002() {
        homePage.stopFlashSale(homePage.flashSaleName);
    }

    @Test(priority = 101, testName = "Check content of dialog")
    public void FB101() {
        helper.refreshPage();
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage), checkoutPage.actualRS);
    }

    @Test(priority = 102, testName = "Click okay button then check the number of dialog is displaying")
    public void FB102() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(okayFlashSaleEndedDialogIndex), checkoutPage.actualRS);
    }

    @Test(priority = 103, testName = "Check price change from flash sale price to original price")
    public void FB103() {
        Assert.assertFalse(checkoutPage.checkPriceOfProduct(false, flashSaleProduct), checkoutPage.actualRS);
    }

    @Test(priority = 104, testName = "Close all tabs")
    public void FB104() {
        helper.closeAllOpenTabExceptMainTab(currentWindow);
    }

    //maximum limit
    @Test(priority = 105, testName = "Check checkout page when user increase quantity more than maximum limit")
    public void FB105() {
        quantity = 5;
        maximumLimit = 3;
        cartQuantity = 3;
        storePage().navigateToHomePage();
        homePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        productFlashSale = homePage.clickAddToCartFlashSaleWithQuantityList(0, cartQuantity);
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        currentLanguage = homePage.getCurrentLanguage();
        checkoutPage.upQuantityWithElementProductFlashSale(1, maximumLimit, cartQuantity);
    }

    @Test(priority = 106, testName = "Check content of dialog")
    public void FB106() {
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage, promotionDataTest.PAGE, checkoutPage.getOverLimitedKeyList()), checkoutPage.actualRS);
    }

    @Test(priority = 107, testName = "Click okay button then check the number of dialog is displaying")
    public void FB107() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(okayFlashSaleEndedDialogIndex), checkoutPage.actualRS);
    }

    @Test(priority = 108, testName = "Check price change from flash sale price to original price")
    public void FB108() {
        Assert.assertTrue(checkoutPage.checkCheckoutQuantityWhenFlashSale(flashSaleProduct, cartQuantity + 1, maximumLimit, false, false, false), checkoutPage.actualRS);
    }

    @Test(priority = 110, testName = "Stop flash sale")
    public void FB110() {
        checkoutPage.clearAllCart();
        homePage.stopFlashSale(homePage.flashSaleName);
    }

    @Test(priority = 111, testName = "Check checkout page when user increase quantity more than remaining quantity")
    public void FB111() {
        quantity = 5;
        maximumLimit = 5;
        cartQuantity = 5;
        storePage().navigateToHomePage();
        homePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        productFlashSale = homePage.clickAddToCartFlashSaleWithQuantityList(0, cartQuantity);
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        currentLanguage = homePage.getCurrentLanguage();
        checkoutPage.upQuantityWithElementProductFlashSale(1, maximumLimit, cartQuantity);
        helper.sleep(1);
    }

    @Test(priority = 112, testName = "Check content of dialog")
    public void FB112() {
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage, promotionDataTest.PAGE, checkoutPage.getOverLimitedKeyList()), checkoutPage.actualRS);
    }

    @Test(priority = 113, testName = "Click okay button then check the number of dialog is displaying")
    public void FB113() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(okayFlashSaleEndedDialogIndex), checkoutPage.actualRS);
    }

    @Test(priority = 114, testName = "Check price change from flash sale price to original price")
    public void FB114() {
        Assert.assertTrue(checkoutPage.checkCheckoutQuantityWhenFlashSale(flashSaleProduct, cartQuantity + 1, maximumLimit, false, false, false), checkoutPage.actualRS);
    }

    @Test(priority = 115, testName = "Stop flash sale")
    public void FB115() {
        homePage.stopFlashSale(homePage.flashSaleName);
    }

    @Test(priority = 116, testName = "Check flash sale price when user click complete btn at flash sale 1 ended and flash sale 2 start, both of flash sale have the same product.")
    public void FB116() {
        helper.refreshPage();
        homePage.checkoutThenClearCartWithoutLogin();
        helper.refreshPage();
        quantity = 20;
        maximumLimit = 15;
        cartQuantity = 1;
        minimumPurchaseOrder = 1000000;
        storePage().navigateToHomePage();
        homePage.createFlashSaleWithMinimumPurchaseOrder(addEndAfterStartMinute, addEndAfterEndMinute, maximumLimit, quantity, minimumPurchaseOrder);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        productFlashSale = homePage.clickAddToCartFlashSaleWithQuantityList(1, cartQuantity);
        productName = productFlashSale.get(0);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        System.out.println(productFlashSale);
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        Assert.assertTrue(checkoutPage.checkQuantity(false, cartQuantity), checkoutPage.actualRS);
    }

    @Test(priority = 117, testName = "Check total amount is bigger than minimum purchase order")
    public void FB117() {
        price = helper.convertToInteger(productFlashSale.get(1));
        flashSalePrice = helper.convertToInteger(productFlashSale.get(2));
        Assert.assertTrue(checkoutPage.checkMaximumPurchase(true, minimumPurchaseOrder, maximumLimit, cartQuantity, price, flashSalePrice), checkoutPage.actualRS);
    }

    @Test(priority = 118, testName = "Check total amount is less than minimum purchase order", description = "Decrease the number of product till total amount is less more than minimum purchase order")
    public void FB118() {
        Assert.assertTrue(checkoutPage.decreaseFlashSaleProductQuantity(), checkoutPage.actualRS);
    }

    @Test(priority = 119, testName = "Check content of minimum purchase notification dialog")
    public void FB119() {
        cartQuantity = checkoutPage.getCartQuantity() - 1;
        currentLanguage = homePage.getCurrentLanguage();
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage, promotionDataTest.PAGE, checkoutPage.getMinimumPurchaseKeyList()), checkoutPage.actualRS);
    }

    @Test(priority = 120, testName = "Click okay button")
    public void FB120() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(okayFlashSaleEndedDialogIndex), checkoutPage.actualRS);
    }

    @Test(priority = 121, testName = "Check price change from flash sale price to original price")
    public void FB121() {
        Assert.assertTrue(checkoutPage.checkPriceOfProduct(false, flashSaleProduct), checkoutPage.actualRS);
    }

    @Test(priority = 122, testName = "Create order")
    public void FB122() {
        checkoutPage.checkMaximumPurchase(false, minimumPurchaseOrder, maximumLimit, cartQuantity, price, flashSalePrice);
        checkoutPage.checkoutAction(currentLanguage, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
        Assert.assertTrue(checkoutPage.checkDisplayOfViewOrder(), "View order did not display");
    }

    @Test(priority = 123, testName = "View order and check subtotal")
    public void FB123() {
        checkoutPage.viewOrderAfterCheckout();
        Assert.assertTrue(myOrderPage.checkValueOfSubTotal(helper.formatCurrencyToThousand(checkoutPage.gettotalOriginalPrice())), myOrderPage.actualRS);
    }

    @Test(priority = 124, testName = "Stop flash sale")
    public void FB124() {
        homePage.stopFlashSale(homePage.flashSaleName);
    }

    @Test(priority = 125, testName = "Check flash sale price when user click complete btn at flash sale 1 ended and flash sale 2 start, both of flash sale have the same product.")
    public void FB125() {
        quantity = 20;
        maximumLimit = 15;
        cartQuantity = 3;
        int addMinus = 1;
        storePage().navigateToHomePage();
        homePage.createFlashSaleWithQuantity(addEndAfterStartMinute, addMinus, maximumLimit, quantity);
        String flashSaleKey = homePage.flashSaleName;
        homePage.createFlashSaleWithQuantity(addEndAfterStartMinute + addMinus, addMinus, maximumLimit, quantity);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        productName = homePage.clickAddToCartFromFlashSaleWithQuantity(0, cartQuantity);
        productFlashSale = homePage.getFlashsSaleProductInformation(flashSaleKey, productName);
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, false, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        helper.refreshPage();
//        checkoutPage.changeAddress(homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
        System.out.println(checkoutPage.checkPriceOfProduct(true, flashSaleProduct));
        productFlashSale = homePage.getFlashsSaleProductInformation(homePage.flashSaleName, productName);
        System.out.println(productFlashSale);
        flashSaleProduct = homePage.getFlashSaleProductByName(createFlashSaleObj, productFlashSale.get(0));
        homePage.waitTimeChangeStatus(addEndAfterStartMinute + addMinus);
        checkoutPage.clickCompleteBtn(homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
    }

    @Test(priority = 126, testName = "Check content of minimum purchase notification dialog")
    public void FB126() {
        currentLanguage = homePage.getCurrentLanguage();
        Assert.assertTrue(checkoutPage.checkContentDialog(currentLanguage, promotionDataTest.PAGE, checkoutPage.getHasBeenChangedKeyList()), checkoutPage.actualRS);
    }

    @Test(priority = 127, testName = "Click okay button on dialog")
    public void FB127() {
        Assert.assertTrue(checkoutPage.clickOkayBtn(okayFlashSaleEndedDialogIndex), checkoutPage.actualRS);
    }

    @Test(priority = 128, testName = "Check price change from flash sale price to original price")
    public void FB128() {
        Assert.assertTrue(checkoutPage.checkPriceOfProduct(true, flashSaleProduct), checkoutPage.actualRS);
    }

    @Test(priority = 129, testName = "Stop flash sale")
    public void FB130() {
        homePage.stopFlashSale(homePage.flashSaleName);
    }
}
