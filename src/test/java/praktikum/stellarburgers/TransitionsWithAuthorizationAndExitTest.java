package praktikum.stellarburgers;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.stellarburgers.pageobjects.AccountProfilePage;
import praktikum.stellarburgers.pageobjects.LoginPage;
import praktikum.stellarburgers.pageobjects.MainPage;
import praktikum.stellarburgers.pageobjects.RegisterPage;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;
import static praktikum.stellarburgers.pageobjects.MainPage.browserTest;


@Feature("Переходы и выход из аккаунта у авторизованного пользователя")
public class TransitionsWithAuthorizationAndExitTest {
    String email;
    String password;
    String name;
    UserClientAPI userClientAPI = new UserClientAPI();

    // Открытие браузера, главной страницы, регистрация и авторизация пользователя через UI
    @Before
    @Step("@Before")
    public void setUp() {
        email = RandomStringUtils.randomAlphabetic(10) + "@ya.ru";
        password = RandomStringUtils.randomAlphabetic(10);
        name = RandomStringUtils.randomAlphabetic(10);

        Configuration.browser = browserTest;
        MainPage mainPage = open(MainPage.URLStellarBurgers, MainPage.class);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLoginPage();

        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.registration(name, email, password);

        loginPage.authorization(email, password);
    }

    /*
    Удаление пользователя (можно сделать только через API с указанием accessToken пользователя)
    Для получения accessToken пользователя необходима сначала авторизация пользователя через API
     */
    @After
    @Step("@After")
    public void tearDown() {
        ValidatableResponse loginResponse = userClientAPI.loginUser(new UserCredentialsForAPI(email, password));
        String accessTokenLoginResponse = loginResponse.extract().path("accessToken");
        userClientAPI.deleteUser(accessTokenLoginResponse);
    }

    @Test
    @DisplayName("Переход успешный в личный кабинет")
    public void successfulTransitionFromMainPageToPersonalAccount() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccount();
        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);
        assertTrue("Не отображается вся форма личного кабинета", accountProfilePage.isPersonalAccountForm());
    }

    @Test
    @DisplayName("Переход успешный из личного кабинета в конструктор")
    public void successfulTransitionFromPersonalAccountToDesigner() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccount();
        mainPage.clickDesigner();
        assertTrue("Нет секции с ингредиентами на главной странице'", mainPage.isSectionIngredients());
        assertTrue("Нет кнопки 'Оформить заказ'", mainPage.isButtonPlaceAnOrder());

    }

    @Test
    @DisplayName("Переход успешный из личного кабинета на логотип  Stellar Burgers")
    public void successfulTransitionFromPersonalAccountToLogo() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccount();
        mainPage.clickLogo();
        assertTrue("Нет секции с ингредиентами на главной странице'", mainPage.isSectionIngredients());
        assertTrue("Нет кнопки 'Оформить заказ'", mainPage.isButtonPlaceAnOrder());
    }

    @Test
    @DisplayName("Выход успешный из аккаунта")
    public void successfulExitFromPersonalAccount() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccount();
        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);
        accountProfilePage.clickExit();
        LoginPage loginPage = page(LoginPage.class);
        assertTrue("При выходе из аккаунта не появилась форма авторизации'", loginPage.isAuthorizationForm());
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
