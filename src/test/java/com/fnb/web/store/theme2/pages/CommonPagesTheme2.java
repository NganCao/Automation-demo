package com.fnb.web.store.theme2.pages;
import com.fnb.utils.helpers.Helper;
import com.fnb.web.store.theme2.pages.home.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

import static com.fnb.web.setup.Setup.configObject;

public class CommonPagesTheme2 {
    public Helper helper;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public HomePage homePage;
    public CommonPagesTheme2(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getTimeOut()));
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        helper = new Helper(driver, wait, actions);
        this.driver = driver;
        homePage = new HomePage(driver);
    }
}
