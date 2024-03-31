package com.fnb.web.store.theme1.scenario_test.home.addUserLocation;

import com.fnb.utils.helpers.Helper;
import com.fnb.web.setup.BaseTest;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.login.DataTest;
import com.fnb.web.store.theme1.pages.product_details.ProductDetailsDataTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddUserLocationScenarios extends BaseTest {
    private HomePage homePage;
    private HomeDataTest homeDataTest;
    private DataTest loginDataTest;
    private ProductDetailsDataTest productDetailsDataTest;
    SoftAssert softAssert;
    Helper helper;

    @BeforeClass
    public void navigateToLoginPage() {
        homePage = storePage().navigateToHomePage();
        softAssert = storePage().softAssert;
        helper = storePage().helper;
    }

    @Test(priority = 11, testName = "Verify display of add location component on Homepage")
    public void FB9378() {
        storePage().navigateToHomePage();
        Assert.assertTrue(homePage.checkDisplayAddLocationComponent(), "Add location component did not display");
    }

    @Test(priority = 11, testName = "Verify the height of location component")
    public void FB9380() {
        Assert.assertTrue(homePage.checkHeightAddLocation(HomeDataTest.LOCATION_HEIGHT), "the height of location component is incorrect");
    }

    @Test(priority = 11, testName = "Verify the color of location component")
    public void FB9381() {
        Assert.assertTrue(homePage.checkColorAddLocation(homeDataTest.LOCATION_BACKGROUND), "the color of location component is incorrect. Actual: " + homePage.actualRS);
    }

    @Test(priority = 11, testName = "Verify display of the location icon")
    public void FB9382() {
        Assert.assertTrue(homePage.checkDisplayAddLocationIcon(), "Add location icon did not display");
    }

    @Test(priority = 11, testName = "Verify display of the location label")
    public void FB9384() {
        Assert.assertTrue(homePage.checkDisplayReceiveLocationLabel(), "Add location label did not display");
    }

    @Test(priority = 11, testName = "Verify the font-size of location label")
    public void FB9386() {
        Assert.assertTrue(homePage.checkFontSizeAddLocationLabel(homeDataTest.LOCATION_LABEL_FONTSIZE), "size of the location icon is incorrect. Actual: " + homePage.actualRS);
    }

    @Test(priority = 11, testName = "Verify the color of location label")
    public void FB9387() {
        Assert.assertTrue(homePage.checkColorAddLocationLabel(homeDataTest.WHITE_COLOR), "The color of the location icon is incorrect. Actual: " + homePage.actualRS);
    }

    @Test(priority = 11, testName = "Verify display of Select store branch button")
    public void FB9388() {
        Assert.assertTrue(homePage.checkDisplaySelectBranchButton(), "Select branch button did not display");
    }

    @Test(priority = 11, testName = "Verify the background color of Select store branch button")
    public void FB9389() {
        Assert.assertTrue(homePage.checkColorSelectBranchButton(homeDataTest.SELECT_BRANCH_COLOR), "The color of Select branch button is incorrect. Actual: " + homePage.actualRS);
    }

    @Test(priority = 11, testName = "Verify the font-size of Select store branch text")
    public void FB9390() {
        Assert.assertTrue(homePage.checkFontSizeSelectBranchText(homeDataTest.SELECT_BRANCH_FONTSIZE), "the font-size of Select branch button is incorrect. Actual: " + homePage.actualRS);
    }

    @Test(priority = 11, testName = "Verify the color of Select store branch text")
    public void FB9391() {
        softAssert.assertTrue(homePage.checkDisplaySelectBranchText(), "Select branch text did not display");
        softAssert.assertTrue(homePage.checkColorSelectBranchText(homeDataTest.WHITE_COLOR), "color of store is incorrect. Actual: " + homePage.actualRS);
        softAssert.assertAll();
    }

    @Test(priority = 11, testName = "Verify display of store branch icon")
    public void FB9392() {
        Assert.assertTrue(homePage.checkDisplaySelectBranchIcon(), "Select branch icon did not display");
    }

    @Test(priority = 11, testName = "Verify the size of store icon")
    public void FB9393() {
        Assert.assertTrue(homePage.checkSizeSelectBranchIcon(HomeDataTest.STORE_BRANCH_ICON_HEIGHT, HomeDataTest.STORE_BRANCH_ICON_WIDTH), "the size of store icon is incorrect. Actual: " + homePage.actualRS);
    }

    @Test(priority = 11, testName = "Verify display of add user location on Product list page")
    public void FB9395() {
        storePage().navigateToProductListTheme1();
        Assert.assertTrue(homePage.checkDisplayAddLocationComponent(), "Add location component did not display on Product list");
    }

    @Test(priority = 11, testName = "Verify display of add user location on Product details page")
    public void FB9396() {
        storePage().navigateToHomePage();
        homePage.clickProductOnBestselling();
        helper.checkURL(productDetailsDataTest.URL);
        Assert.assertTrue(homePage.checkDisplayAddLocationComponent(), "Add location component did not display on Product details");
    }

    @Test(priority = 12, testName = "Verify display of the Add location dialog after user click on Location label")
    public void FB9398() {
        storePage().navigateToHomePage();
        homePage.clickLocationLabel();
        softAssert.assertTrue(homePage.checkDisplayOfDeliveryTab(), "Delivery tab did not display");
        softAssert.assertTrue(homePage.checkDisplayOfPickupTab(), "Pickup tab did not display");
        softAssert.assertAll();
    }

    @Test(priority = 12, testName = "Verify display of the Add location dialog after user click on Location label")
    public void FB9399() {
        helper.refreshPage();
        homePage.clickSelectStoreBranch();
        softAssert.assertTrue(homePage.checkDisplayOfDeliveryTab(), "Delivery tab did not display");
        softAssert.assertTrue(homePage.checkDisplayOfPickupTab(), "Pickup tab did not display");
        softAssert.assertAll();
    }

    @Test(priority = 12, testName = "Verify align of Add location dialog")
    public void FB9402() {
        helper.refreshPage();
        homePage.clickSelectAddress();
        Assert.assertTrue(homePage.checkAlignOfAddLocationDialog(homeDataTest.LOCATION_DIALOG_ALIGN), "Add location dialog aligns wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 12, testName = "Verify display of Delivery label")
    public void FB9403() {
        Assert.assertTrue(homePage.checkDisplayOfDeliveryLabel(), "Delivery label display wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 12, testName = "Verify display of Pickup label")
    public void FB9404() {
        Assert.assertTrue(homePage.checkDisplayOfPickupLabel(), "Pickup label display wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 12, testName = "Verify color of Delivery tab when ACTIVE")
    public void FB9405() {
        homePage.clickDelivery(true);
        Assert.assertTrue(homePage.checkColorOfDelivery(homeDataTest.ACTIVE_BG_COLOR, true), "Background color of Delivery display wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 12, testName = "Verify color of Pickup tab when INACTIVE")
    public void FB9406() {
        Assert.assertTrue(homePage.checkColorOfPickup(homeDataTest.INACTIVE_BG_COLOR, true), "Background color of Pickup display wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 12, testName = "Verify display of elements on Delivery tab after clicking Delivery")
    public void FB9407() {
        softAssert.assertTrue(homePage.checkDisplayOfDeliveryInput(false), "Delivery address input did not display");
        softAssert.assertTrue(homePage.checkDisplayOfDeliveryInputIcon(), "Delivery address icon did not display");
        softAssert.assertAll();
    }

    @Test(priority = 12, testName = "Verify display of elements on Delivery tab after clicking Delivery")
    public void FB9409() {
        homePage.enterAddressLocation(homeDataTest.ADDRESS_DELIVERY);
        softAssert.assertTrue(homePage.checkDisplayClear(), "Clear(x) icon did not display");
        softAssert.assertTrue(homePage.checkDisplayAddressList(), "Recommend address list did not display");
        softAssert.assertAll();
    }

    @Test(priority = 12, testName = "Verify the max-length of address input")
    public void FB94101() {
        Assert.assertTrue(homePage.checkMaxLengthOfAddressInput(homeDataTest.LENGTH_ADDRESS_INPUT, homeDataTest.MAXLENGTH_ADDRESS_INPUT), "The max-lenght of address input. Actual: " + homePage.actualRS);
    }

    @Test(priority = 12, testName = "Verify the max-length of address input")
    public void FB94102() {
        Assert.assertTrue(homePage.checkMaxLengthOfAddressInput(homeDataTest.LENGTH_ADDRESS_INPUT_AGAIN, homeDataTest.MAXLENGTH_ADDRESS_INPUT), "The max-lenght of address input after entering again. Actual: " + homePage.actualRS);
    }

    @Test(priority = 12, testName = "Verify when user clicks clear (x) icon")
    public void FB9411() {
        homePage.enterAddressLocation(homeDataTest.ADDRESS_DELIVERY);
        Assert.assertTrue(homePage.checkValueOfAddressAfterClear(), "Background color of Puckup display wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 12, testName = "Verify display of recommend address item")
    public void FB9413() {
        homePage.enterAddressLocation(homeDataTest.ADDRESS_DELIVERY_CHECK_NUMBER);
        Assert.assertTrue(homePage.checkMatchKeySearchAddress(HomeDataTest.ADDRESS_DELIVERY_CHECK_NUMBER), "the recommend item(s) are incorrect.");
    }

    @Test(priority = 12, testName = "Verify the number item will be displayed on recommend address list")
    public void FB9414() {
        Assert.assertTrue(homePage.checkNumberOfAddressItem(HomeDataTest.MAXNUMBER_ADDRESS_ITEMS), "the number item display wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 12, testName = "Verify when user clicks out side of location dialog")
    public void FB9415() {
        Assert.assertTrue(homePage.checkOutsideDialog(), "Location dialog still displayed");
    }

    @Test(priority = 12, testName = "Verify when user select an recommend address")
    public void FB94161() {
        helper.refreshPage();
        homePage.loginSuccessfully(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD);
        helper.refreshPage();
        homePage.enterAddress(homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
        Assert.assertTrue(homePage.checkLabelAfterSelectAddress(), "Address label displayed wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 12, testName = "Verify when user logged in and has address list")
    public void FB9417() {
        Assert.assertTrue(homePage.checkDisplayOfAddressList(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD), "Address list did not display");
    }

    @Test(priority = 13, testName = "Verify display of address list")
    public void FB94181() {
        Assert.assertTrue(homePage.checkDisplayAddressListTitle(), "address list title did not diplay");
    }

    @Test(priority = 13, testName = "Verify display of address list")
    public void FB94182() {
        Assert.assertTrue(homePage.checkDisplayAddressListAddress(), "address list details did not diplay");
    }

    @Test(priority = 13, testName = "Verify display of address list")
    public void FB94183() {
        Assert.assertTrue(homePage.checkDisplayOfDefaultTag(), "Default tag did not display");
    }

    @Test(priority = 13, testName = "Verify when user clicks my address item")
    public void FB9420() {
        Assert.assertTrue(homePage.checkDisplayOfAddressLabelAfterSelectMyAddress(homeDataTest.SELECT_INDEX_ADDRESS_LIST), "Address label displayed wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify when user select an branch - Check branch information")
    public void FB9421() {
        homePage.clickSelectAddress();
        homePage.clickClearIcon();
        homePage.clickPickup(true);
        Assert.assertTrue(homePage.checkBranchWithoutAddress(), homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify color of Pickup tab when ACTIVE")
    public void FB9422() {
        helper.refreshPage();
        homePage.clickSelectStoreBranch();
        Assert.assertTrue(homePage.checkColorOfPickup(HomeDataTest.ACTIVE_BG_COLOR, false), "Color of Pickup is wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify color of Delivery tab when INACTIVE")
    public void FB9423() {
        Assert.assertTrue(homePage.checkColorOfDelivery(HomeDataTest.INACTIVE_BG_COLOR, false), "Color of Delivery is wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify display of elements on Pickup tab")
    public void FB9424() {
        Assert.assertTrue(homePage.checkDisplayOfElementOnPickup(), homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify display of branch list")
    public void FB9425() {
        Assert.assertTrue(homePage.checkDisplayOfBranchList(), "Branch list did not display");
    }

    @Test(priority = 13, testName = "Verify display of nearest branch item")
    public void FB94271() {
        Assert.assertTrue(homePage.checkDisplayOfNearestBranchListItem(), "Nearest Branch item did not display");
    }

    @Test(priority = 13, testName = "Verify display of nearest branch item")
    public void FB94272() {
        Assert.assertTrue(homePage.checkDisplayOfNearestBranchListName(), "Nearest Branch name list did not display");
    }

    @Test(priority = 13, testName = "Verify display of nearest branch item")
    public void FB94273() {
        Assert.assertTrue(homePage.checkDisplayOfNearestBranchListDistance(), "Branch distance list did not display");
    }

    @Test(priority = 13, testName = "Verify display of nearest branch item")
    public void FB94274() {
        Assert.assertTrue(homePage.checkDisplayOfNearestBranchListAddress(), "Branch address list did not display");
    }

    @Test(priority = 13, testName = "Verify display of nearest branch item")
    public void FB94275() {
        Assert.assertTrue(homePage.checkDisplayOfNearestCheckedBranch(), "Branch checked list did not display");
    }

    @Test(priority = 13, testName = "Verify display of nearest branch item")
    public void FB94276() {
        Assert.assertTrue(homePage.checkDisplayOfSelectedDefaultBranchChecked(), "Nearest branch checked default displayed wrong.\nActual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify display of nearest branch item")
    public void FB94277() {
        Assert.assertTrue(homePage.checkColorOfSelectedDefaultBranchChecked(homeDataTest.CHECKED_BRANCH_ICON), "Nearest branch border checked displayed wrong.\nActual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify display of nearest branch item")
    public void FB9429() {
        Assert.assertTrue(homePage.checkColorOfSelectedDefaultBranchIcon(homeDataTest.CHECKED_BRANCH_ICON), "Nearest branch border checked displayed wrong.\nActual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify display of nearest branch icon")
    public void FB9428() {
        Assert.assertTrue(homePage.checkDisplayOfNearestBranchListIcon(), "Nearest Branch icon list did not display");
    }

    @Test(priority = 13, testName = "Verify font-size of icon")
    public void FB9430() {
        Assert.assertTrue(homePage.checkSizeNearestBranchListIcon(homeDataTest.BRANCH_ICON_H, homeDataTest.BRANCH_ICON_W), "font-size of the Nearest Branch icon list displayed wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify font-size of branch address")
    public void FB94311() {
        Assert.assertTrue(homePage.checkSizeOfNearestBranchListName(homeDataTest.BRANCH_NAME_SIZE), "font-size of the Nearest Branch name list displayed wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify font-weight of branch address")
    public void FB94312() {
        Assert.assertTrue(homePage.checkFontWeightOfNearestBranchListName(homeDataTest.BRANCH_NAME_WEIGHT), "font-weight of the Nearest Branch name list displayed wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify font-size of branch address")
    public void FB94321() {
        Assert.assertTrue(homePage.checkSizeOfNearestBranchListAddress(homeDataTest.BRANCH_ADDRESS_SIZE), "font-size of the Nearest Branch name list displayed wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify font-weight of branch address")
    public void FB94322() {
        Assert.assertTrue(homePage.checkFontWeightOfNearestBranchListAddress(homeDataTest.BRANCH_ADDRESS_WEIGHT), "font-weight of the Nearest Branch name list displayed wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify display of other branch item")
    public void FB9434() {
        Assert.assertTrue(homePage.checkDisplayOfOtherBranchListItem(), "other Branch item did not display");
    }

    @Test(priority = 13, testName = "Verify display of other branch item")
    public void FB94341() {
        Assert.assertTrue(homePage.checkDisplayOfOtherBranchListName(), "other Branch name list did not display");
    }

    @Test(priority = 13, testName = "Verify display of other branch item")
    public void FB94343() {
        Assert.assertTrue(homePage.checkDisplayOfOtherBranchListDistance(), "other distance list did not display");
    }

    @Test(priority = 13, testName = "Verify display of other branch item")
    public void FB94344() {
        Assert.assertTrue(homePage.checkDisplayOfOtherBranchListAddress(), "other address list did not display");
    }

    @Test(priority = 13, testName = "Verify display of other branch item")
    public void FB94345() {
        Assert.assertTrue(homePage.checkDisplayOfOtherCheckedBranch(), "other checked list did not display");
    }

    @Test(priority = 13, testName = "Verify color of other branch icon on Other branch")
    public void FB9435() {
        Assert.assertTrue(homePage.checkColorOfOtherBranchIconList(homeDataTest.CHECKED_BRANCH_ICON), "other branch border checked displayed wrong.\nActual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify font-size of icon on Other branch")
    public void FB9444() {
        Assert.assertTrue(homePage.checkSizeOfOtherBranchIconList(homeDataTest.BRANCH_ICON_HEIGHT, homeDataTest.BRANCH_ICON_WIDTH), "font-size of icon on Other branch displayed wrong.\nActual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify display of other branch icon")
    public void FB94342() {
        Assert.assertTrue(homePage.checkDisplayOfOtherBranchListIcon(), "other Branch icon list did not display");
    }

    @Test(priority = 13, testName = "Verify font-size of branch address on Other branch")
    public void FB94451() {
        Assert.assertTrue(homePage.checkFontSizeOfOtherBranchListName(homeDataTest.BRANCH_NAME_SIZE), "font-size of branch address displayed wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 13, testName = "Verify font-weight of branch address on Other branch")
    public void FB94452() {
        Assert.assertTrue(homePage.checkFontWeightOfOtherBranchListAddress(homeDataTest.BRANCH_NAME_WEIGHT), "font-size of branch address displayed wrong. Actual: " + homePage.actualRS);
    }

    @Test(priority = 14, testName = "Verify when user select an branch")
    public void FB94391() {
        Assert.assertTrue(homePage.checkDisplayAfterSelectBranch(homeDataTest.BRANCH_INDEX), "Select branch button displayed wrong.\nActual: " + homePage.actualRS + "\nExpected: " + homePage.expectedRS);
    }

    @Test(priority = 14, testName = "Verify when user select an branch")
    public void FB94392() {
        homePage.clickSelectStoreBranch();
        Assert.assertTrue(homePage.checkColorBranchIconAfterSelectBranch(homeDataTest.BRANCH_INDEX, homeDataTest.CHECKED_BRANCH_ICON), "Color of branch icon displayed wrong.\nActual: " + homePage.actualRS);
    }

    @Test(priority = 14, testName = "Verify when user select an branch")
    public void FB94394() {
        Assert.assertTrue(homePage.checkColorCheckedIconAfterSelectBranch(homeDataTest.BRANCH_INDEX, homeDataTest.CHECKED_BRANCH_ICON), "Color of checked icon displayed wrong.\nActual: " + homePage.actualRS);
    }

    @Test(priority = 14, testName = "Verify when user updates new address - Check distance list order")
    public void FB94401() throws InterruptedException {
        helper.refreshPage();
        homePage.enterDeliveryAddress(homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST);
        Thread.sleep(1000);
        homePage.clickSelectStoreBranch();
        Assert.assertTrue(homePage.checkBranchAfterAddAddress(), "After add user address then check nearest branch\n" + homePage.actualRS);
    }

    @Test(priority = 14, testName = "Verify when user select an branch - check branch information")
    public void FB94402() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkBranchAfterEnterAddress(homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST));
    }

    @Test(priority = 15, testName = "Verify display of add user location on Checkout page")
    public void FB9397() {
        helper.refreshPage();
        Assert.assertTrue(homePage.checkDisplayAddLocationComponent(), "Add location component did not display on Checkout");
    }

    @Test(priority = 20, testName = "Verify display of add user location on Checkout page")
    public void logOut() {
        homePage.checkAfterLogout();
    }
}
