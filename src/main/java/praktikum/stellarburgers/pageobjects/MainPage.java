package praktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.openqa.selenium.support.How.CLASS_NAME;


public class MainPage {

    public static final String browserTest = "chrome";

    public static final String URLStellarBurgers = "https://stellarburgers.nomoreparties.site/";

    private static final String activeTabCssClass = "tab_tab_type_current__2BEPc";

    // Локатор "Конструктор"
    @FindBy(how = How.XPATH, using = ".//p[contains(text(), 'Конструктор')]/..")
    private SelenideElement designerMainPage;

    // Локатор логотипа "stellar burgers"
    @FindBy(how = CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement logoMainPage;

    // Локатор "Личный кабинет"
    @FindBy(how = How.CSS, using = "a[href='/account']")
    private SelenideElement personalAccountMainPage;

    // Локатор кнопки "Войти в аккаунт" на главной странице
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Войти в аккаунт')]")
    private SelenideElement logInAccount;

    // Локатор кнопки "Оформить заказ" на главной странице
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Оформить заказ')]")
    private SelenideElement placeAnOrder;

    // Локатор секции ингредиентов на главной странице
    @FindBy(how = How.CLASS_NAME, using = "BurgerIngredients_ingredients__1N8v2")
    private SelenideElement sectionIngredientsMainPage;

    // Коллекция локаторов разделов "Булки", "Соусы", "Начинки" (над лентой)
    @FindBy(how = How.CSS, using = "div.tab_tab__1SPyG")
    private ElementsCollection burgerComponents;



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
        return placeAnOrder.shouldBe(exist, appear, visible).isDisplayed();
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
    public void clickBunsOverNotActive() {
        getBunsElement().shouldBe(exist, appear, visible).click();
    }

    @Step("Возврат истинности отображения АКТИВНОГО раздела 'Булки'")
    public boolean isBunsOverActive() {
        return getBunsElement().shouldBe(cssClass(activeTabCssClass)).getText().equals("Булки");
    }

    @Step("Нажатие на раздел 'Соусы' над лентой")
    public void clickSauceOverNotActive() {
        getSaucesElement().shouldBe(exist, appear, visible).click();
    }

    @Step("Возврат истинности отображения АКТИВНОГО раздела 'Соусы'")
    public boolean isSaucesOverActive() {
        return getSaucesElement().shouldBe(cssClass(activeTabCssClass)).getText().equals("Соусы");
    }

    @Step("Нажатие на раздел 'Начинки' над лентой")
    public void clickFillingsOverNotActive() {
        getFillingsElement().shouldBe(exist, appear, visible).click();
    }

    @Step("Возврат истинности отображения АКТИВНОГО раздела 'Начинки'")
    public boolean isFillingsOverActive() {
        return getFillingsElement().shouldBe(cssClass(activeTabCssClass)).getText().equals("Начинки");
    }

    @Step("Возврат истинности отображения секции ингредиентов'")
    public boolean isSectionIngredients() {
        return sectionIngredientsMainPage.isDisplayed();
    }

    private SelenideElement getBunsElement() {
        return  burgerComponents.get(0);
    }

    private SelenideElement getSaucesElement() {
        return  burgerComponents.get(1);
    }

    private SelenideElement getFillingsElement() {
        return  burgerComponents.get(2);
    }
}
