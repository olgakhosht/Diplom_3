package praktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.sleep;


public class LoginPage {

    // Локатор "Вход"
    @FindBy(how = How.XPATH, using = ".//div[@class='Auth_login__3hAey']")
    private SelenideElement entranceHeadingLoginPage;

    // Локатор кнопки "Email" на странице авторизации

    @FindBy(how = How.XPATH, using = ".//input[@class='text input__textfield text_type_main-default'][@type='text']")
    // @FindBy(how = How.XPATH, using = "/html/body/div/div/main/div/form/fieldset[1]/div/div/input")
    private SelenideElement emailLoginPage;

    // Локатор кнопки "Пароль" на странице авторизации
    @FindBy(how = How.XPATH, using = ".//input[@class='text input__textfield text_type_main-default'][@type='password']")
    private SelenideElement passwordLoginPage;

    // Локатор кнопки "Войти" на странице авторизации
    @FindBy(how = How.XPATH, using = ".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    //@FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa")
    private SelenideElement logInLoginPage;

    // Локатор кнопки "Зарегистрироваться" на странице авторизации
    @FindBy(how = How.XPATH, using = ".//a[@class='Auth_link__1fOlj'][@href='/register']")
    //@FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement registerLoginPage;

    // Локатор кнопки "Восстановить пароль" на странице авторизации
    @FindBy(how = How.XPATH, using = ".//a[@class='Auth_link__1fOlj'][@href='/forgot-password']")
    private SelenideElement recoverPasswordLoginPage;


    @Step("Заполнение полей 'Email', 'Пароль', нажатие на кнопку 'Войти' в форме 'Вход'")
    public void authorization(String email, String password) {
        // НЕ забыть заменить sleep на другую конструкцию
        sleep(3000);
        emailLoginPage.sendKeys(email);
        passwordLoginPage.sendKeys(password);
        logInLoginPage.click();
        sleep(3000);
    }

    @Step("Нажатие на кнопку 'Восстановить пароль' в форме 'Вход'")
    public void clickRecoverPasswordLoginPage() {
        recoverPasswordLoginPage.click();
    }

    @Step("Нажатие на кнопку 'Зарегистрироваться в форме 'Вход'")
    public void clickRegisterLoginPage() {
        registerLoginPage.click();
    }

    @Step("Возврат истинности отображения полей и кнопок формы 'Вход'")
    public boolean isAuthorizationForm() {
        sleep(3000);
        boolean authorizationForm = entranceHeadingLoginPage.isDisplayed() && emailLoginPage.isDisplayed() &&
                passwordLoginPage.isDisplayed() && logInLoginPage.isDisplayed() && registerLoginPage.isDisplayed() &&
                recoverPasswordLoginPage.isDisplayed();
        return authorizationForm;
    }

}
