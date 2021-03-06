package com.nata.page;

import com.epam.lab.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private static final String BASE_URL = "https://mail.ru/";

    public LogInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id = "mailbox:login")
    private WebElement fieldMailbox;

    @FindBy(xpath = "//*[@id='mailbox:domain']/option[contains(text(), '@bk.ru')]")
    private WebElement dropMenuForMail;

    @FindBy(id = "mailbox:password")
    private WebElement fieldPassword;

    @FindBy(xpath = "//input[@value='Войти']")
    private WebElement buttonEnter;

    @FindBy(xpath = "//span/i[@id='PH_user-email' and contains(text(), 'nata154154@bk.ru')]")
    private WebElement fieldLogin;

    @FindBy(xpath = "//i[@id='PH_user-email']")
    private WebElement checkLogininRightCorner;

    public LogInPage openLogInPage(){
        driver.get(BASE_URL);
        logger.info("Login page opened");
        return this;
    }

    public LogInPage enterLoginAndPassword(User user) {
        fieldMailbox.clear();
        fieldMailbox.sendKeys(user.getMailboxLogin());
        dropMenuForMail.click();
        fieldPassword.sendKeys(user.getPassword());
        return this;
    }

    public MainPage clickEnterEmail(){
        buttonEnter.click();
        waitForElementVisible(fieldLogin);
        logger.info("Login performed");
        return new MainPage(driver);
    }

    public String checkLogin() {
        return checkLogininRightCorner.getText();
    }
}
