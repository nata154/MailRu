package com.nata_PageFactory.page;

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

    public MainPage writeMail(String writeEmailTo, String subject, String bodyOfEmail) {
        buttonCreateNewLetter.click();
        fieldToInLetter.sendKeys(writeEmailTo);
        fieldSubject.sendKeys(subject);
        fieldBodyOfEmail.sendKeys(bodyOfEmail);
        return this;
    }

    public MainPage saveMailToDrafts() {
        buttonSaveToDrafts.click();
        waitForElementVisible(closeWindowButton);
        closeWindowButton.click();
        return this;
    }

    public MainPage sendEmail(){
        buttonSendEmail.click();
        buttonCloseWindowAfterSending.click();
        return this;
    }
}
