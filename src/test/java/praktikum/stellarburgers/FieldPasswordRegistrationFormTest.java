package praktikum.stellarburgers;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.stellarburgers.pageobjects.LoginPage;
import praktikum.stellarburgers.pageobjects.MainPage;
import praktikum.stellarburgers.pageobjects.RegisterPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;
import static praktikum.stellarburgers.pageobjects.MainPage.browserTest;

@Feature("Сообщение об ошибке для некорректного пароля в форме регистрации")
@RunWith(Parameterized.class)
public class FieldPasswordRegistrationFormTest {
    private final String incorrectPassword;

    public FieldPasswordRegistrationFormTest(String incorrectPassword) {
        this.incorrectPassword = incorrectPassword;
    }

    @Parameterized.Parameters(name = "Тестовые данные Некорректный пароль = {0}")
    public static Object[][] getData() {
        return new Object[][]{
                // Тестовые данные с учетом граничных значений
                {"1"},
                {"2w"},
                {"4rrr"},
                {"5qtw0"},
        };
    }

    // Открытие браузера, главной страницы сайта, переход в форму регистрации
    @Step("@Before")
    @Before
    public void setUp() {
        Configuration.browser = browserTest;
        MainPage mainPage = open(MainPage.URLStellarBurgers, MainPage.class);
        mainPage.clickLogInAccount();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLoginPage();
    }


    @Test
    public void errorMessageWithIncorrectPassword() {
        RegisterPage registerPage = page(RegisterPage.class);

        registerPage.fillPasswordRegisterPage(incorrectPassword);
        registerPage.clickEmailRegisterPage();
        assertTrue("Нет сообщения о некорректном пароле после ввода некорректного пароля и клике на поле 'Email'",
                registerPage.isMessageAboutIncorrectPassword());

        registerPage.clickNameRegisterPage();
        assertTrue("Нет сообщения о некорректном пароле при переходе на поле 'Имя'",
                registerPage.isMessageAboutIncorrectPassword());

        registerPage.clickRegisterRegisterPage();
        assertTrue("Нет сообщения о некорректном пароле после клика на кнопку 'Зарегистрироваться'",
                registerPage.isMessageAboutIncorrectPassword());
    }
}
