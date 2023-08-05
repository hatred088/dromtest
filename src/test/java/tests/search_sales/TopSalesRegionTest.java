package tests.search_sales;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import static constants.Constant.Urls.DROM_SALES_PAGE;

public class TopSalesRegionTest extends BaseTest {

    @Test(priority = 2, description = "Тест №3. Вывод топ 20 фирм с наибольшим количеством поданных объявлений")
    public void checkTopSalesRegion () {

        basePage.open(DROM_SALES_PAGE);

        dromSalesPage
                .choiceRegion("Приморский край")
                .top20Sales();

    }
}
