package pages.drom_sales;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.base.BasePage;


import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// Страница продаж
public class DromSalesPage extends BasePage {

    public DromSalesPage(WebDriver driver) {
        super(driver);
    }

    private final By brandAutocomplete = By.cssSelector("input[placeholder='Марка']");
    private final By brandToyota = By.xpath("//div[@role = 'option' and contains(text(), 'Toyota')]");
    private final By modelAutocomplete = By.cssSelector("input[placeholder='Модель']");
    private final By modelHarrier = By.xpath("//div[@role = 'option' and contains(text(), 'Harrier')]");
    private final By fuelDropdown = By.xpath("//button[@data-ftid='component_select_button' and contains(text(), 'Топливо')]");
    private final By fuelGibrid = By.xpath("//div[@role = 'option' and contains(text(), 'Гибрид')]");
    private final By checkboxUnsold = By.cssSelector("[for='sales__filter_unsold']");
    private final By buttonAdvancedSearch = By.cssSelector("[data-ftid='sales__filter_advanced-button']");
    private final By inputMileageFrom = By.cssSelector("[data-ftid='sales__filter_mileage-from']");
    private final By dropdownYearFrom = By.xpath("//button[text() = 'Год от']");
    private final By dropdownYearTo = By.cssSelector("[data-ftid='sales__filter_year-to']");
    private final By yearTo2007 = By.xpath("//button[text() = 'до']/../following-sibling::div[@role='listbox'] / div[@role = 'option' and text() = 2007]");
    private final By yearFrom2007 = By.xpath("//button[text() = 'Год от']/../following-sibling::div[@role='listbox'] / div[@role = 'option' and text() = 2007]");
    private final By buttonApplyFilters = By.xpath("//div[text()='Показать']");
    private final By buttonToAuth = By.cssSelector("[data-ga-stats-name='auth_block_login']");
    private final By buttonFirstSaleFavorite = By.xpath("(//div[@class='css-1rozdag'])[1]");
    private final By aFirstSale = By.xpath("(//a[@data-ftid='bulls-list_bull'])[1]");
    private final By buttonMyFavorites = By.xpath("(//a[@data-ga-stats-name='topmenu_accountFavorite'])[2]");
    private String hrefFirstSale;
    private final By buttonAnotherCity = By.cssSelector("[data-ftid='sales_search-location-picker_geoOverCity']");
    private final By inputFindRegion = By.cssSelector("input[placeholder='поиск города, региона']");
    private final By allBrandCount = By.xpath("//span[@data-ftid='component_cars-list-item_counter']");
    private final By allResults = By.cssSelector("[data-ftid='component_cars-list_expand-control']");
    private final By allBrandNames = By.xpath("//span[@data-ftid='component_cars-list-item_counter']/preceding-sibling::span");



    @Step("Нажатие на кнопку \"Расширенный поиск\"")
    public DromSalesPage clickAdvancedSearch(){
        driver.findElement(buttonAdvancedSearch).click();
        return this;
    }

    @Step("Установка заданных фильтров")
    public DromSalesPage findFilters(){
        driver.findElement(brandAutocomplete).click();
        driver.findElement(brandToyota).click();


        WebElement autocompleteModel = driver.findElement(modelAutocomplete);
        waitElementIsVisible(autocompleteModel).click();
        autocompleteModel.sendKeys("Harrier");

        driver.findElement(modelHarrier).click();

        driver.findElement(fuelDropdown).click();
        WebElement gibridFuel = driver.findElement(fuelGibrid);
        waitElementIsVisible(gibridFuel).click();

        driver.findElement(checkboxUnsold).click();

        WebElement mileageInputFrom = driver.findElement(inputMileageFrom);
        mileageInputFrom.click();
        mileageInputFrom.sendKeys("1");

        driver.findElement(dropdownYearFrom).click();
        WebElement fromYear2007 = driver.findElement(yearFrom2007);
        waitElementIsVisible(fromYear2007).click();
        return this;
    }

    @Step("Нажатие на кнопку \"Показать\"")
    public DromSalesPage applyFilters(){
        WebElement findBtn = driver.findElement(buttonApplyFilters);
        waitElementIsVisible(findBtn).click();
        return this;
    }

    @Step("Переход на страницу авторизации")
    public DromSalesPage goToAuth(){
        driver.findElement(buttonToAuth).click();
        return this;
    }

    @Step("Добавление в избранное первого элемента на странице продаж")
    public DromSalesPage addFirstSaleToFavorite(){
        driver.findElement(buttonFirstSaleFavorite).click();
        hrefFirstSale = driver.findElement(aFirstSale).getAttribute("href");
        return this;
    }

    @Step("Получение ссылки первого элемента на странице продаж")
    public String getHrefFirstSale() {
        return hrefFirstSale;
    }

    @Step("Переход на страницу с избранными товарами")
    public DromSalesPage goToFavorites(){
        driver.findElement(buttonMyFavorites).click();
        return this;
    }

    @Step("Выбор определенного региона/города в фильтрации")
    public DromSalesPage choiceRegion (String region){
        driver.findElement(buttonAnotherCity).click();
        WebElement findRegionInput = driver.findElement(inputFindRegion);
        waitElementIsVisible(findRegionInput).sendKeys(region, Keys.ENTER);
        return this;
    }

    @Step ("Вывод топ 20 по кол-ву объявлений")
    public DromSalesPage top20Sales (){
        WebElement resultsAll = driver.findElement(allResults);
        waitElementIsVisible(resultsAll).click();

        List<WebElement> elementCount = driver.findElements(allBrandCount);
        List<WebElement> elementNames = driver.findElements(allBrandNames);

        Map<String, Integer> keyValueMap = new HashMap<>();

        for (int i = 0; i < elementNames.size(); i++) {
            String name = elementNames.get(i).getText();
            int count = Integer.parseInt(elementCount.get(i).getText());
            keyValueMap.put(name, count);
        }

        List<Map.Entry<String, Integer>> sortedList = keyValueMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(20)
                .collect(Collectors.toList());


        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table>")
                .append("<tr><th>Фирма</th><th>Количество объявлений</th></tr>");

        for (Map.Entry<String, Integer> entry : sortedList) {
            String name = entry.getKey();
            int count = entry.getValue();
            htmlTable.append("<tr><td>").append(name).append("</td><td>").append(count).append("</td></tr>");
        }

        htmlTable.append("</table>");

        Allure.descriptionHtml(htmlTable.toString());
        return this;
    }

}
