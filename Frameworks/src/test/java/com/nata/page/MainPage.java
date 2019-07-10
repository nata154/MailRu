package com.nata.page;

import com.epam.lab.model.Email;
import jdk.nashorn.internal.parser.Lexer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//span[contains(text(), 'Написать письмо')]")
    //@FindBy(xpath = "//div[@class='dimmer']//span[contains(text(), 'Написать письмо')]")
    private WebElement buttonCreateNewLetter;

    @FindBy(xpath = "//div[@class='compose-app__compose']//input[@type='text']")
    private WebElement fieldToInLetter;

    @FindBy(xpath = "//div[@class='subject__container--HWnat']//input[@class='container--H9L5q size_s--3_M-_']")
    private WebElement fieldSubject;

    @FindBy(xpath = "//div[@class='compose-app__compose']//div[contains(@role, 'textbox')]")
    private WebElement fieldBodyOfEmail;

    @FindBy(xpath = "//span[@class='button2__txt' and contains(text(), 'Сохранить')]")
    private WebElement buttonSaveToDrafts;

    @FindBy(xpath = "//button[@title='Закрыть']")
    private WebElement closeWindowButton;

    @FindBy(xpath = "//span[@class='button2__txt' and contains(text(), 'Отправить')]")
    private WebElement buttonSendEmail;

    @FindBy(xpath = "//span[@title='Закрыть']/span")
    private WebElement buttonCloseWindowAfterSending;

    public MainPage writeMail(Email email) {
        waitForElementVisible(buttonCreateNewLetter);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        buttonCreateNewLetter.click();
        fieldToInLetter.sendKeys(email.getWriteEmailTo());
        fieldSubject.sendKeys(email.getSubject());
        fieldBodyOfEmail.sendKeys(email.getBodyOfEmail());
        logger.info("email was written");
        return this;
    }

    public MainPage saveMailToDrafts() {
        buttonSaveToDrafts.click();
        logger.info("email was saved to drafts");
        waitForElementVisible(closeWindowButton);
        closeWindowButton.click();
        return this;
    }

    public MainPage sendEmail() {
        buttonSendEmail.click();
        logger.info("email was sent");
        buttonCloseWindowAfterSending.click();
        return this;
    }
}
