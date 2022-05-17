package praktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.sleep;


public class AccountProfilePage {

    // Локатор поля "Имя" в личном кабинете
    @FindBy(how = How.XPATH, using = ".//li[1]/div/div/input[@class='text input__textfield text_type_main-default input__textfield-disabled'][@type='text']")
    //@FindBy(how = How.XPATH, using = "//*[@id='root']/div/main/div/div/div/ul/li[1]/div/div/input")
    private SelenideElement nameLK;

    // Локатор поля "Email" в личном кабинете
    @FindBy(how = How.XPATH, using = ".//li[2]/div/div/input[@class='text input__textfield text_type_main-default input__textfield-disabled'][@type='text']")
    //@FindBy(how = How.XPATH, using= "/html/body/div/div/main/div/div/div/ul/li[2]/div/div/input")
    private SelenideElement emailLK;

    // Локатор поля "Пароль" в личном кабинете
    @FindBy(how = How.XPATH, using = ".//input[@class='text input__textfield text_type_main-default input__textfield-disabled'][@type='password']")
    //@FindBy(how = How.XPATH, using = "/html/body/div/div/main/div/div/div/ul/li[3]/div/div/input")
    private SelenideElement passwordLK;

    // Локатор кнопки "Выход" в личном кабинете
    @FindBy(how = How.XPATH, using = ".//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive']")
    private SelenideElement exit;

    // Локатор кнопки "Профиль"
    @FindBy(how = How.XPATH, using = ".//a[@href='/account/profile']")
    private SelenideElement profileLK;

    // Локатор кнопки "История заказов"
    @FindBy(how = How.XPATH, using = ".//a[@href='/account/order-history']")
    private SelenideElement orderHistoryLK;

    // Локатор кнопки "Сохранить"
    @FindBy(how = How.XPATH, using = ".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement saveLK;

    // Локатор кнопки "Отмена"
    @FindBy(how = How.XPATH, using = ".//button[@class='button_button__33qZ0 button_button_type_secondary__3-tsA button_button_size_medium__3zxIa']")
    private SelenideElement cancelLK;

    // Локатор надписи "В этом разделе вы можете изменить свои персональные данные"
    @FindBy(how = How.XPATH, using = ".//p[@class='Account_text__fZAIn text text_type_main-default']")
    private SelenideElement informationLabelLK;




    @Step("Нажатие на кнопку 'Выход' в личном кабинете")
    public void clickExit() {
        exit.click();
    }

    @Step("Возврат значения из поля 'Имя' личного кабинета")
    public String getNameLKValue() {
        return nameLK.getValue();
    }

    @Step("Возврат значения из поля 'Email' личного кабинета")
    public String getEmailLKValue() {
        return emailLK.getValue();
    }

    @Step("Возврат истинности отображения поля 'Имя' личного кабинета")
    public boolean isFieldNameLK() {
        return nameLK.isDisplayed();
    }

    @Step("Возврат истинности отображения поля 'Email' личного кабинета")
    public boolean isFieldEmailLK() {
        return emailLK.isDisplayed();
    }

    @Step("Возврат истинности отображения поля 'Пароль' личного кабинета")
    public boolean isFieldPasswordLK() {
        return passwordLK.isDisplayed();
    }

    @Step("Возврат истинности отображения полей и кнопок формы личного кабинета авторизованного пользователя")
    public boolean isPersonalAccountForm() {
        sleep(3000);
        boolean personalAccountForm = nameLK.isDisplayed() && emailLK.isDisplayed() &&
                passwordLK.isDisplayed() && profileLK.isDisplayed() && orderHistoryLK.isDisplayed() &&
                exit.isDisplayed() && saveLK.isDisplayed()  && cancelLK.isDisplayed() && informationLabelLK.isDisplayed();
        return personalAccountForm;
    }

}
