package tests.base;

import common.CommonActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.base.BasePage;
import pages.drom_auth.DromAuthPage;
import pages.drom_favorites.MyFavoritesPage;
import pages.drom_sales.DromSalesPage;
import pages.listing.SalesListingPage;

import static common.Config.CLEAR_COOKIES_AND_STORAGE;
import static common.Config.HOLD_BROWSER_OPEN;

// Базовый тестовый класс


public class BaseTest {

    protected WebDriver driver = CommonActions.getDriver();
    protected BasePage basePage = new BasePage(driver);
    protected DromSalesPage dromSalesPage  = new DromSalesPage(driver);
    protected SalesListingPage salesListingPage = new SalesListingPage(driver);
    protected DromAuthPage dromAuthPage = new DromAuthPage(driver);
    protected MyFavoritesPage myFavoritesPage = new MyFavoritesPage(driver);



    @AfterTest(description = "Очистка cookies и localStorage")
    public void clearCookiesAndLocalStorage(){
        if (CLEAR_COOKIES_AND_STORAGE) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }

    @AfterSuite(description = "Выход из браузера")
    public void close(){
        if (HOLD_BROWSER_OPEN) {
            driver.quit();
        }
    }

}
