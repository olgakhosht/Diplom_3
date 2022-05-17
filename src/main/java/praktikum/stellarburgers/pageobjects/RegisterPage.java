package praktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class RegisterPage {

    // Локатор поля Имя
    @FindBy(how = How.XPATH, using = ".//fieldset[1]/div/div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name ='name']")
    //@FindBy(how = How.XPATH, using = ".//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name ='name']")
    private SelenideElement nameRegisterPage;

    // Локатор поля "Email"
    @FindBy(how = How.XPATH, using = ".//fieldset[2]/div/div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name ='name']")
    //@FindBy(how = How.XPATH, using = "/html/body/div/div/main/div/form/fieldset[2]/div/div/input")
    private SelenideElement emailRegisterPage;

    // Локатор поля "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@class='text input__textfield text_type_main-default'][@type ='password']")
    private SelenideElement passwordRegisterPage;

    // Локатор кнопки "Зарегистрироваться" на странице регистрации
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    //@FindBy(how = How.XPATH, using = ".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement registerRegisterPage;


    // Локатор кнопки "Войти" на странице регистрации
    @FindBy(how = How.XPATH, using = ".//a[@class='Auth_link__1fOlj'][@href='/login']")
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
