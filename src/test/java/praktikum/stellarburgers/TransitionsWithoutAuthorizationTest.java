package praktikum.stellarburgers;

import com.codeborne.selenide.Configuration;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;

import org.junit.Before;
import org.junit.Test;

import praktikum.stellarburgers.pageobjects.LoginPage;
import praktikum.stellarburgers.pageobjects.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertTrue;
import static praktikum.stellarburgers.pageobjects.MainPage.browserTest;


@Feature("Переходы у неавторизованного пользователя")
public class TransitionsWithoutAuthorizationTest {

    @Before
    @Step("@Before: открытие браузера и сайта")
    public void setUp() {
        Configuration.browser = browserTest;
        open(MainPage.URLStellarBurgers);
    }


    @Test
    @DisplayName("Переход успешный в личный кабинет")
    public void successfulTransitionFromMainPageToPersonalAccount() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = page(LoginPage.class);
        assertTrue("Не отображается форма входа-авторизации", loginPage.isAuthorizationForm());
    }

    @Test
    @DisplayName("Переход успешный из личного кабинета в конструктор")
    public void successfulTransitionFromPersonalAccountToDesigner() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccount();

        mainPage.clickDesigner();
        assertTrue("Нет секции с ингредиентами на главной странице'", mainPage.isSectionIngredients());
        assertTrue("Нет кнопки 'Войти в аккаунт'", mainPage.isButtonLogInAccount());
    }

    @Test
    @DisplayName("Переход успешный из личного кабинета на логотип  Stellar Burgers")
    public void successfulTransitionFromPersonalAccountToLogo() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccount();

        mainPage.clickLogo();
        assertTrue("Нет секции с ингредиентами на главной странице'", mainPage.isSectionIngredients());
        assertTrue("Нет кнопки 'Войти в аккаунт'", mainPage.isButtonLogInAccount());
    }

    @Test
    @DisplayName("Переход успешный к разделу 'Булки'")
    public void designerSuccessfulTransitionToRolls() {
        MainPage mainPage = page(MainPage.class);
        assertThat("У активного раздела заголовок не 'Булки'",
                mainPage.isBunsOverActive(), equalTo(true));
        mainPage.clickSauceOverNotActive();
        mainPage.clickBunsOverNotActive();
        assertThat("При переходе к разделу 'Булки' заголовок раздела не 'Булки'",
                mainPage.isBunsOverActive(), equalTo(true));
    }

    @Test
    @DisplayName("Переход успешный к разделу 'Соусы'")
    public void designerSuccessfulTransitionToSauces() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickSauceOverNotActive();
        assertThat("При переходе к разделу 'Соусы' заголовок раздела не 'Соусы'",
                mainPage.isSaucesOverActive(), equalTo(true));
    }

    @Test
    @DisplayName("Переход успешный к разделу 'Начинки'")
    public void designerSuccessfulTransitionToFillings() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickFillingsOverNotActive();
        assertThat("При переходе к разделу 'Начинки' заголовок раздела не 'Начинки'",
                mainPage.isFillingsOverActive(), equalTo(true));
    }
}
