package com.fnb.web.store.theme1.scenario_test.home.flashsale;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.product_list.ProductListPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlashsaleScenariosPart4 extends BaseTest {
    //Check on product list
    // Testcase:https://mediastep1-my.sharepoint.com/:x:/g/personal/ngan_cao_thi_kim_gosell_vn/EQtsv7XuR7VOuGBoZpHOKRkBCdTct0yNUZ74Q5qIB96Bsg?e=CUOyYp&nav=MTVfe0QxN0U3NDg1LTM0RjUtNDk0MC1BRDgwLTg0Rjg1NjZBRjZERn0
    private HomePage homePage;
    private ProductListPage productListPage;
    private Helper helper;
    private int addComingStartMinute = 40;
    private int addComingEndMinute = 20;
    private int addEndAfterStartMinute = 1;
    private int addEndAfterEndMinute = 90;
    private int addEndedStartMinute = 1;
    private int addEndedEndMinute = 1;
    private String currentWindow = "";
    private String productListHandle = "";
    private int quantity = 0;
    private int maximumLimit = 0;

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = new HomePage(getDriver());
        productListPage = storePage().navigateToProductListTheme1();
        helper = storePage().helper;
    }

    @Test(priority = 1, testName = "Check flash sale on product list when flash sale is coming")
    public void FB185() {
        maximumLimit = 2;
        quantity = 10;
        homePage.createFlashSaleWithVariation(addComingStartMinute, addComingEndMinute, "M", maximumLimit, quantity);
        currentWindow = helper.getCurrentWindow();
        helper.openNewTab(helper.getCurrentURL());
        productListHandle = helper.getCurrentWindow();
        helper.refreshPage();
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(homePage.flashSaleName, false), productListPage.actualRS);
    }

    @Test(priority = 2, testName = "Check flash sale on product list when flash sale is end after before reload")
    public void FB186() {
        helper.switchToWindowHandle(currentWindow);
        storePage().navigateToHomePage();
        homePage.updateTimeFlashSale(homePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        helper.switchToWindowHandle(productListHandle);
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(homePage.flashSaleName, false), productListPage.actualRS);
    }

    @Test(priority = 3, testName = "Check flash sale on product list when flash sale is end after after reload")
    public void FB187() {
        helper.refreshPage();
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(homePage.flashSaleName, true), productListPage.actualRS);
    }

    //Product
    @Test(priority = 4, testName = "Check flash sale product on cart when user clicks add to cart icon")
    public void FB188() {
        helper.refreshPage();
        Assert.assertTrue(productListPage.clickFlashSaleProductByName(homePage.flashSaleName), homePage.actualRS);
    }

    @Test(priority = 5, testName = "Check flash sale on product list on new tab when flash sale is out of time before reload")
    public void FB189() {
        storePage().navigateToProductListTheme1();
        helper.switchToWindowHandle(currentWindow);
        homePage.updateTimeFlashSale(homePage.flashSaleName, addEndedStartMinute, addEndedEndMinute);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndedStartMinute);
        helper.switchToWindowHandle(productListHandle);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndedStartMinute + addEndedEndMinute);
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(homePage.flashSaleName, true), productListPage.actualRS);
    }

    @Test(priority = 6, testName = "Check flash sale on product list on new tab when flash sale is out of time after reload")
    public void FB190() {
        helper.refreshPage();
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(homePage.flashSaleName, false), productListPage.actualRS);
    }

    //Check flash sale tag when stopped by admin
    @Test(priority = 7, testName = "Verify flash sale tag on Product list when stop flash sale without reloaded ")
    public void FB12013() {
        storePage().navigateToHomePage();
        homePage.createFlashSaleNotFullVariations(addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        currentWindow = helper.getCurrentWindow();
        helper.openNewTab(helper.getCurrentUrl());
        storePage().navigateToProductListTheme1();
        helper.sleep(1);
        homePage.stopFlashSale(homePage.flashSaleName);
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(homePage.flashSaleName, true), productListPage.actualRS);
    }

    @Test(priority = 8, testName = "Verify flash sale tag on Product list when stop flash sale then reloaded ")
    public void FB12015() {
        helper.refreshPage();
        Assert.assertTrue(productListPage.checkProductFlashSaleByProductName(homePage.flashSaleName, false), productListPage.actualRS);
    }
}
