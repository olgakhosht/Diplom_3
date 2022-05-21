package praktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {
    // Локатор кнопки "Войти" на странице восстановления пароля
    @FindBy(how = How.CSS, using ="a[class='Auth_link__1fOlj'][href='/login']")
    private SelenideElement logInForgotPasswordPage;


    @Step("Нажатие на кнопку 'Восстановить пароль' формы 'Вход'")
    public void clickLogInForgotPasswordPage() {
        logInForgotPasswordPage.click();
    }
}
