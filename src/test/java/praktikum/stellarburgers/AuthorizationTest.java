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
import praktikum.stellarburgers.pageobjects.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;
import static praktikum.stellarburgers.pageobjects.MainPage.browserTest;

@Feature("Вход по разным кнопкам (авторизация)")
public class AuthorizationTest {
    String email;
    String password;
    String name;
    UserClientAPI userClientAPI = new UserClientAPI();

    // Браузер, открытие сайта, регистрация пользователя через UI, переход на главную страницу сайта
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

        mainPage.clickDesigner();
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

    /*
    В задании указано проверить Вход по разным кнопкам.
    Я поняла ВХОД как АВТОРИЗАЦИЮ по разным кнопкам.
    Поэтому тесты написаны на проверку входа-авторизации.

    Тесты отличаются только тем, по какой кнопке/кнопкам осуществляется переход в форму авторизации.
    Экспериментировала разными способами написать один тест, отличающийся только вызовом первого метода/ов (нажатие кнопок),
    но неуспешно. Поэтому остановилась на таком варианте: отдельный тест для каждой кнопки.
     */

    @Test
    @DisplayName("Вход успешный через кнопку 'Личный кабинет' (авторизация)")
    public void successfulAuthorizationByButtonPersonalAccount() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = page(LoginPage.class);

        loginPage.authorization(email, password);

        assertTrue("После авторизации не появилась кнопка 'Оформить заказ' на главной странице",
                mainPage.isButtonPlaceAnOrder());

        mainPage.clickPersonalAccount();

        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);

        assertTrue("В личном кабинете указанный email не соответствует email авторизованного пользователя",
                accountProfilePage.getEmailLKValue().equalsIgnoreCase(email));

        assertTrue("В личном кабинете указанное имя не соответствует имени авторизованного пользователя",
                accountProfilePage.getNameLKValue().equals(name));
    }

    @Test
    @DisplayName("Вход успешный по кнопке 'Войти в аккаунт' на главной (авторизация)")
    public void successfulAuthorizationByButtonLogInAccount() {
        Configuration.browser = browserTest;
        MainPage mainPage = open(MainPage.URLStellarBurgers, MainPage.class);

        mainPage.clickLogInAccount();

        LoginPage loginPage = page(LoginPage.class);

        loginPage.authorization(email, password);

        assertTrue("После авторизации не появилась кнопка 'Оформить заказ' на главной странице",
                mainPage.isButtonPlaceAnOrder());

        mainPage.clickPersonalAccount();

        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);


        assertTrue("В личном кабинете указанный email не соответствует email авторизованного пользователя",
                accountProfilePage.getEmailLKValue().equalsIgnoreCase(email));


        assertTrue("В личном кабинете указанное имя не соответствует имени авторизованного пользователя",
                accountProfilePage.getNameLKValue().equals(name));
    }

    @Test
    @DisplayName("Вход успешный через кнопку в форме регистрации (авторизация)")
    public void successfulAuthorizationByButtonLogInRegisterPage() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLoginPage();

        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.clickLogInRegisterPage();

        loginPage.authorization(email, password);

        assertTrue("После авторизации не появилась кнопка 'Оформить заказ' на главной странице",
                mainPage.isButtonPlaceAnOrder());

        mainPage.clickPersonalAccount();

        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);

        assertTrue("В личном кабинете указанный email не соответствует email авторизованного пользователя",
                accountProfilePage.getEmailLKValue().equalsIgnoreCase(email));

        assertTrue("В личном кабинете указанное имя не соответствует имени авторизованного пользователя",
                accountProfilePage.getNameLKValue().equals(name));
    }

    @Test
    @DisplayName("Вход успешный через кнопку в фоме восстановления пароля (авторизация)")
    public void successfulAuthorizationByButtonLogInForgotPasswordPage() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRecoverPasswordLoginPage();

        ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);
        forgotPasswordPage.clickLogInForgotPasswordPage();

        loginPage.authorization(email, password);

        assertTrue("После авторизации не появилась кнопка 'Оформить заказ' на главной странице",
                mainPage.isButtonPlaceAnOrder());

        mainPage.clickPersonalAccount();

        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);

        assertTrue("В личном кабинете указанный email не соответствует email авторизованного пользователя",
                accountProfilePage.getEmailLKValue().equalsIgnoreCase(email));

        assertTrue("В личном кабинете указанное имя не соответствует имени авторизованного пользователя",
                accountProfilePage.getNameLKValue().equals(name));
    }

    /*
    Неуспешную авторизацию (с неправильными email и паролем, без email, без пароля) не делала,
    т.к. посчитала, что заданием это не предусмотрено
     */
}
