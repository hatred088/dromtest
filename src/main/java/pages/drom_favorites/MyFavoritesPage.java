package pages.drom_favorites;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.base.BasePage;

import java.util.List;

public class MyFavoritesPage extends BasePage {
    public MyFavoritesPage(WebDriver driver) {
        super(driver);
    }

    private final By aFirstFavorite = By.cssSelector(".bull-item-content__subject-container>a");
    private final By allRemoveButtons = By.cssSelector("a.removeBookmark");
    private final By buttonHeaderSales = By.cssSelector("[data-ga-stats-name='topmenu_sales']");

    @Step("Переход на страницу \"Автомобили\"")
    public MyFavoritesPage goToDromSalesPage (){
        driver.findElement(buttonHeaderSales).click();
        return this;
    }


    @Step("Проверка совпадения ссылок избранного товара")
    public MyFavoritesPage checkFavoriteLinksMatch (String hrefFirstSale){
        Assert.assertEquals(driver.findElement(aFirstFavorite).getAttribute("href"), hrefFirstSale);
        return this;
    }

    @Step("Удаление всех избранных товаров")
    public MyFavoritesPage deleteAllFavorites (){
        List<WebElement> removeButtons = driver.findElements(allRemoveButtons);
        for (WebElement e : removeButtons) {
            e.click();
        }
        return this;
    }


}


