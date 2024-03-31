package com.fnb.web.store.theme1.pages.product_list;

import com.fnb.utils.api.admin.helpers.APIAminService;
import com.fnb.utils.api.admin.helpers.JsonAPIAdminReader.*;
import com.fnb.utils.helpers.Helper;
import com.fnb.utils.helpers.Log;
import com.fnb.web.setup.Setup;
import com.fnb.web.store.theme1.pages.checkout.CheckoutDataTest;
import com.fnb.web.store.theme1.pages.login.CheckOutLoginPage;
import com.fnb.web.store.theme1.pages.login.DataTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductListPage extends Setup {
    public Helper helper;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public APIAminService apiAminService;
    private WebDriver driver;
    private CheckOutLoginPage checkOutLoginPage;
    private CheckoutDataTest checkoutDataTest;
    private DataTest loginDataTest;
    public static String actualRS = "";
    private By banner = By.xpath("//div[@class=\"banner-top-product-list\"]");
    //Message
    public static By addProductSuccessToast = By.xpath("//div[@class=\"ant-notification-notice-message\"]");
    private String categoryId = "list-products-section-id-";
    private String categoryNameXP = ".//span[contains(@class,\"title-name\")]"; //concat with ID
    private String categoryQuantityXP = ".//span[contains(@class,\"quantity-products\")]"; //concat with ID
    private String categoryProductContainerXP = "./div[contains(@class,\"product-list__container\")]";
    private String categoryProductCartListXP = "./div[contains(@class,\"product-list__container\")]/div";
    //product information - get from productCartList
    private String productNameXP = ".//div[contains(@class,\"product-card__title\")]";
    private String productPriceXP = ".//span[contains(@class,\"product-card__price-sell\")]";
    private String productThumnailXP = ".//img[contains(@class,\"product-card__img\")]";
    private String productAddtoCartXP = ".//div[contains(@class,\"product-card__btn-add-to-cart\")]";
    //flash sale
    private String flashSaleLogoXP = ".//img[contains(@class,\"product-card__flashsale-bottom\")]";
    private String flashSaleDiscountPercentXP = ".//div[contains(@class,\"product-card__percent-discount\")]";
    private String flashSaleOriginalPriceXP = ".//span[contains(@class,\"product-card__price-discount\")]";
    private String flashSaleBorderXP = ".//img[contains(@class,\"product-card__flashsale-img\")]";

    public ProductListPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
    }

    private String getCategoryIdByProductName(String productName) {
        return categoryId + apiAminService.getCategoryIdByProductId(productName);
    }

    private WebElement getCategoryElement(String productName) {
        return driver.findElement(By.id(getCategoryIdByProductName(productName)));
    }

    public void scrollToCategoryByProductName(String productName) {
        String element = getCategoryIdByProductName(productName);
        helper.waitForPresence(By.id(element));
        helper.visibleOfLocated(By.id(element));
        try {
            helper.scrollByJS(driver.findElement(By.id(element)));
        } catch (Exception e) {
            Log.info(e.getMessage());
            helper.actionScrollToElement(driver.findElement(By.id(element)));
        }
        helper.waitForPresence(By.id(element));
    }

    //flash sale
    private Boolean checkDisplayFlashSale(Boolean isFlashSale, WebElement productCart, FlashSaleProduct flashSaleProduct) {
        List<Boolean> flag = new ArrayList<>();
        actualRS = "";
        if (isFlashSale) {
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleBorderXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale border did not display\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale border did not display\n";
                flag.add(false);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleLogoXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale logo did not display\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale logo did not display\n";
                flag.add(false);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleDiscountPercentXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(true);
                    String discountPercent = helper.formatPercentDiscount(helper.calculateDiscountPercent(flashSaleProduct.getPrice(), flashSaleProduct.getFlashSalePrice()));
                    if (element.getText().equals(discountPercent)) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale discount displayed incorrect! Actual: " + element.getText() + " Expected: " + discountPercent + "\n";
                        flag.add(false);
                    }
                } else {
                    actualRS = actualRS + "Flash sale discount tag did not display\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale discount tag did not display\n";
                flag.add(false);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(productNameXP));
                if (element.getText().equals(flashSaleProduct.getName())) {
                    flag.add(true);
                } else {
                    actualRS = actualRS + "Flash sale name displayed incorrect! Actual: " + element.getText() + " Expected: " + flashSaleProduct.getName() + "\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale name did not display\n";
                flag.add(false);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(productPriceXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(true);
                    if (element.getText().equals(helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice()))) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Flash sale price displayed incorrect! Actual: " + element.getText() + " Expected: " + helper.formatCurrencyToThousand(flashSaleProduct.getFlashSalePrice()) + "\n";
                        flag.add(false);
                    }
                } else {
                    actualRS = actualRS + "Flash sale price did not display\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale price did not display\n";
                flag.add(false);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleOriginalPriceXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(true);
                    if (element.getText().equals(helper.formatCurrencyToThousand(flashSaleProduct.getPrice()))) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Original price displayed incorrect! Actual: " + element.getText() + " Expected: " + helper.formatCurrencyToThousand(flashSaleProduct.getPrice()) + "\n";
                        flag.add(false);
                    }
                } else {
                    actualRS = actualRS + "Flash sale original price did not display\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale original price did not display\n";
                flag.add(false);
            }
        } else {
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleBorderXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(false);
                    actualRS = actualRS + "Flash sale border did not display\n";
                } else {
                    flag.add(true);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.info(noSuchElementException.getMessage());
                flag.add(true);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleLogoXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(false);
                    actualRS = actualRS + "Flash sale logo did not display\n";
                } else {
                    flag.add(true);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.info(noSuchElementException.getMessage());
                flag.add(true);
            }
            try {
                WebElement element = productCart.findElement(By.xpath(flashSaleDiscountPercentXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(false);
                    actualRS = actualRS + "Flash sale discount tag did not display\n";
                } else {
                    flag.add(true);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.info(noSuchElementException.getMessage());
                flag.add(true);
            }
            String productName = flashSaleProduct.getName().replaceAll("\\s*\\(.*\\)", "");
            String productPrice = apiAminService.getFirstProductPriceByName(productName);
            try {
                WebElement element = productCart.findElement(By.xpath(productPriceXP));
                if (helper.checkDisplayElementByElement(element)) {
                    flag.add(true);
                    if (element.getText().equals(productPrice)) {
                        flag.add(true);
                    } else {
                        actualRS = actualRS + "Original price displayed incorrect! Actual: " + element.getText() + " Expected: " + productPrice + "\n";
                        flag.add(false);
                    }
                } else {
                    actualRS = actualRS + "Flash sale original price did not display\n";
                    flag.add(false);
                }
            } catch (NoSuchElementException noSuchElementException) {
                Log.error(noSuchElementException.getMessage());
                actualRS = actualRS + "Flash sale original price did not display\n";
                flag.add(false);
            }
        }
        if (flag.contains(false)) return false;
        else return true;
    }

    /**
     * Not do for full variations
     *
     * @param flashSaleName
     * @param isFlashSale
     * @return
     */
    public Boolean checkProductFlashSaleByProductName(String flashSaleName, Boolean isFlashSale) {
        List<Boolean> flag = new ArrayList<>();
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleName);
        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
        List<WebElement> productCartElementList = new ArrayList<>();
        WebElement category;
        String productNameText = "";
        System.out.println("size: " + flashSaleProductList.size());
        for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
            productNameText = flashSaleProduct.getName().replaceAll("\\s*\\(.*\\)", "");
            scrollToCategoryByProductName(productNameText);
            category = getCategoryElement(productNameText);
            productCartElementList = category.findElements(By.xpath(categoryProductCartListXP));
            for (WebElement productCart : productCartElementList) {
                if (productCart.getText().contains(productNameText)) {
                    flag.add(checkDisplayFlashSale(isFlashSale, productCart, flashSaleProduct));
                    break;
                }
            }
        }
        if (flag.size() != flashSaleProductList.size()) flag.add(false);
        if (flag.contains(false)) return false;
        else return true;
    }

    public Boolean clickFlashSaleProductByName(String flashSaleName) {
        String url = helper.getCurrentURL();
        List<Boolean> flag = new ArrayList<>();
        FlashSale flashSale = apiAminService.getFlashSaleByIdForDetailPage(flashSaleName);
        List<FlashSaleProduct> flashSaleProductList = flashSale.getFlashSaleProduct();
        List<WebElement> productCartElementList = new ArrayList<>();
        List<String> productNameList = new ArrayList<>();
        for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
            productNameList.add(flashSaleProduct.getName());
        }
        WebElement category;
        String productNameText = "";
        for (FlashSaleProduct flashSaleProduct : flashSaleProductList) {
            productNameText = flashSaleProduct.getName().replaceAll("\\s*\\(.*\\)", "");
            scrollToCategoryByProductName(productNameText);
            category = getCategoryElement(productNameText);
            productCartElementList = category.findElements(By.xpath(categoryProductCartListXP));
            for (int i = 0; i < productCartElementList.size(); i++) {
                if (productCartElementList.get(i).getText().contains(productNameText)) {
                    //click add to cart
                    try {
                        helper.scrollByJS(productCartElementList.get(i));
                        helper.actionHover(productCartElementList.get(i));
                        helper.waitUtilElementVisible(productCartElementList.get(i).findElement(By.xpath(productAddtoCartXP)));
                        helper.clickBtn(productCartElementList.get(i).findElement(By.xpath(productAddtoCartXP)));
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        helper.clickByJS(productCartElementList.get(i).findElement(By.xpath(productAddtoCartXP)));
                    }
                    helper.waitForPresence(addProductSuccessToast);
                    try {
                        helper.waitUtilElementVisible(helper.getElement(addProductSuccessToast));
                    } catch (Exception exception) {
                        Log.info(exception.getMessage());
                        System.out.println("Add successful toast message did not display");
                    }
                    //check cart
                    flag.add(commonPagesTheme1().homePage.checkCartWhenFlashSale(flashSaleProduct.getName(), true));
                    //clear cart
                    commonPagesTheme1().homePage.checkoutThenClearCartWithoutLogin();
                    //product list page
                    helper.navigateTo(url);
                    break;
                }
            }
        }
        if (flag.size() != flashSaleProductList.size()) flag.add(false);
        if (flag.contains(false)) return false;
        else return true;
    }
}
