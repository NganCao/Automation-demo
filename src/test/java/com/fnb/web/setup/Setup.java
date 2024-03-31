package com.fnb.web.setup;

import com.fnb.drivermanager.DriverFactory;
import com.fnb.utils.api.admin.helpers.APIAminService;
import com.fnb.utils.api.pos.helpers.APIPosService;
import com.fnb.utils.helpers.DataReader;
import com.fnb.utils.helpers.HelperListener;
import com.fnb.utils.helpers.JsonReader;
import com.fnb.web.admin.pages.PagesAdminSetup;
import com.fnb.web.pos.pages.PagesPosSetup;
import com.fnb.web.store.PagesStoreSetup;
import com.fnb.web.store.theme1.pages.CommonPagesTheme1;
import com.fnb.web.store.theme2.pages.CommonPagesTheme2;
import io.restassured.response.Response;
import dataObject.Inventory.Material;
import dataObject.Inventory.MaterialCategory;
import dataObject.Inventory.Supplier;
import dataObject.Product.*;
import dataObject.Store.Branch;
import dataObject.Store.Tax;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;

public class Setup {
    public WebDriver driver;
    public static Robot robot;
    public static JsonReader.ConfigObject configObject;
    public static String baseUrl = null;
    public static Response response = null;
    public static APIPosService apiPosService;
    public static APIAminService apiAminService;
    public static JsonReader.ConfigObject configObjectPos;
    public static Product productData;
    public static ProductCategory productCategoryData;
    public static Options optionData;
    public static Material materialData;
    public static Topping toppingData;
    public static SpecificCombo specificComboData;
    public static String getPosPlatform = "pos";
    public static Tax taxData;
    public static Branch branchData;
    public static MaterialCategory materialCategoryData;
    public static Supplier supplierData;
    public static ThreadLocal<CommonPagesTheme1> commonPagesTheme1 = new ThreadLocal<>();
    public static ThreadLocal<CommonPagesTheme2> commonPagesTheme2 = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver;
    }

    public CommonPagesTheme1 commonPagesTheme1() {
        return commonPagesTheme1.get();
    }

    public CommonPagesTheme2 commonPagesTheme2() {
        return commonPagesTheme2.get();
    }

    public void setupDriver(String platform, String theme) throws AWTException {
        configObjectPos = JsonReader.configObject(getPosPlatform, theme);
        configObject = JsonReader.configObject(platform, theme);
        String browserType = JsonReader.configObject(platform, theme).getBrowser();
        driver = DriverFactory.initDriver(browserType);
        commonPagesTheme1.set(new CommonPagesTheme1(driver));
        commonPagesTheme2.set(new CommonPagesTheme2(driver));
        driver.manage().window().maximize();
        robot = new Robot();
        apiAminService = new APIAminService(driver, platform, theme, configObject.getUrlBase());
        apiPosService = new APIPosService(driver, platform, theme, configObject.getUrlBase());
        materialData = DataReader.read(Material.class);
        productData = DataReader.read(Product.class);
        productCategoryData = DataReader.read(ProductCategory.class);
        optionData = DataReader.read(Options.class);
        toppingData = DataReader.read(Topping.class);
        specificComboData = DataReader.read(SpecificCombo.class);
        taxData = DataReader.read(Tax.class);
        branchData = DataReader.read(Branch.class);
        materialCategoryData = DataReader.read(MaterialCategory.class);
        supplierData = DataReader.read(Supplier.class);
    }

    public static String getOsName() {
        String osName = System.getProperty("os.name").toLowerCase();
        // Windown: os = win, Linux: os = nix || nux, macOS: os = mac
        return osName;
    }

    public PagesAdminSetup navigateToAdminPage() throws IOException {
        baseUrl = configObject.getUrlBase();
        driver.get(baseUrl);
        return new PagesAdminSetup(driver);
    }

    public PagesPosSetup navigateToPOSPage() throws IOException {
        baseUrl = configObject.getUrlBase();
        return new PagesPosSetup(driver);
    }

    /**
     * store web THEME 1
     *
     * @return
     * @throws IOException
     */
    public PagesStoreSetup navigateToStorePage() {
        baseUrl = configObject.getUrlBase();
        driver.get(baseUrl);
        return new PagesStoreSetup(driver);
    }

    public void gobacktoHomePage() {
        baseUrl = configObject.getUrlBase();
        driver.navigate().to(baseUrl);
    }

    public void tearDownDriver() {
        driver.quit();
    }

}
