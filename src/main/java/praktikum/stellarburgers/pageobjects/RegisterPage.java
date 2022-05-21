package praktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class RegisterPage {

    // Локатор поля Имя
    @FindBy(how = How.XPATH, using = ".//label[contains(text(),'Имя')]/../input")
    private SelenideElement nameRegisterPage;

    // Локатор поля "Email"
    @FindBy(how = How.XPATH, using = ".//label[contains(text(),'Email')]/../input")
    private SelenideElement emailRegisterPage;

    // Локатор поля "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@type ='password']")
    private SelenideElement passwordRegisterPage;

    // Локатор кнопки "Зарегистрироваться" на странице регистрации
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerRegisterPage;

    // Локатор кнопки "Войти" на странице регистрации
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement logInRegisterPage;//

    // Локатор появляющегося сообщения "Некорректный пароль" при некорректном заполнении поля "Пароль"
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement messageAboutIncorrectPassword;

    @Step("Заполнение полей 'Имя', 'Email', 'Пароль', нажатие на кнопку 'Зарегистрироваться' в форме 'Регистрация'")
    public void registration(String name, String email, String password) {
        nameRegisterPage.sendKeys(name);
        emailRegisterPage.sendKeys(email);
        passwordRegisterPage.sendKeys(password);
        registerRegisterPage.click();
    }

    @Step("Нажатие на кнопку 'Войти' в форме 'Регистрация'")
    public void clickLogInRegisterPage() {
        logInRegisterPage.click();
    }

    @Step("Заполнение поля 'Пароль' в форме 'Регистрация'")
    public void fillPasswordRegisterPage(String password) {
        passwordRegisterPage.sendKeys(password);
    }

    @Step("Нажатие на поле 'Имя' в форме 'Регистрация'")
    public void clickNameRegisterPage() {
        nameRegisterPage.click();
    }

    @Step("Нажатие на поле 'Email' в форме 'Регистрация'")
    public void clickEmailRegisterPage() {
        emailRegisterPage.click();
    }

    @Step("Нажатие на поле 'Зарегистрироваться' в форме 'Регистрация'")
    public void clickRegisterRegisterPage() {
        registerRegisterPage.click();
    }

    @Step("Возврат истинности отображения сообщения об ошибке некорректного пароля в форме 'Регистрация'")
    public boolean isMessageAboutIncorrectPassword() {
        return messageAboutIncorrectPassword.isDisplayed();
    }
}
