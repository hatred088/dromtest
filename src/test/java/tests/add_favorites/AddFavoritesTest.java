package tests.add_favorites;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import static constants.Constant.Urls.DROM_SALES_PAGE;

// Тесты проверки добавления в избранное

public class AddFavoritesTest extends BaseTest {

    @Test(priority = 1, description = "Тест №2. Проверка добавления авто в избранное")
    public void checkAddCarFavorites(){

        basePage.open(DROM_SALES_PAGE);

        dromSalesPage.goToAuth();

        dromAuthPage
                .inputData()
                .submitButton();

        dromSalesPage.goToFavorites();

        myFavoritesPage
                .deleteAllFavorites()
                .goToDromSalesPage();

        dromSalesPage
                .addFirstSaleToFavorite()
                .goToFavorites();

        myFavoritesPage
                .checkFavoriteLinksMatch(dromSalesPage.getHrefFirstSale())
                .deleteAllFavorites();

    }
}
