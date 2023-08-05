package tests.search_sales;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import static constants.Constant.Urls.DROM_SALES_PAGE;

public class SearchSalesTest extends BaseTest {

    @Test(priority = 0, description = "Тест №1. Проверка отсутствия проданных авто + год >= 2007")
    public void checkIsRedirectListing () {
        basePage.open(DROM_SALES_PAGE);

        dromSalesPage
                .clickAdvancedSearch()
                .findFilters()
                .applyFilters();

        salesListingPage
                .checkSoldCars()
                .checkYearCars()
                .clickNextPage()
                .checkSoldCars()
                .checkYearCars();
    }

}
