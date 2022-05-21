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

@Feature("Регистрация пользователя")
public class RegistrationTest {
    String email;
    String password;
    String name;
    UserClientAPI userClientAPI = new UserClientAPI();



    // Формирование данных пользователя, открытие браузера, главной страницы, переход на форму авторизации
    @Before
    @Step("@Before")
    public void setUp() {
        email = RandomStringUtils.randomAlphabetic(10) + "@ya.ru";
        password = RandomStringUtils.randomAlphabetic(10);
        name = RandomStringUtils.randomAlphabetic(10);

        Configuration.browser = browserTest;
        MainPage mainPage = open(MainPage.URLStellarBurgers, MainPage.class);
        mainPage.clickLogInAccount();
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
    @DisplayName("Успешная регистрация пользователя")
    public void successfulRegistration() {
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLoginPage();

        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.registration(name, email, password);

        loginPage.authorization(email, password);

        MainPage mainPage = page(MainPage.class);

        assertTrue("После авторизации не появилась кнопка 'Оформить заказ' на главной странице",
                mainPage.isButtonPlaceAnOrder());

        mainPage.clickPersonalAccount();
        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);

        assertTrue("В личном кабинете указанный email не соответствует email авторизованного пользователя",
                accountProfilePage.getEmailLKValue().equalsIgnoreCase(email));
        assertTrue("В личном кабинете указанное имя не соответствует имени авторизованного пользователя",
                accountProfilePage.getNameLKValue().equals(name));
    }
}
