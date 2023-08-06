package pages.listing;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.base.BasePage;

import java.util.List;

// Страница поиска продаж по фильтрам
public class SalesListingPage extends BasePage {

    public SalesListingPage(WebDriver driver) {
        super(driver);
    }

    private final By nextPage = By.cssSelector("[data-ftid='component_pagination-item-next']");

    private final By cardSale = By.xpath("//span[@data-ftid='bull_title']/..");

    @Step("Проверка непроданных авто на странице")
    public SalesListingPage checkSoldCars(){
        List<WebElement> cards = driver.findElements(cardSale);
        for (WebElement e : cards) {
            Assert.assertFalse(e.getCssValue("text-decoration").contains("line-through"));
        }
        return this;
    }

    @Step("Проверка года выпуска авто")
    public SalesListingPage checkYearCars(){
        List<WebElement> cards = driver.findElements(cardSale);
        for (WebElement e : cards) {
            Assert.assertTrue(Integer.parseInt(e.getText().split(", ")[1])>=2007, "Ошибка! Год авто меньше заданного");
        }
        return this;
    }

    @Step("Переход на следующую страницу продаж авто")

    public SalesListingPage clickNextPage(){
        driver.findElement(nextPage).click();
        return this;
    }




}
