package praktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;


public class MainPage {

    public static final String browserTest = "chrome";

    public static final String URLStellarBurgers = "https://stellarburgers.nomoreparties.site/";


    // Локатор "Конструктор"
    @FindBy(how = How.XPATH, using = ".//li[1]/a[@class='AppHeader_header__link__3D_hX']")
    //@FindBy(how = How.XPATH, using = "/html/body/div/div/header/nav/ul/li[1]/a")
    private SelenideElement designerMainPage;

    // Локатор логотипа "stellar burgers"
    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement logoMainPage;

    // Локатор "Личный кабинет"
    @FindBy(how = How.XPATH, using = ".//a[@href='/account']")
    //@FindBy(how = How.XPATH, using = ".//a[@class='AppHeader_header__link__3D_hX']")
    private SelenideElement personalAccountMainPage;

    // Локатор кнопки "Войти в аккаунт" на главной странице
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Войти в аккаунт')]")
    private SelenideElement logInAccount;

    // Локатор кнопки "Оформить заказ" на главной странице
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Оформить заказ')]")
    private SelenideElement placeAnOrder;

    // Локатор секции ингредиентов на главной странице
    @FindBy(how = How.XPATH, using = ".//section[@class='BurgerIngredients_ingredients__1N8v2']")
    private SelenideElement sectionIngredientsMainPage;

    // Локатор раздела "Булки" над лентой
    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/section[1]/div[1]/div[1]")
    private SelenideElement rollsOver;

    // Локатор раздела "Булки" внутри ленты
    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Булки')]")
    private SelenideElement rollsInside;

    // Локатор надписи "Соусы" над лентой
    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/section[1]/div[1]/div[2]")
    private SelenideElement saucesOver;

    // Локатор надписи "Соусы" внутри ленты
    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Соусы')]")
    private SelenideElement saucesInside;

    // Локатор надписи "Начинки" над лентой
    @FindBy(how = How.XPATH, using = "/html/body/div/div/main/section[1]/div[1]/div[3]")
    private SelenideElement fillingsOver;

    // Локатор надписи "Начинки" внутри ленты
    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Начинки')]")
    private SelenideElement fillingsInside;


    @Step("Нажатие на кнопку 'Войти в аккаунт' на главной странице неавторизованного пользователя")
    public void clickLogInAccount() {
        logInAccount.click();
    }

    @Step("Возврат истинности отображения кнопки 'Войти в аккаунт' на главной странице неавторизованного пользователя")
    public boolean isButtonLogInAccount() {
        return logInAccount.isDisplayed();
    }

    @Step("Возврат истинности отображения кнопки 'Оформить заказ' у авторизованного пользователя")
    public boolean isButtonPlaceAnOrder() {
        return placeAnOrder.isDisplayed();
    }

    @Step("Нажатие на 'Личный кабинет' в шапке")
    public void clickPersonalAccount() {
        personalAccountMainPage.click();
    }

    @Step("Нажатие на 'Конструктор' в шапке")
    public void clickDesigner() {
        designerMainPage.click();
    }

    @Step("Нажатие на логотип в шапке")
    public void clickLogo() {
        logoMainPage.click();
    }

    @Step("Нажатие на логотип в шапке и возврат истинности текущего URL со сравниваемым'")
    public boolean clickLogoReturnMainPage() {
        logoMainPage.click();
        return url().equals(URLStellarBurgers);
    }

    @Step("Нажатие на раздел 'Булки' над лентой")
    public void clickRollsOver() {
        rollsOver.click();
    }

    @Step("Возврат истинности отображения 'Булки' внутри ленты")
    public boolean isRollsInside() {
        return rollsInside.isDisplayed();
    }

    @Step("Нажатие на раздел 'Соусы'над лентой")
    public void clickSauceOver() {
        saucesOver.click();
    }

    @Step("Возврат истинности отображения 'Соусы' внутри ленты")
    public boolean isSauceInside() {
        return saucesInside.isDisplayed();
    }

    @Step("Нажатие на раздел 'Начинки' над лентой")
    public void clickFillingsOver() {
        fillingsOver.click();
        sleep(3000);
    }

    @Step("Возврат истинности отображения 'Начинки' внутри ленты")
    public boolean isFillingsInside() {
        return fillingsInside.isDisplayed();
    }

    @Step("Возврат истинности отображения секции ингредиентов'")
    public boolean isSectionIngredients() {
        return sectionIngredientsMainPage.isDisplayed();
    }
}
