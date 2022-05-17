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
        assertThat("В ленте нет заголовка 'Булки'",
                mainPage.isRollsInside(), equalTo(true));
        mainPage.clickFillingsOver();
        mainPage.clickRollsOver();
        assertThat("При переходе к разделу 'Булки' в ленте нет заголовка 'Булки'",
                mainPage.isRollsInside(), equalTo(true));
    }

    @Test
    @DisplayName("Переход успешный к разделу 'Соусы'")
    public void designerSuccessfulTransitionToSauces() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickSauceOver();
        assertThat("При переходе к разделу 'Соусы' в ленте нет заголовка 'Соусы'",
                mainPage.isSauceInside(), equalTo(true));
        //assertThat("При переходе к разделу 'Соусы' в ленте отображается заголовок 'Булки'", mainPage.isRollsInside(), equalTo(false));
    }

    @Test
    @DisplayName("Переход успешный к разделу 'Начинки'")
    public void designerSuccessfulTransitionToFillings() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickFillingsOver();
        assertThat("При переходе к разделу 'Начинки' в ленте нет заголовка 'Начинки'",
                mainPage.isFillingsInside(), equalTo(true));
        //assertThat("При переходе к разделу 'Начинки' в ленте отображается заголовок 'Булки'", mainPage.isRollsInside(), equalTo(false));
        //assertThat("При переходе к разделу 'Начинки' в ленте отображается заголовок 'Соусы'", mainPage.isSauceInside(), equalTo(false));
    }
}
