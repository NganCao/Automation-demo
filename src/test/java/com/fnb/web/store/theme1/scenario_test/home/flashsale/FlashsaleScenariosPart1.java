package com.fnb.web.store.theme1.scenario_test.home.flashsale;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.store.theme1.pages.checkout.CheckoutPage;
import com.fnb.web.store.theme1.pages.login.DataTest;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.fnb.web.setup.Setup.*;

public class FlashsaleScenariosPart1 extends BaseTest {
    //Check on home page
    // Testcase:https://mediastep1-my.sharepoint.com/:x:/g/personal/ngan_cao_thi_kim_gosell_vn/EQtsv7XuR7VOuGBoZpHOKRkBCdTct0yNUZ74Q5qIB96Bsg?e=CUOyYp&nav=MTVfe0QxN0U3NDg1LTM0RjUtNDk0MC1BRDgwLTg0Rjg1NjZBRjZERn0
    private Helper helper;
    private SoftAssert softAssert;
    private HomePage homePage;
    private CheckoutPage checkoutPage;
    private HomeDataTest homeDataTest;
    private DataTest loginDataTest;
    private int addComingStartMinute = 20;
    private int addComingEndMinute = 20;
    private int addEndAfterStartMinute = 1;
    private int addEndAfterEndMinute = 10;
    private int addEndedStartMinute = 1;
    private int addEndedEndMinute = 1;
    private String comingFlashSaleName = "";
    private String endAfterFlashSaleName = "";
    private String oldTime = "";
    private String newTime = "";
    private String productName = "";
    private String checkoutURL = "";
    private String currentWindow = "";
    private String checkoutHandle = "";
    private String cartHandle = "";
    private int quantity = 0;
    private int maximumLimit = 0;
    private int cartQuantity = 0;

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = storePage().navigateToHomePage();
        helper = storePage().helper;
        checkoutPage = new CheckoutPage(getDriver());
        softAssert = new SoftAssert();
    }

    //create coming
    @Test(priority = 1, testName = "Verify display of Coming flash sale", description = "Verify display of Coming flash sale")
    public void FB9455() {
        Assert.assertTrue(homePage.checkTabFlashSaleAfterCreatedFlashSale(true, addComingStartMinute, addComingEndMinute, 2, false), homePage.actualRS);
    }

    //check coming
    @Test(priority = 2, testName = "Verify display of Coming flash sale main session", description = "Verify display of Coming flash sale main session")
    public void FB94801() {
        storePage().navigateToHomePage();
        Assert.assertTrue(homePage.checkDisplayOfMainSession(homePage.flashSaleName, false, 2), homePage.actualRS);
        helper.refreshPage();
        Assert.assertTrue(homePage.checkCSSOfMainSession(), homePage.actualRS);
    }

    @Test(priority = 2, testName = "Verify CSS of Coming flash sale main session", description = "Verify CSS of Coming flash sale main session")
    public void FB94802() {
        Assert.assertTrue(homePage.checkCSSOfMainSession(), homePage.actualRS);
    }

    //change and check end after
    @Test(priority = 3, testName = "Update flash sale time and check automatically change to EndAfter", description = "Update flash sale time and check automatically change to EndAfter")
    public void FB9456() {
        homePage.updateTimeFlashSale(homePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        Assert.assertTrue(homePage.waitTimeToChangeStatus(addEndAfterStartMinute), homePage.actualRS);
    }

    @Test(priority = 4, testName = "Verify display of End after flash sale main session", description = "Verify display of End after flash sale main session")
    public void FB94561() {
        Assert.assertTrue(homePage.checkTabFlashSaleAfterCreatedFlashSale(false, addEndAfterStartMinute, addEndAfterEndMinute, 1, false), homePage.actualRS);
    }

    @Test(priority = 5, testName = "Verify display of EndAfter main session", description = "Verify display of Coming flash sale main session")
    public void FB94971() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkDisplayOfMainSession(homePage.flashSaleName, true, 1), homePage.actualRS);
    }

    @Test(priority = 5, testName = "Verify CSS of EndAfter main session", description = "Verify CSS of EndAfter flash sale main session")
    public void FB94972() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkCSSOfMainSession(), homePage.actualRS);
    }

    //check ended
    @Test(priority = 8, testName = "Stop end after flash sale then create ended flash sale", description = "Stop end after flash sale then create ended flash sale")
    public void FB9457() {
        homePage.stopFlashSale(homePage.flashSaleName);
        homePage.createFlashSale(addEndedStartMinute, addEndedEndMinute, false);
        helper.refreshPage();
        homePage.checkDisplayOfFlashSaleComponent();
        homePage.waitTimeChangeStatus(addEndedStartMinute + addEndedEndMinute);
    }

    @Test(priority = 9, testName = "Verify display of Ended flash sale", description = "Verify display of Ended flash sale ")
    public void FB94612() {
        Assert.assertTrue(homePage.checkTabFlashSaleAfterCreatedFlashSale(false, addEndedStartMinute, addEndedEndMinute, 0, false), homePage.actualRS);
    }

    @Test(priority = 10, testName = "Verify display of Ended flash sale main session", description = "Verify display of Ended flash sale main session")
    public void FB94651() {
        Assert.assertTrue(homePage.checkDisplayOfMainSession(homePage.flashSaleName, false, 0), homePage.actualRS);
    }

    @Test(priority = 10, testName = "Verify display of Ended flash sale main session", description = "Verify display of Ended flash sale main session")
    public void FB94652() {
        Assert.assertTrue(homePage.checkCSSOfMainSession(), homePage.actualRS);
    }

    //Create Coming flash sale
    @Test(priority = 11, testName = "Check tab active between Coming and Ended flash sale", description = "Check tab active between Coming and Ended flash sale")
    public void FB9484() {
        homePage.createFlashSale(addComingStartMinute, addComingEndMinute, false);
        comingFlashSaleName = homePage.flashSaleName;
        helper.refreshPage();
        Assert.assertTrue(homePage.checkActiveStatusTab(true, false, true));
    }

    //change and check end after
    @Test(priority = 12, testName = "Update flash sale time and check automatically change to EndAfter", description = "Update flash sale time and check automatically change to EndAfter")
    public void FB12() {
        homePage.updateTimeFlashSale(homePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
        endAfterFlashSaleName = homePage.flashSaleName;
        helper.refreshPage();
        Assert.assertTrue(homePage.waitTimeToChangeStatus(addEndAfterStartMinute), homePage.actualRS);
    }

    @Test(priority = 13, testName = "Check tab active between EndAfter and Ended flash sale", description = "Check tab active between EndAfter and Ended flash sale")
    public void FB9482() {
        Assert.assertTrue(homePage.checkActiveStatusTab(true, true, false));
    }

    //create coming flash sale
    @Test(priority = 14, testName = "Check tab active between Coming, EndAfter and Ended flash sale", description = "Check tab active between Coming, EndAfter and Ended flash sale")
    public void FB9516() {
        homePage.createFlashSale(addEndAfterStartMinute + addEndAfterEndMinute, addComingEndMinute, false);
        comingFlashSaleName = homePage.flashSaleName;
        helper.refreshPage();
        Assert.assertTrue(homePage.checkActiveStatusTab(true, true, true), homePage.actualRS);
    }

    @Test(priority = 15, testName = "Clear all flash sale", description = "Clear all flash sale")
    public void FB15() {
        homePage.stopFlashSale(endAfterFlashSaleName);
        homePage.deleteFlashSale(comingFlashSaleName);
    }

    //Coming flash sale
    @Test(priority = 16, testName = "Check priority display of Coming flash sale: coming 2 earlier than coming 1", description = "Check tab active between Coming and Ended flash sale")
    public void FB9518() {
        int addFirstComingMinutes = addComingStartMinute + addComingEndMinute;
        homePage.createFlashSale(addFirstComingMinutes, addComingEndMinute, false); //adding 10 minutes to create flash sale will hide
        comingFlashSaleName = homePage.flashSaleName;
        helper.refreshPage();
        oldTime = homePage.getFlashSaleTabTime();
        homePage.createFlashSale(addComingStartMinute, addComingStartMinute - 1, false); //make sure this flash sale will be created before the previous flash sale
        helper.refreshPage();
        newTime = homePage.getFlashSaleTabTime();
        Assert.assertNotEquals(oldTime, newTime, "Old time: " + oldTime + " Expected: " + newTime);
    }

    @Test(priority = 17, testName = "Clear all Coming flash sale", description = "Clear all Coming flash sale")
    public void FB17() {
        homePage.deleteFlashSale(comingFlashSaleName);
        comingFlashSaleName = homePage.flashSaleName;
        oldTime = newTime;
    }

    @Test(priority = 18, testName = "Check priority display of Coming flash sale: coming 2 is later than coming 1", description = "Check tab active between Coming and Ended flash sale")
    public void FB9519() {
        homePage.createFlashSale(addComingStartMinute + addComingEndMinute, addComingEndMinute, false);
        helper.refreshPage();
        newTime = homePage.getFlashSaleTabTime();
        Assert.assertEquals(oldTime, newTime, "Old time: " + oldTime + " Expected: " + newTime);
    }

    @Test(priority = 19, testName = "Clear all Coming flash sale", description = "Clear all Coming flash sale")
    public void FB19() {
        homePage.deleteFlashSale(comingFlashSaleName);
        homePage.deleteFlashSale(homePage.flashSaleName);
    }

    //Ended flash sale
    @Test(priority = 20, testName = "Check priority display of Ended flash sale: Ended 1 earlier than ended 2", description = "Check priority display of Ended flash sale")
    public void FB9521() {
        homePage.createFlashSale(addEndedStartMinute, addEndedEndMinute, false); //adding 10 minutes to create flash sale will hide
        helper.refreshPage();
        homePage.checkDisplayOfFlashSaleComponent();
        homePage.waitTimeChangeStatus(addEndedStartMinute + addEndedEndMinute);
        helper.refreshPage();
        oldTime = homePage.getFlashSaleTabTime();
        homePage.createFlashSale(addEndedStartMinute, addEndedEndMinute, false); //adding 10 minutes to create flash sale will hide
        comingFlashSaleName = homePage.flashSaleName;
        helper.refreshPage();
        homePage.checkDisplayOfFlashSaleComponent();
        homePage.waitTimeChangeStatus(addEndedStartMinute + addEndedEndMinute);
        helper.refreshPage();
        newTime = homePage.getFlashSaleTabTime();
        Assert.assertNotEquals(oldTime, newTime, "Old time: " + oldTime + " Expected: " + newTime);
    }

    //Create Coming flash sale on tomorrow
    @Test(priority = 21, testName = "Check display of Coming flash sale with next day", description = "Check tab active between Coming and Ended flash sale")
    public void FB9522() {
        int addFirstComingMinutes = 1440;
        homePage.createFlashSale(addFirstComingMinutes, addComingEndMinute, false);
        helper.refreshPage();
        Assert.assertTrue(homePage.checkActiveStatusTab(true, false, false));
    }

    @Test(priority = 22, testName = "Clear all Coming flash sale", description = "Clear all Coming flash sale")
    public void FB22() {
        homePage.deleteFlashSale(homePage.flashSaleName);
    }

    @Test(priority = 23, testName = "Create Coming flash sale", description = "Check tab active between Coming and Ended flash sale")
    public void FB23() {
        homePage.createFlashSale(addComingStartMinute, addComingEndMinute, true);
    }

    @Test(priority = 24, testName = "Check product has not belongs to selected branch", description = "Check tab active between Coming and Ended flash sale")
    public void FB95241() {
        Assert.assertTrue(homePage.checkDisplayOfMainSessionAfterChangeBranch(homePage.flashSaleName), homePage.actualRS);
    }

    //change branch
    @Test(priority = 24, testName = "Change branch", description = "Clear all Coming flash sale")
    public void FB95242() {
        storePage().navigateToHomePage();
        homePage.selectBranchWithName(homePage.getBranchNameMissingProductByEnv());
    }

    @Test(priority = 25, testName = "Check product belongs to selected branch", description = "Check tab active between Coming and Ended flash sale")
    public void FB95243() {
        Assert.assertFalse(homePage.checkDisplayOfMainSessionAfterChangeBranch(homePage.flashSaleName), homePage.actualRS);
    }

    @Test(priority = 26, testName = "Change branch again", description = "Clear all Coming flash sale")
    public void FB26() {
        helper.refreshPage();
        homePage.selectBranchWithName(homePage.getBranchNameByEnv());
    }

    @Test(priority = 28, testName = "Clear all Coming flash sale", description = "Clear all Coming flash sale")
    public void FB28() {
        homePage.deleteFlashSale(homePage.flashSaleName);
    }

    @Test(priority = 29, testName = "Check display of flash sale if it belongs to the selected branch(es)", description = "Check tab active between Coming and Ended flash sale")
    public void FB11929() {
        homePage.createFlashSaleSpecialBranch(addComingStartMinute, addComingEndMinute);
        helper.refreshPage();
        Assert.assertTrue(homePage.checkActiveStatusTab(false, false, true), homePage.actualRS);
    }

    //change branch
    @Test(priority = 30, testName = "Change branch", description = "Clear all Coming flash sale")
    public void FB30() {
        helper.refreshPage();
        homePage.selectBranchWithName(homePage.getBranchNameMissingProductByEnv());
    }

    @Test(priority = 31, testName = "Check display of flash sale if it did not belongs to the selected branch(es)", description = "Check display of flash sale if it did not belongs to the selected branch(es)")
    public void FB11930() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkActiveStatusTab(true, false, false), homePage.actualRS);
    }

    @Test(priority = 32, testName = "Clear all Coming flash sale", description = "Clear all Coming flash sale")
    public void FB32() {
        homePage.deleteFlashSale(homePage.flashSaleName);
    }

    //check localization Ended flash sale
    @Test(priority = 33, testName = "Check localization of Ended flash sale", description = "Clear all Coming flash sale")
    public void FB119311() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkLanguageOfFlashSale(true, false, false), homePage.actualRS);
    }

    //change and check Coming
    @Test(priority = 34, testName = "Check localization of Coming", description = "Check localization of EndAfter")
    public void FB119312() {
        homePage.createFlashSale(addComingStartMinute, addComingEndMinute, false);
        helper.refreshPage();
        Assert.assertTrue(homePage.checkLanguageOfFlashSale(true, false, true), homePage.actualRS);
    }

    //change and check end after
    @Test(priority = 35, testName = "Check localization of EndAfter", description = "FB11931 - Check localization of EndAfter")
    public void FB119313() {
        homePage.updateTimeFlashSale(homePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        Assert.assertTrue(homePage.checkLanguageOfFlashSale(true, true, false), homePage.actualRS);
    }

    @Test(priority = 36, testName = "Stop end after flash sale", description = "Stop end after flash sale")
    public void FB36() {
        homePage.stopFlashSale(homePage.flashSaleName);
    }

    @Test(priority = 37, testName = "Create coming flash sale, end after flash saler to verify clickable of Flash sale tab", description = "Create coming flash sale, end after flash sale")
    public void FB371() {
        homePage.createFlashSale(addComingStartMinute, addComingEndMinute, false);
        comingFlashSaleName = homePage.flashSaleName;
        homePage.createFlashSale(addEndAfterStartMinute, addEndAfterEndMinute, false);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        Assert.assertTrue(homePage.checkClickableOfFlashSaleTab(), homePage.actualRS);
    }

    @Test(priority = 37, testName = "Create coming flash sale, end after flash saler to verify clickable of Flash sale tab", description = "Create coming flash sale, end after flash sale")
    public void FB372() {
        homePage.deleteFlashSale(comingFlashSaleName);
        homePage.stopFlashSale(homePage.flashSaleName);
    }

    //Create end after flash sale
    @Test(priority = 38, testName = "Create end after flash sale", description = "Create end after flash sale")
    public void addProductToCart() {
        homePage.createFlashSaleNotFullVariations(addEndAfterStartMinute, 180);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
    }

    @Test(priority = 39, testName = "Add product flash sale to cart", description = "Add product flash sale to cart")
    public void FB38() {
        helper.refreshPage();
        productName = homePage.clickAddToCartFromFlashSale(homePage.flashSaleName);
    }

    @Test(priority = 40, testName = "Verify display of flash sale product on cart when flash sale is in progress", description = "Verify display of flash sale product on cart when flash sale is in progress")
    public void FB39() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkCartWhenFlashSale(productName, true), homePage.actualRS);
    }

    @Test(priority = 41, testName = "Verify display of flash sale product on best selling product when flash sale is in progress", description = "Verify display of flash sale product on best selling product when flash sale is in progress")
    public void FB40() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkBestSellingAllWhenFlashSale(homePage.flashSaleName), homePage.actualRS);
    }

    @Test(priority = 41, testName = "Checkout then verify flash sale tag when flash sale is in progress", description = "Checkout then verify flash sale tag when flash sale is in progress")
    public void FB41() {
        helper.refreshPage();
        homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, true, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        softAssert.assertTrue(checkoutPage.checkDisplayOfFlashSaleBorder(), "Flash sale border did not display");
        softAssert.assertTrue(checkoutPage.checkDisplayOfFlashSaleTag(), "Flash sale tag did not display");
        softAssert.assertTrue(checkoutPage.checkDisplayOfFlashSaleLabel(), "Flash sale promotion label did not display");
        softAssert.assertAll();
    }

    @Test(priority = 42, testName = "Open checkout page on new tab", description = "Open checkout page on new tab")
    public void FB42() {
        checkoutURL = helper.getCurrentURL();
        storePage().navigateToHomePage();
        currentWindow = helper.getCurrentWindow();
        helper.openNewTab(checkoutURL);
        helper.waitForURLContains(checkoutURL);
        checkoutHandle = helper.getCurrentWindow();
        helper.openNewTab(baseUrl);
        cartHandle = helper.getCurrentWindow();
        homePage.clickCartIcon();
    }

    //stop flash sale by admin
    @Test(priority = 43, testName = "Stop end after flash sale", description = "Stop end after flash sale")
    public void FB43() {
        homePage.stopFlashSale(homePage.flashSaleName);
    }

    @Test(priority = 44, testName = "Check flash sale product on cart before reloaded", description = "Check flash sale product on cart before reloaded")
    public void FB44() {
        Assert.assertTrue(homePage.checkCartWhenFlashSale(productName, false), homePage.actualRS);
    }

    @Test(priority = 44, testName = "Click out and click cart again to check flash sale product on cart", description = "Click out and click cart again to check flash sale product on cart")
    public void FB45() {
        helper.refreshPage();
        Assert.assertFalse(homePage.checkCartWhenFlashSale(productName, true), homePage.actualRS);
    }

    @Test(priority = 46, testName = "Switch to checkout tab and check checkout page before reloaded", description = "Switch to checkout tab and check checkout page before reloaded")
    public void FB46() {
        helper.switchToWindowHandle(checkoutHandle);
        softAssert.assertTrue(checkoutPage.checkDisplayOfFlashSaleBorder(), "Flash sale border did not display");
        softAssert.assertTrue(checkoutPage.checkDisplayOfFlashSaleTag(), "Flash sale tag did not display");
        softAssert.assertTrue(checkoutPage.checkDisplayOfFlashSaleLabel(), "Flash sale promotion label did not display");
        softAssert.assertAll();
    }

    @Test(priority = 46, testName = "Check checkout after refresh", description = "Check checkout after refresh")
    public void FB47() {
        helper.refreshPage();
        softAssert.assertFalse(checkoutPage.checkDisplayOfFlashSaleBorder(), "Flash sale border did not display");
        softAssert.assertFalse(checkoutPage.checkDisplayOfFlashSaleTag(), "Flash sale tag did not display");
        softAssert.assertFalse(checkoutPage.checkDisplayOfFlashSaleLabel(), "Flash sale promotion label did not display");
        softAssert.assertAll();
    }

    @Test(priority = 48, testName = "Check flash sale campaign before reload", description = "Check flash sale campaign before reload")
    public void FB48() {
        helper.switchToWindowHandle(currentWindow);
        Assert.assertTrue(homePage.checkActiveStatusTab(true, true, false), "End after flash sale did not display");
    }

    @Test(priority = 50, testName = "Check flash sale campaign after reload", description = "Check flash sale campaign after reload")
    public void FB50() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkActiveStatusTab(true, false, false), homePage.actualRS);
    }

    @Test(priority = 52, testName = "Close all tab except main tab", description = "Close all tab except main tab")
    public void closeTab() {
        helper.closeAllOpenTabExceptMainTab(currentWindow);
    }

    //TODO Action can't run well with best selling product -> should manual this case
    @Test(priority = 53, testName = "Create coming flash sale then check best selling", description = "")
    public void FB52() {
        maximumLimit = 2;
        quantity = 10;
        cartQuantity = 5;
        storePage().navigateToHomePage();
        homePage.createFlashSaleWithVariation(addComingStartMinute, addComingEndMinute, "M", maximumLimit, quantity);
        helper.refreshPage();
        Assert.assertFalse(homePage.checkBestSellingAllWhenFlashSale(homePage.flashSaleName), homePage.actualRS);
    }

    @Test(priority = 54, testName = "Before reloading", description = "")
    public void FB54() {
        currentWindow = helper.getCurrentWindow();
        String homeUrl = helper.getCurrentURL();
        homePage.updateTimeFlashSale(homePage.flashSaleName, addEndAfterStartMinute, addEndAfterEndMinute);
        helper.refreshPage();
        helper.openNewTab(homeUrl);
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        helper.switchToWindowHandle(currentWindow);
        Assert.assertFalse(homePage.checkBestSellingAllWhenFlashSale(homePage.flashSaleName), homePage.actualRS);
    }

    @Test(priority = 55, testName = "After reloading", description = "")
    public void FB55() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkBestSellingAllWhenFlashSale(homePage.flashSaleName), homePage.actualRS);
    }

    @Test(priority = 56, testName = "Ended flash sale - Before reloading", description = "")
    public void FB56() {
        String homeUrl = helper.getCurrentURL();
        homePage.updateTimeFlashSale(homePage.flashSaleName, addEndedStartMinute, addEndedEndMinute);
        helper.refreshPage();
        homePage.waitTimeChangeStatus(addEndAfterStartMinute);
        helper.refreshPage();
        helper.openNewTab(homeUrl);
        homePage.waitTimeChangeStatus(addEndedEndMinute);
        Assert.assertTrue(homePage.checkBestSellingAllWhenFlashSale(homePage.flashSaleName), homePage.actualRS);
    }

    @Test(priority = 57, testName = "After reloading", description = "")
    public void FB125() {
        helper.refreshPage();
        Assert.assertFalse(homePage.checkBestSellingAllWhenFlashSale(homePage.flashSaleName), homePage.actualRS);
    }

    @Test(priority = 58, testName = "Stop created flash sale")
    public void FB126() {
        helper.closeAllOpenTabExceptMainTab(currentWindow);
        homePage.stopFlashSale(homePage.flashSaleName);
    }
}
