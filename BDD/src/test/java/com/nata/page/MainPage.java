package com.nata.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {

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

    public MainPage writeMail(String writeEmailTo, String subject, String bodyOfEmaill) {
        waitForElementVisible(buttonCreateNewLetter);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        buttonCreateNewLetter.click();
        fieldToInLetter.sendKeys(writeEmailTo);
        fieldSubject.sendKeys(subject);
        fieldBodyOfEmail.sendKeys(bodyOfEmaill);
        return this;
    }

    public MainPage clickButtonCreateNewLetter() {
        waitForElementVisible(buttonCreateNewLetter);
        buttonCreateNewLetter.click();
        return this;
    }

    public MainPage writeMailWriteEmailTo(String writeEmailTo) {
        fieldToInLetter.sendKeys(writeEmailTo);
        return this;
    }

    public MainPage writeMailSubject(String subject) {
        fieldSubject.sendKeys(subject);
        return this;
    }

    public MainPage writeMailBodyOfEmaill(String bodyOfEmaill) {
        fieldBodyOfEmail.sendKeys(bodyOfEmaill);
        return this;
    }

    public MainPage saveMailToDrafts() {
        buttonSaveToDrafts.click();
        waitForElementVisible(closeWindowButton);
        closeWindowButton.click();
        return this;
    }

    public MainPage sendEmail() {
        buttonSendEmail.click();
        buttonCloseWindowAfterSending.click();
        return this;
    }
}
