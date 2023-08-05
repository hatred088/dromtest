package pages.drom_auth;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import pages.drom_sales.DromSalesPage;

import static constants.Constant.Accounts.LOGIN;
import static constants.Constant.Accounts.PASSWORD;

public class DromAuthPage extends BasePage {
    public DromAuthPage(WebDriver driver) {
        super(driver);
    }

    private final By inputLogin = By.cssSelector("input[name='sign']");
    private final By inputPassword = By.cssSelector("input[name='password']");
    private final By buttonSign = By.id("signbutton");


    @Step("Ввод данных для авторизации")
    public DromAuthPage inputData(){
        driver.findElement(inputLogin).sendKeys(LOGIN);
        driver.findElement(inputPassword).sendKeys(PASSWORD);
        return this;
    }

    @Step("Нажатие на кнопку авторизации")
    public DromAuthPage submitButton(){
        driver.findElement(buttonSign).click();
        return this;
    }


}
