package com.fnb.web.store.theme1.pages.checkout;

import com.fnb.utils.api.admin.helpers.JsonAPIAdminReader.*;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme1.pages.home.FlashSaleDataTest;
import com.fnb.web.store.theme1.pages.home.HomeDataTest;
import com.fnb.web.store.theme1.pages.home.HomePage;
import com.fnb.web.store.theme1.pages.login.CheckOutLoginPage;
import com.fnb.web.store.theme1.pages.login.DataTest;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckoutPage extends Setup {
    //select address
    public static By selectAddressSuccess = By.xpath("(//div[contains(@class,\"ant-notification-notice-message\")])[1]");
    //header
    public static By bannerHeaderCheckout = By.id("themeHeaderCheckout");
    //product info
    public static By productDetailsSection = By.xpath("//div[contains(@class, \"product_detail\")]");
    public static By totalQuantity = By.xpath("//div[@class=\"total\"]/div[@class=\"quantity\"]");
    public static By deleteList = By.xpath("//div[contains(@class,\"delete\")]");
    //Complete
    public static By completeBtn = By.xpath("//button[contains(@class,\"shipping_complete\")]");
    //checkout successfully
    public static By checkOutPopup = By.xpath("//div[contains(@class,\"check-out-cash-theme1-vn\")]");
    public static By congratulationText = By.xpath("//div[contains(@class,\"check-out-cash-title1\")]/span");
    public static By viewOrder = By.xpath("//div[contains(@class,\"check-out-cash-theme1\")]//div[contains(@class,\"view-order\")]");
    public static By newOrder = By.xpath("//div[contains(@class,\"new-order\")]");
    public static By toastMsg = By.xpath("//div[contains(@class,\"ant-notification-notice-message\")]");
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public String actualRS;
    //user info
    public By userName = By.id("txtName");
    public By phoneNumber = By.id("txtPhone");
    private CheckoutDataTest dataTest;
    private String expectedRS;
    private HomePage homePage;
    private JsonReader jsonReader;
    private DataTest loginDataTest;
    private HomeDataTest homeDataTest;
    private FlashSaleDataTest flashSaleDataTest;
    private CheckOutLoginPage checkOutLoginPage;
    private int cartQuantityNew = 0, totalOriginalPrice = 0;
    private By changeAddressBtn = By.xpath("//div[contains(@class,\"box_delivery\")]//div[contains(@class,\"header-button\")]");
    private By boxProductList = By.xpath("//div[contains(@class, \"product_cart_theme1\")]");
    private By productImage = By.xpath("//div[contains(@class,\"product_image\")]/div");
    //dialog
    private By confirmDialog = By.xpath("//div[contains(@class,\"confirmation-modal\")]");
    private String dialogContent = ".//div[contains(@class,\"confirmation-dialog-content\")]";
    private String okayBtnDialog = ".//button[contains(@class,\"ant-btn-default\")]";
    //total
    private By subTotalAmount = By.xpath("//div[contains(@class,\"cart_sub_total\")]/div[contains(@class,\"amount\")]");
    //flash sale
    private By flashSaleImageBorder = By.xpath("//div[contains(@class, \"product_cart_theme1\")]//div[contains(@class, \"product_image\") and contains(@class, \"flash-sale-border\")]");
    private By notFlashSaleImage = By.xpath("//div[contains(@class, \"product_cart_theme1\")]//div[contains(@class, \"product_image\") and not(contains(@class, \"flash-sale-border\"))]");
    private By flashSaleImageTag = By.xpath("//div[contains(@class, \"product_cart_theme1\")]//div[contains(@class, \"flash-sale-checkout\")]/img");
    private By promotionLabelCheckout = By.xpath("//div[contains(@class, \"product_cart_theme1\")]//div[contains(@class, \"promotion-label-checkout\")]/span");
    private By flashSaleProductName = By.xpath("//div[contains(@class,\"flash-sale-border\")]//following-sibling::div/div[contains(@class,\"name\")]");
    private By flashSaleProductQuantity = By.xpath("//div[contains(@class,\"flash-sale-border\")]/parent::div/following-sibling::div/div/div[contains(@class,\"quantity\")]");
    private String flashSaleTagXP = ".//following-sibling::div[contains(@class,\"flash-sale-checkout\")]/img";
    //Xpath can user for normal product and flash sale product
    private String productPriceXP = "./following-sibling::div/div[contains(@class, \"price_box\")]/div";
    private String productQuantityXP = "./parent::div/following-sibling::div/div/div[contains(@class,\"quantity\")]";
    private String productNameXP = ".//following-sibling::div/div[contains(@class,\"name\")]";
    private String upQuantityBtnXP = "./parent::div/following-sibling::div/div[contains(@class,\"change-quantity-group\")]//div[contains(@class,\"up\")]";
    private String downQuantityBtnXP = "./parent::div/following-sibling::div/div[contains(@class,\"change-quantity-group\")]//div[contains(@class,\"down\")]";
    private By okayBtn = By.xpath("//div[contains(@class,\"confirmation-modal\")]//button");

    public CheckoutPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        homePage = commonPagesTheme1().homePage;
        this.driver = driver;
    }

    public Boolean checkDisplayOfUserName() {
        try {
            helper.waitUtilElementVisible(driver.findElement(userName));
            return true;
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            return false;
        }
    }

    public Boolean checkDisplayOfPhoneNumber() {
        try {
            helper.waitUtilElementVisible(driver.findElement(userName));
            return true;
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            return false;
        }
    }

    public void checkDisplayTotalQuantity() {
        helper.checkDisplayElement(totalQuantity);
    }

    public Boolean checkValueOfSubTotalQuantity(String expected) {
        helper.waitForPresence(subTotalAmount);
        actualRS = "Actual: " + driver.findElement(subTotalAmount).getText().trim() + " Expected: " + expected;
        return driver.findElement(subTotalAmount).getText().trim().equals(expected);
    }

    public Boolean checkValueOfUsername() {
        return driver.findElement(userName).getAttribute("value").trim().equals(dataTest.USERNAME);
    }

    //flash sale

    /**
     * Apply for one product flash sale
     *
     * @return
     */
    public Boolean checkDisplayOfFlashSaleBorder() {
        return helper.checkDisplayElement(flashSaleImageBorder);
    }

    /**
     * Apply for one product flash sale
     *
     * @return
     */
    public Boolean checkDisplayOfFlashSaleTag() {
        return helper.checkDisplayElement(flashSaleImageTag);
    }

    /**
     * Apply for one product flash sale
     *
     * @return
     */
    public Boolean checkDisplayOfFlashSaleLabel() {
        return helper.checkDisplayElement(promotionLabelCheckout);
    }

    /**
     * Check flash sale / normal product following isFlashSale
     *
     * @param flashSaleProduct got when add to cart. Include: name, original price, flash sale price
     * @param isFlashSale
     * @param iscLickCart
     * @param isLogin
     * @return
     */
    public Boolean checkCheckoutPriceWhenFlashSale(FlashSaleProduct flashSaleProduct, Boolean isFlashSale, Boolean iscLickCart, Boolean isLogin, Boolean isEnterAddress) {
        helper.waitForJStoLoad();
        String productName = flashSaleProduct.getName();
        String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
        String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        if (isLogin) {
            homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, isEnterAddress, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        } else {
            if (iscLickCart) {
                helper.pressPageUpAction();
                homePage.clickCartIcon();
                checkOutLoginPage = homePage.clickCheckoutOnCart();
                homePage.clickCartIcon();
            }
        }
        helper.waitForPresence(bannerHeaderCheckout);
        helper.visibleOfLocated(bannerHeaderCheckout);
        if (isFlashSale) {
            helper.visibleOfLocated(productDetailsSection);
            List<WebElement> productCartFSList = helper.getElementList(flashSaleImageBorder);
            if (productCartFSList.size() > 0) {
                for (WebElement element : productCartFSList) {
                    //check name
                    if (productName.equals(element.findElement(By.xpath(productNameXP)).getText().trim())) {
                        flag.add(true);
                        actualRS = actualRS + "Flash sale still display with product: " + productName + "\n";
                    } else {
                        actualRS = actualRS + "Flash sale did not display with product: " + productName + "\n";
                        flag.add(false);
                    }
                    if (flashSalePrice.equals(element.findElement(By.xpath(productPriceXP)).getText().trim())) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale price is incorrect. Actual: " + element.findElement(By.xpath(productPriceXP)).getText().trim() + " Expected: " + flashSalePrice + "\n";
                        flag.add(false);
                    }
                    //check flash sale tag
                    try {
                        element.findElement(By.xpath(flashSaleTagXP)).isDisplayed();
                        flag.add(true);
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        actualRS = actualRS + "Flash sale tag did not display\n";
                        flag.add(false);
                    }
                }
            } else {
                flag.add(false);
                actualRS = actualRS + "Flash sale did not display with product: " + productName;
            }
        } else {
            helper.visibleOfLocated(productDetailsSection);
            List<WebElement> productCartNotFSList = helper.getElementList(notFlashSaleImage);
            if (productCartNotFSList.size() > 0) {
                for (WebElement element : productCartNotFSList) {
                    try {
                        helper.waitUtilElementVisible(element.findElement(By.xpath(productNameXP)));
                    } catch (Exception ex) {
                        Log.info(ex.getMessage());
                    }
                    if (productName.equals(element.findElement(By.xpath(productNameXP)).getText().trim())) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale Product name is incorrect. Actual: " + element.findElement(By.xpath(productNameXP)).getText().trim() + " Expected: " + productName + "\n";
                        System.out.println(actualRS);
                        flag.add(false);
                    }
                    if (originalPrice.equals(element.findElement(By.xpath(productPriceXP)).getText().trim())) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Original price is incorrect. Actual: " + element.findElement(By.xpath(productPriceXP)).getText().trim() + " Expected: " + originalPrice + "\n";
                        System.out.println(actualRS);
                        flag.add(false);
                    }
                    //check flash sale tag
                    try {
                        element.findElement(By.xpath(flashSaleTagXP)).isDisplayed();
                        actualRS = actualRS + "Flash sale tag displayed on normal product\n";
                        flag.add(false);
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        flag.add(true);
                    }
                }
            } else {
                flag.add(false);
                actualRS = actualRS + "Nornal product did not display with product: " + productName;
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    /**
     * @param flashSaleProduct got when add to cart. Include: name, original price, flash sale price
     * @param cartQuantity
     * @param maximumLimit
     * @param isLogin
     * @param isClickCart
     * @return
     */
    public Boolean checkCheckoutQuantityWhenFlashSale(FlashSaleProduct flashSaleProduct, int cartQuantity, int maximumLimit, Boolean isLogin, Boolean isClickCart, Boolean isEnterAddress) {
        helper.waitForJStoLoad();
        String productName = flashSaleProduct.getName();
        String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
        String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        if (isLogin) {
            homePage.checkoutWhenFlashSale(loginDataTest.PHONE_DATA, loginDataTest.PASSWORD, isEnterAddress, homeDataTest.ADDRESS_DELIVERY, homeDataTest.SELECT_INDEX_ADDRESS_LIST, false);
        } else {
            if (isClickCart) {
                helper.pressPageUpAction();
                homePage.clickCartIcon();
                checkOutLoginPage = homePage.clickCheckoutOnCart();
            }
        }
        helper.waitForPresence(bannerHeaderCheckout);
        helper.visibleOfLocated(bannerHeaderCheckout);
        int quantity = 0, remainingQuantity = 0, cartList = 0;
        if (maximumLimit < cartQuantity) {
            quantity = maximumLimit;
            remainingQuantity = cartQuantity - maximumLimit;
            cartList = 2;
        } else if (maximumLimit > cartQuantity) {
            quantity = cartQuantity;
            cartList = 2;
        } else {
            quantity = cartQuantity;
            cartList = 1;
        }
        int load = 0;
        while (load < 5) {
            List<WebElement> boxProductElementList = helper.getElementList(boxProductList);
            if (boxProductElementList.size() != cartList) {
                boxProductElementList = helper.getElementList(boxProductList);
                load++;
            } else break;
        }
        List<WebElement> productCartFSList = helper.getElementList(flashSaleImageBorder);
        if (productCartFSList.size() == 1) {
            for (WebElement element : productCartFSList) {
                if (flashSalePrice.equals(element.findElement(By.xpath(productPriceXP)).getText().trim())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale price is incorrect. Actual: " + element.findElement(By.xpath(productPriceXP)).getText().trim() + " Expected: " + flashSalePrice + "\n";
                    flag.add(false);
                }
                //check flash sale tag
                try {
                    element.findElement(By.xpath(flashSaleTagXP)).isDisplayed();
                    flag.add(true);
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                    actualRS = actualRS + "Flash sale tag did not display\n";
                    flag.add(false);
                }
                //check quantity of fs product
                if (String.valueOf(quantity).equals(element.findElement(By.xpath(productQuantityXP)).getText().trim())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale quantity is incorrect. Actual: " + element.findElement(By.xpath(productQuantityXP)).getText().trim() + " Expected: " + quantity + "\n";
                    flag.add(false);
                }
            }
        } else {
            flag.add(false);
            actualRS = actualRS + "Flash sale did not display with product: " + productName;
        }
        List<WebElement> productCartNotFSList = helper.getElementList(notFlashSaleImage);
        if (productCartNotFSList.size() == 1) {
            for (WebElement element : productCartNotFSList) {
                try {
                    helper.waitUtilElementVisible(element.findElement(By.xpath(productNameXP)));
                } catch (Exception ex) {
                    Log.info(ex.getMessage());
                }
                if (originalPrice.equals(element.findElement(By.xpath(productPriceXP)).getText().trim())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Original price is incorrect. Actual: " + element.findElement(By.xpath(productPriceXP)).getText().trim() + " Expected: " + originalPrice + "\n";
                    System.out.println(actualRS);
                    flag.add(false);
                }
                if (String.valueOf(remainingQuantity).equals(element.findElement(By.xpath(productQuantityXP)).getText().trim())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale quantity is incorrect. Actual: " + element.findElement(By.xpath(productQuantityXP)).getText().trim() + " Expected: " + remainingQuantity + "\n";
                    flag.add(false);
                }
            }
        } else {
            flag.add(false);
            actualRS = actualRS + "Nornal product did not display with product: " + productName;
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkDisplayOfFlashSaleEndedDialog() {
        return helper.checkDisplayElement(confirmDialog);
    }

    public Boolean checkContentConfirmEndedFlashSaleDialog() {
        List<String> keyList = new ArrayList<>();
        actualRS = "";
        String currentLanguage = homePage.getCurrentLanguage();
        keyList.add("flashSale");
        keyList.add("flashSaleEndNotification");
        if (checkDisplayOfFlashSaleEndedDialog()) {
            String actualText = driver.findElement(confirmDialog).findElement(By.xpath(dialogContent)).getText();
            return actualText.equals(jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList));
        } else {
            actualRS = "End flash sale notification dialog did not display";
            return false;
        }
    }

    public List<String> getProductFlashSaleInformation(String productName) {
        List<String> productList = new ArrayList<>();
        helper.waitForPresence(flashSaleImageBorder);
        String name = driver.findElement(flashSaleImageBorder).findElement(By.xpath(productNameXP)).getText();
        if (name.equals(productName)) {
            productList.add(driver.findElement(flashSaleImageBorder).findElement(By.xpath(productPriceXP)).getText());
        }
        return productList;
    }

    //checkout action
    public Boolean checkQuantityFlashSale(Map<String, String> productFlashSale) {
        List<Boolean> flag = new ArrayList<>();
        String quantity = "";
        helper.waitForJStoLoad();
        helper.waitForPresence(bannerHeaderCheckout);
        helper.visibleOfLocated(bannerHeaderCheckout);
        List<WebElement> flashSaleProductNameElementList = helper.getElementList(flashSaleProductName);
        List<WebElement> flashSaleProductQuantityElementList = helper.getElementList(flashSaleProductQuantity);
        for (int i = 0; i < flashSaleProductNameElementList.size(); i++) {
            //check flash sale product name
            if (productFlashSale.containsKey(flashSaleProductNameElementList.get(i).getText())) {
                quantity = productFlashSale.get(flashSaleProductNameElementList.get(i).getText());
                System.out.println("Name: " + flashSaleProductNameElementList.get(i).getText() + ", Quantity: " + flashSaleProductQuantityElementList.get(i).getText());
                //check flash sale product quantity
                if (quantity.equals(flashSaleProductQuantityElementList.get(i).getText())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Quantity of " + flashSaleProductNameElementList.get(i).getText() + "is incorrect. Actual: " + flashSaleProductQuantityElementList.get(i).getText() + " Expected: " + quantity + "\n";
                    flag.add(false);
                }
            } else {
                actualRS = actualRS + "Checkout not included " + flashSaleProductQuantityElementList.get(i).getText() + "\n";
                flag.add(false);
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    //TODO debug for issue api loaded slowly
    public void checkoutAction(String currentLanguage, Boolean isEnterAddress, String address, int addressIndex) {
        int count = 5;
        int loopNo = count;
        helper.visibleOfLocated(bannerHeaderCheckout);
        helper.pressPageDownAction();
        try {
            helper.waitForPresence(subTotalAmount);
            helper.visibleOfLocated(subTotalAmount);
            helper.scrollByJS(driver.findElement(subTotalAmount));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.pressEndAction();
        }
        helper.waitForJStoLoad();
        waitTotalLoaded();
        try {
            helper.clickBtn(driver.findElement(completeBtn));
            System.out.println("1");
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.waitForJStoLoad();
            waitTotalLoaded();
            helper.sleep(1);
            try {
                helper.clickByJS(driver.findElement(completeBtn));
                System.out.println("2");
            } catch (Exception ex1) {
                Log.info(ex1.getMessage());
                helper.refreshPage();
                helper.waitForPresence(homePage.footer);
                helper.scrollByJS(driver.findElement(homePage.footer));
                waitTotalLoaded();
                helper.sleep(1);
                helper.waitForPresence(completeBtn);
                helper.waitForJStoLoad();
                try {
                    helper.clickByJS(driver.findElement(completeBtn));
                    System.out.println("3");
                } catch (Exception exception1) {
                    Log.info(exception1.getMessage());
                }
            }
        }
        System.out.println("hello");
        if (isEnterAddress) {
            try {
                homePage.onlyFillDeliveryAddressNoClear(address, addressIndex);
            } catch (Exception exception) {
                System.out.println(exception);
                Log.info(exception.getMessage());
            }
        }
        try {
            try {
                helper.sleep(1);
                helper.getElement(viewOrder).isDisplayed();
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                helper.clickByJS(driver.findElement(completeBtn));
            }
            helper.checkDisplayElement(viewOrder);
            while (!helper.checkDisplayElement(viewOrder) && loopNo > 0) {
                helper.refreshPage();
                try {
                    helper.waitForPresence(homePage.footer);
                    helper.scrollByJS(driver.findElement(homePage.footer));
                } catch (Exception ex2) {
                    Log.info(ex2.getMessage());
                }
                helper.waitForJStoLoad();
                helper.waitForPresence(subTotalAmount);
                waitTotalLoaded();
                helper.sleep(1);
                try {
                    helper.clickByJS(driver.findElement(completeBtn));
                    System.out.println("7");
                } catch (Exception ex1) {
                    Log.info(ex1.getMessage());
                    helper.clickByJS(driver.findElement(completeBtn));
                    System.out.println("8");
                }
                loopNo--;
            }
        } catch (Exception exception) {
            Log.info(exception.getMessage());
        }
        List<String> keyList = new ArrayList<>();
        keyList.add("congratulation");
        loopNo = count;
        try {
            helper.checkDisplayElement(viewOrder);
            helper.waitForTextToBe(driver.findElement(congratulationText), jsonReader.localeReader(currentLanguage, "order", keyList));
            helper.waitForPresence(checkOutPopup);
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            System.out.println(125);
            helper.checkDisplayElement(viewOrder);
            while (!helper.checkDisplayElement(viewOrder) && loopNo > 0) {
                helper.refreshPage();
                try {
                    helper.waitForPresence(homePage.footer);
                    helper.scrollByJS(driver.findElement(homePage.footer));
                } catch (Exception ex2) {
                    Log.info(ex2.getMessage());
                }
                helper.waitForJStoLoad();
                waitTotalLoaded();
                helper.sleep(1);
                try {
                    helper.clickByJS(driver.findElement(completeBtn));
                    System.out.println("8");
                } catch (Exception ex1) {
                    Log.info(ex1.getMessage());
                    System.out.println(124);
                    int loopNo2 = count;
                    helper.checkDisplayElement(viewOrder);
                    while (!helper.checkDisplayElement(viewOrder) && loopNo2 > 0) {
                        helper.refreshPage();
                        try {
                            helper.waitForPresence(homePage.footer);
                            helper.scrollByJS(driver.findElement(homePage.footer));
                        } catch (Exception ex2) {
                            Log.info(ex2.getMessage());
                            System.out.println("catch 1");
                        }
                        helper.waitForJStoLoad();
                        waitTotalLoaded();
                        helper.sleep(1);
                        try {
                            try {
                                helper.clickByJS(driver.findElement(completeBtn));
                                System.out.println("9");
                            } catch (Exception ex2) {
                                Log.info(ex2.getMessage());
                                helper.clickByJS(driver.findElement(completeBtn));
                                System.out.println("10");
                            }
                        } catch (Exception ex2) {
                            Log.info(ex2.getMessage());
                        }
                        loopNo2--;
                    }
                }
                loopNo--;
            }
        }
    }

    public Boolean clickOkayBtn(int dialogIndex) {
        List<WebElement> okayList = helper.getElementList(okayBtn);
        if (okayList.size() > 0) {
            for (WebElement element : okayList) {
                try {
                    helper.waitUtilElementVisible(element);
                    helper.clickBtn(element);
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                    helper.clickByJS(element);
                }
            }
            actualRS = "Dialog display more than 1";
        } else {
            actualRS = "Dialog did not display";
        }
        return (okayList.size() == 1);
    }

    //view order
    public Boolean checkDisplayOfViewOrder() {
        return helper.checkDisplayElement(viewOrder);
    }

    public String viewOrderAfterCheckout() {
        helper.visibleOfLocated(checkOutPopup);
        helper.visibleOfLocated(viewOrder);
        try {
            helper.clickBtn(driver.findElement(viewOrder));
        } catch (Exception ex) {
            Log.info(ex.getMessage());
            helper.clickByJS(driver.findElement(viewOrder));
        }
        System.out.println(driver.getCurrentUrl());
        helper.waitForURLContains("my-profile/2/");
        return helper.getCurrentURL();
    }

    public void clearAllCart() {
        helper.waitForJStoLoad();
        helper.waitForPresence(bannerHeaderCheckout);
        helper.visibleOfLocated(bannerHeaderCheckout);
        List<WebElement> deleteBTNList = helper.getElementList(deleteList);
        if (deleteBTNList.size() == 0) deleteBTNList = helper.getElementList(deleteList);
        int size = deleteBTNList.size();
        while (helper.checkDisplayElement(homePage.cartQuantity)) {
            if (deleteBTNList.size() < 0) break;
            for (int i = 0; i < size; i++) {
                try {
                    helper.waitUtilElementVisible(deleteBTNList.get(0));
                    helper.clickBtn(deleteBTNList.get(0));
                } catch (Exception exception) {
                    Log.info(exception.getMessage());
                    try {
                        helper.clickByJS(deleteBTNList.get(0));
                    } catch (Exception exception1) {
                        Log.info(exception1.getMessage());
                        deleteBTNList = helper.getElementList(deleteList);
                        if (deleteBTNList.size() > 0) helper.clickByJS(deleteBTNList.get(0));
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Log.info(e.getMessage());
                }
                deleteBTNList = helper.getElementList(deleteList);
            }
        }
    }

    private void waitTotalLoaded() {
        helper.waitForJStoLoad();
        int total = 0;
        helper.waitForPresence(subTotalAmount);
        int count = 3, loopNo = 3;
        while (total == 0 && count > 0) {
            total = 0;
            try {
                total = helper.convertToIntegerWithEnd(driver.findElement(subTotalAmount).getText(), "đ", 4);
            } catch (Exception ex2) {
                Log.info(ex2.getMessage());
                helper.refreshPage();
                try {
                    helper.waitForPresence(homePage.footer);
                    helper.scrollByJS(driver.findElement(homePage.footer));
                } catch (Exception ex3) {
                    Log.info(ex3.getMessage());
                    System.out.println("catch");
                }
                helper.waitForJStoLoad();
                total = 0;
                while (total == 0 && loopNo > 0) {
                    total = 0;
                    try {
                        total = helper.convertToIntegerWithEnd(driver.findElement(subTotalAmount).getText(), "đ", 4);
                    } catch (Exception ex4) {
                        Log.info(ex4.getMessage());
                    }
                    loopNo--;
                }
            }
            count--;
        }
    }

    public void clickCompleteBtn(String address, int addressIndex) {
        helper.visibleOfLocated(bannerHeaderCheckout);
        helper.pressPageDownAction();
        try {
            helper.waitForPresence(subTotalAmount);
            helper.scrollByJS(driver.findElement(subTotalAmount));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.pressEndAction();
        }
        helper.waitForJStoLoad();
        helper.waitForPresence(subTotalAmount);
        helper.visibleOfLocated(subTotalAmount);
        helper.waitForJStoLoad();
        int total = 0;
        int loopNo = 3;
        while (total == 0 && loopNo > 0) {
            total = 0;
            try {
                total = helper.convertToIntegerWithEnd(driver.findElement(subTotalAmount).getText(), "đ", 4);
            } catch (Exception ex4) {
                Log.info(ex4.getMessage());
            }
            loopNo--;
        }
        try {
            helper.clickBtn(driver.findElement(completeBtn));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            helper.clickByJS(driver.findElement(completeBtn));
        }
    }

    public Boolean checkDisplayOfNotificationDialog() {
        return helper.checkDisplayElement(confirmDialog);
    }

    public Boolean checkDisplayOfNotificationContent() {
        return helper.checkDisplayElementByElement(driver.findElement(confirmDialog).findElement(By.xpath(dialogContent)));
    }

    public Boolean checkDisplayOfNotificationOkayBtn() {
        return helper.checkDisplayElement(okayBtn);
    }

    public void changeAddress(String address, int addressIndex) {
        helper.waitForPresence(changeAddressBtn);
        helper.visibleOfLocated(changeAddressBtn);
        helper.clickElement(changeAddressBtn);
        homePage.onlyFillDeliveryAddress(address, addressIndex);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Log.info(e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            helper.waitUtilElementVisible(driver.findElement(toastMsg));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            System.out.println("Toast msg did not display");
        }
    }

    public List<String> getFlashSaleKeyList() {
        List<String> keyList = new ArrayList<>();
        keyList.add("flashSale");
        keyList.add("flashSaleEndNotification");
        return keyList;
    }

    public List<String> getOverLimitedKeyList() {
        List<String> keyList = new ArrayList<>();
        keyList.add("flashSale");
        keyList.add("description");
        keyList.add("overLimited");
        return keyList;
    }

    public List<String> getMinimumPurchaseKeyList() {
        List<String> keyList = new ArrayList<>();
        keyList.add("flashSale");
        keyList.add("description");
        keyList.add("minimumPurchaseValue");
        return keyList;
    }

    public List<String> getHasBeenChangedKeyList() {
        List<String> keyList = new ArrayList<>();
        keyList.add("flashSale");
        keyList.add("description");
        keyList.add("hasBeenChanged");
        return keyList;
    }

    public Boolean checkContentDialog(String currentLanguage) {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        List<String> keyList = getFlashSaleKeyList();
        helper.waitForPresence(confirmDialog);
        List<WebElement> dialogList = helper.getElementList(confirmDialog);
        WebElement dialog;
        if (dialogList.size() > 0) {
            if (dialogList.size() > 1) {
                dialog = dialogList.get(1);
                flag.add(false);
                actualRS = actualRS + "Dialog displayed more than 1.\n";
            } else {
                dialog = dialogList.get(0);
                flag.add(true);
            }
            helper.waitUtilElementVisible(dialog);
            if (checkDisplayOfNotificationDialog()) flag.add(true);
            else {
                actualRS = actualRS + "Dialog did not display" + "\n";
                flag.add(false);
            }
            if (checkDisplayOfNotificationContent()) flag.add(true);
            else {
                actualRS = actualRS + "Dialog did not display" + "\n";
                flag.add(false);
            }
            if (checkDisplayOfNotificationOkayBtn()) flag.add(true);
            else {
                actualRS = actualRS + "Dialog did not display" + "\n";
                flag.add(false);
            }
            if (helper.checkTextWithLanguage(currentLanguage, dialog.findElement(By.xpath(dialogContent)), "text", flashSaleDataTest.FLASH_SALE_PAGE, keyList))
                flag.add(true);
            else {
                actualRS = actualRS + "Dialog content is incorrect. Actual: " + dialog.findElement(By.xpath(dialogContent)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, flashSaleDataTest.FLASH_SALE_PAGE, keyList) + "\n";
                flag.add(false);
            }
        } else {
            flag.add(false);
            actualRS = "Dialog did not display";
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkContentDialog(String currentLanguage, String page, List<String> keyList) {
        actualRS = "";
        List<Boolean> flag = new ArrayList<>();
        helper.waitForPresence(confirmDialog);
        List<WebElement> dialogList = helper.getElementList(confirmDialog);
        WebElement dialog;
        if (dialogList.size() > 0) {
            if (dialogList.size() > 1) {
                dialog = dialogList.get(1);
                flag.add(false);
                actualRS = actualRS + "Dialog displayed more than 1.\n";
            } else {
                dialog = dialogList.get(0);
                flag.add(true);
            }
            helper.waitUtilElementVisible(dialog);
            if (checkDisplayOfNotificationDialog()) flag.add(true);
            else {
                actualRS = actualRS + "Dialog did not display" + "\n";
                flag.add(false);
            }
            if (checkDisplayOfNotificationContent()) flag.add(true);
            else {
                actualRS = actualRS + "Dialog content did not display" + "\n";
                flag.add(false);
            }
            if (checkDisplayOfNotificationOkayBtn()) flag.add(true);
            else {
                actualRS = actualRS + "Dialog button did not display" + "\n";
                flag.add(false);
            }
            if (helper.checkTextWithLanguage(currentLanguage, dialog.findElement(By.xpath(dialogContent)), "text", page, keyList))
                flag.add(true);
            else {
                actualRS = actualRS + "Dialog content is incorrect. Actual: " + dialog.findElement(By.xpath(dialogContent)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, page, keyList) + "\n";
                flag.add(false);
            }
            keyList.clear();
            keyList.add("okay");
            if (helper.checkTextWithLanguage(currentLanguage, dialog.findElement(By.xpath(okayBtnDialog)), "text", dataTest.PAGE, keyList))
                flag.add(true);
            else {
                actualRS = actualRS + "Dialog button is incorrect. Actual: " + dialog.findElement(By.xpath(okayBtnDialog)).getText() + " Expected: " + jsonReader.localeReader(currentLanguage, dataTest.PAGE, keyList) + "\n";
                flag.add(false);
            }
        } else {
            actualRS = actualRS + "Dialog did not display.\n";
            flag.add(false);
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean checkPriceOfProduct(boolean isFlashSale, FlashSaleProduct flashSaleProduct) {
        String productName = flashSaleProduct.getName();
        String originalPrice = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
        String flashSalePrice = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
        helper.waitForPresence(productDetailsSection);
        actualRS = "";
        expectedRS = "";
        List<WebElement> productList = new ArrayList<>();
        List<String> productPrice = new ArrayList<>();
        String price = "";
        if (isFlashSale) {
            productList = helper.getElementList(flashSaleImageBorder);
            expectedRS = flashSalePrice;
        } else {
            productList = helper.getElementList(notFlashSaleImage);
            expectedRS = originalPrice;
        }
        for (WebElement element : productList) {
            helper.scrollByJS(element);
            if (productName.equals(element.findElement(By.xpath(productNameXP)).getText()))
                price = element.getText();
        }
        actualRS = "Price is wrong. Actual: " + productPrice + " Expected: " + expectedRS;
        return price.equals(expectedRS);
    }

//    /**
//     * apply for one flash sale product
//     *
//     * @param isFlashSale
//     * @param flashSaleProduct
//     * @return
//     */
//    public Boolean checkPriceOfProduct(boolean isFlashSale, FlashSaleProduct flashSaleProduct) {
//        String price = "";
//        helper.waitForPresence(productDetailsSection);
//        actualRS = "";
//        WebElement product;
//        String productPrice = "";
//        if (isFlashSale) {
//            product = helper.getElement(flashSaleImageBorder);
//            price = helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice());
//        } else {
//            product = helper.getElement(notFlashSaleImage);
//            price = helper.formatCurrencyToThousand(flashSaleProduct.getPrice());
//        }
//        helper.scrollByJS(product);
//        if (price.equals(product.findElement(By.xpath(productPriceXP)).getText())) return true;
//        else {
//            actualRS = actualRS + "Actual: " + product.findElement(By.xpath(productPriceXP)).getText() + " Expected: " + price;
//            return false;
//        }
//    }

    private void clickUpQuantityWithElement(WebElement product, int addProduct) {
        helper.waitUtilElementVisible(product);
        int oldQuantity = Integer.parseInt(product.findElement(By.xpath(productQuantityXP)).getText());
        int total = oldQuantity + addProduct;
        while (oldQuantity < total) {
            helper.clickBtn(product.findElement(By.xpath(upQuantityBtnXP)));
            oldQuantity++;
        }
    }

    private void clickDownQuantityWithElement(WebElement product, int minusProduct) {
        helper.waitUtilElementVisible(product);
        int oldQuantity = Integer.parseInt(product.findElement(By.xpath(productQuantityXP)).getText());
        int total = oldQuantity - minusProduct;
        while (oldQuantity > total) {
            helper.clickBtn(product.findElement(By.xpath(downQuantityBtnXP)));
            oldQuantity--;
        }
    }

    /**
     * pply for one product
     *
     * @param addProduct
     * @param maxinmumLimit
     * @param cartQuantity
     */
    public void upQuantityWithElementProductFlashSale(int addProduct, int maxinmumLimit, int cartQuantity) {
        helper.waitForPresence(bannerHeaderCheckout);
        WebElement product = helper.getElement(flashSaleImageBorder);
        List<WebElement> productList = new ArrayList<>();
        int totalQuantity = cartQuantity + addProduct;
        product = helper.getElement(flashSaleImageBorder);
        clickUpQuantityWithElement(product, addProduct);
        if (maxinmumLimit < totalQuantity) {
            helper.waitForPresence(notFlashSaleImage);
            product = helper.getElement(notFlashSaleImage);
        } else {
            product = helper.getElement(flashSaleImageBorder);
        }
    }

    public void upQuantityWithElementProduct(int addProduct) {
        helper.waitForPresence(bannerHeaderCheckout);
        WebElement product;
        try {
            product = helper.getElement(notFlashSaleImage);
        } catch (Exception exc) {
            Log.info(exc.getMessage());
            product = helper.getElement(flashSaleImageBorder);
        }
        List<WebElement> productList = new ArrayList<>();
        clickUpQuantityWithElement(product, addProduct);
    }

    public void downQuantityWithElementProduct(int minusProduct) {
        helper.waitForPresence(bannerHeaderCheckout);
        WebElement product;
        try {
            product = helper.getElement(notFlashSaleImage);
        } catch (Exception exc) {
            Log.info(exc.getMessage());
            product = helper.getElement(flashSaleImageBorder);
        }
        List<WebElement> productList = new ArrayList<>();
        clickDownQuantityWithElement(product, minusProduct);
    }

    public Boolean checkMaximumPurchase(Boolean isFlashSale, int minimumPurchase, int maximumLimit, int cartQuantity, int originalPrice, int flashSalePrice) {
        helper.waitForPresence(subTotalAmount);
        helper.visibleOfLocated(subTotalAmount);
        String totalSTR = helper.getText(subTotalAmount);
        int total = helper.convertToIntegerWithEnd(totalSTR, "đ", 4);
        totalOriginalPrice = cartQuantity * originalPrice - originalPrice + flashSalePrice;
        while (totalOriginalPrice < minimumPurchase) {
            upQuantityWithElementProduct(1);
            helper.sleep(3);
            helper.waitForJStoLoad();
            totalSTR = helper.getText(subTotalAmount);
            total = helper.convertToIntegerWithEnd(totalSTR, "đ", 4);
            cartQuantity++;
            totalOriginalPrice = cartQuantity * originalPrice - originalPrice + flashSalePrice;
        }
        cartQuantityNew = cartQuantity;
        helper.waitForJStoLoad();
        helper.waitForPresence(flashSaleImageBorder);
        return checkValueOfSubTotalQuantity(helper.formatCurrencyToThousandWithCurrencyCode(totalOriginalPrice));
    }

    public int getCartQuantity() {
        return cartQuantityNew;
    }

    public int gettotalOriginalPrice() {
        return totalOriginalPrice;
    }

    /**
     * apply for one flash sale product
     *
     * @return if flash sale border is not display -> true
     */
    public Boolean decreaseFlashSaleProductQuantity() {
        helper.waitForPresence(flashSaleImageBorder);
        helper.visibleOfLocated(flashSaleImageBorder);
        String quantityStr = driver.findElement(flashSaleImageBorder).findElement(By.xpath(productQuantityXP)).getText();
        int quantity = Integer.parseInt(quantityStr);
        while (quantity > 0) {
            downQuantityWithElementProduct(1);
            helper.sleep(3);
            helper.waitForJStoLoad();
            try {
                quantityStr = driver.findElement(flashSaleImageBorder).findElement(By.xpath(productQuantityXP)).getText();
            } catch (Exception exception) {
                Log.info(exception.getMessage());
                break;
            }
            quantity = Integer.parseInt(quantityStr);
        }
        helper.waitForJStoLoad();
        helper.waitForPresence(flashSaleImageBorder);
        actualRS = "flash sale product still display";
        return !checkDisplayOfFlashSaleBorder();
    }

    public Boolean checkQuantity(Boolean isFlashSale, int quantity) {
        helper.waitForJStoLoad();
        WebElement element;
        if (isFlashSale) {
            helper.waitForPresence(flashSaleImageBorder);
            helper.waitForPresence(flashSaleImageBorder);
            helper.visibleOfLocated(flashSaleImageBorder);
            element = driver.findElement(flashSaleImageBorder);
        } else {
            helper.waitForPresence(notFlashSaleImage);
            helper.waitForPresence(notFlashSaleImage);
            helper.visibleOfLocated(notFlashSaleImage);
            element = driver.findElement(notFlashSaleImage);
        }
        try {
            actualRS = "Flash sale quantity is incorrect. Actual:" + element.findElement(By.xpath(productQuantityXP)).getText();
            return (element.findElement(By.xpath(productQuantityXP)).getText().equals(String.valueOf(quantity)));
        } catch (Exception exception) {
            Log.info(exception.getMessage());
            actualRS = exception.getMessage() + "\n\nFlash sale product did not display.";
            return false;
        }
    }

    //language
    public Boolean checkLanguageOfDialog(String page, List<String> keyList) {
        List<Boolean> flag = new ArrayList<>();
        String currentLanguage = homePage.getCurrentLanguage();
        String checkedLanguage = currentLanguage;
        int index = 0;
        String language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
        List<WebElement> languageList = helper.changeLanguage(homePage.languageSwitch, homePage.languageOptions);
        helper.waitUtilElementVisible(driver.findElement(confirmDialog));
        if (languageList.get(0).getText().equals(language)) index = 1;
        else index = 0;
        homePage.clickLanguage();
        //check default language
        flag.add(checkContentDialog(currentLanguage, page, keyList));
        languageList = helper.changeLanguage(homePage.languageSwitch, homePage.languageOptions);
        for (int i = index; i < languageList.size(); i++) {
            try {
                helper.waitForPresence(confirmDialog);
                helper.waitUtilElementVisible(driver.findElement(confirmDialog));
                helper.waitUtilElementVisible(languageList.get(i));
            } catch (Exception exception) {
                Log.info(exception.getMessage());
            }
            helper.clickBtn(languageList.get(i));
            helper.waitForJStoLoad();
            flag.add(checkContentDialog(currentLanguage, page, keyList));
            helper.refreshPage();
            helper.pressPageUpAction();
            helper.changeLanguage(homePage.languageSwitch, homePage.languageOptions);
            helper.waitUtilElementVisible(driver.findElement(confirmDialog));
            language = homeDataTest.LANGUAGE_MAP.get(checkedLanguage.toUpperCase());
            languageList = helper.getElementList(homePage.languageOptions);
            if (!languageList.get(i).getText().equals(language)) {
                helper.waitUtilElementVisible(languageList.get(i));
                helper.clickBtn(languageList.get(i));
                helper.waitForJStoLoad();
                i++;
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    //edit order
    public Boolean openEditOrderFromCheckout() {
        helper.waitForPresence(productDetailsSection);
        helper.visibleOfLocated(productDetailsSection);
        helper.visibleOfLocated(productImage);
        try {
            helper.clickBtn(driver.findElement(productImage));
        } catch (ElementClickInterceptedException e) {
            Log.info(e.getMessage());
            helper.clickByJS(driver.findElement(productImage));
        }
        return commonPagesTheme1().editOrder.checkDisplayOfEditCart();
    }
}
