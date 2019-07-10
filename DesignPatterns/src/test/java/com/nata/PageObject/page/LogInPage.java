package com.nata_PageObject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage extends AbstractPage {

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    String BASE_URL = "https://mail.ru/";
    private static final By FIELD_FOR_ENTER_EMAIL_LOCATOR = By.id("mailbox:login");
    private static final By DROP_MENU_FOR_MAIL_LOCATOR = By.xpath("//*[@id='mailbox:domain']/option[contains(text(), '@bk.ru')]");
    private static final By FIELD_PASSWORD_LOCATOR = By.id("mailbox:password");
    private static final By BUTTON_ENTER_ACCOUNT_FIELD_LOCATOR = By.xpath("//input[@value='Войти']");
    private static final By FIELD_LOGIN_LOCATOR = By.xpath("//span/i[@id='PH_user-email' and contains(text(), 'nata154154@bk.ru')]");

    public LogInPage openLogInPage() {
        driver.get(BASE_URL);
        return this;
    }

    public LogInPage enterLoginAndPassword(String mailboxLogin, String password) {
        driver.findElement(FIELD_FOR_ENTER_EMAIL_LOCATOR).clear();
        driver.findElement(FIELD_FOR_ENTER_EMAIL_LOCATOR).sendKeys(mailboxLogin);
        driver.findElement(DROP_MENU_FOR_MAIL_LOCATOR).click();
        driver.findElement(FIELD_PASSWORD_LOCATOR).sendKeys(password);
        return this;
    }

    public MainPage clickEnterEmail(){
        driver.findElement(BUTTON_ENTER_ACCOUNT_FIELD_LOCATOR).click();
        waitForElementVisible(FIELD_LOGIN_LOCATOR);
        return new MainPage(driver);
    }
}
