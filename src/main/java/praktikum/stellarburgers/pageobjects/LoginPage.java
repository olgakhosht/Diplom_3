package praktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;


public class LoginPage {

    // Локатор "Вход"
    @FindBy(how = How.XPATH, using = ".//h2[text()='Вход']")
    private SelenideElement entranceHeadingLoginPage;

    // Локатор кнопки "Email" на странице авторизации
    @FindBy(how = How.XPATH, using = ".//label[contains(text(),'Email')]/../input")
    private SelenideElement emailLoginPage;

    // Локатор кнопки "Пароль" на странице авторизации
    @FindBy(how = How.XPATH, using = ".//input[@type='password']")
    private SelenideElement passwordLoginPage;

    // Локатор кнопки "Войти" на странице авторизации
    @FindBy(how = How.XPATH, using = ".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement logInLoginPage;

    // Локатор кнопки "Зарегистрироваться" на странице авторизации
    @FindBy(how = How.CSS, using = "a[class='Auth_link__1fOlj'][href='/register']")
    private SelenideElement registerLoginPage;

    // Локатор кнопки "Восстановить пароль" на странице авторизации
    @FindBy(how = How.CSS, using = "a[class='Auth_link__1fOlj'][href='/forgot-password']")
    private SelenideElement recoverPasswordLoginPage;


    @Step("Заполнение полей 'Email', 'Пароль', нажатие на кнопку 'Войти' в форме 'Вход'")
    public void authorization(String email, String password) {
        emailLoginPage.shouldBe(exist, appear, visible).sendKeys(email);
        passwordLoginPage.sendKeys(password);
        logInLoginPage.click();
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
        boolean authorizationForm = entranceHeadingLoginPage.shouldBe(exist, appear, visible).isDisplayed() && emailLoginPage.shouldBe(exist, appear, visible).isDisplayed() &&
                passwordLoginPage.shouldBe(exist, appear, visible).isDisplayed() && logInLoginPage.shouldBe(exist, appear, visible).isDisplayed() && registerLoginPage.shouldBe(exist, appear, visible).isDisplayed() &&
                recoverPasswordLoginPage.shouldBe(exist, appear, visible).isDisplayed();
        return authorizationForm;
    }

}
