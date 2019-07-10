package com.nata_PageFactory.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DraftPage extends AbstractPage {

    private static final String BASE_URL_DRAFTS = "https://e.mail.ru/drafts/";

    public DraftPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

//    @FindBy(xpath = "//a[@href='/drafts/']//div[contains(text(), 'Черновики')]")
//    WebElement folderDrafts;

    @FindBy(xpath = "//a[@href='/drafts/']/div")
    private WebElement folderDrafts;

    @FindBy(xpath = "//child::div[@class='dataset__items']")
    private WebElement linesOfLetter;

    @FindBy(xpath = "//div[@class='dataset__items']/a")
    private WebElement lineOfFirstLetterInDrafts;

    @FindBy(xpath = "//div[@class='container--3B3Lm status_base--wsRcM']/span[@class='text--1tHKB']")
    private WebElement forCheckAddress;

    @FindBy(xpath = "//div[@class='subject__container--HWnat']//input[@class='container--H9L5q size_s--3_M-_']")
    private WebElement forCheckSubject;

    @FindBy(xpath = "//div[@class='js-helper js-readmsg-msg']/div[1]/div[1]/div[1]/div")
    private WebElement forCheckBody;

    @FindBy(xpath = "//div[@class='dataset__items']/a[1]")
    private WebElement lineOfLetterInDraft;

    @FindBy(xpath = "//span[@class='button2__txt' and contains(text(), 'Отправить')]")
    private WebElement buttonSendEmail;

    @FindBy(xpath = "//div[@data-test-id='confirmation:empty-letter']//span[@style='visibility: visible;' and contains(text(), 'Отправить')]")
    private WebElement buttonIAmSureToSendEmptyEmail;

    @FindBy(xpath = "//span[@title='Закрыть']")
    private WebElement buttonCloseWindowAboutSucceedSend;

    @FindBy(xpath = "//span[@class='octopus__line' and contains(text(), 'У вас нет незаконченных')]")
    private WebElement lineAbsenseOfAnyLetterInDraft;

    public DraftPage moveToDraftPage(){
        folderDrafts.click();
        return this;
    }

    public DraftPage openFirstEmailDrafts() {
        waitForElementVisible(folderDrafts);
        waitForElementVisible(lineOfFirstLetterInDrafts);
        lineOfFirstLetterInDrafts.click();
        waitForElementVisible(forCheckAddress);
        return this;
    }

    public String lineVerifyDraftAdress() {
        return forCheckAddress.getText();
    }

    public String lineVerifyDraftSubject() {
        return forCheckSubject.getAttribute("value");
    }

    public String lineVerifyDraftBody() {
        return forCheckBody.getText();
    }

    public DraftPage sendFirstLetterFromDrafts() {
        buttonSendEmail.click();//
        buttonIAmSureToSendEmptyEmail.click();
        buttonCloseWindowAboutSucceedSend.click();
        return this;
    }

    public boolean lineAbsenseOfAnyLetterInDraft() {
        folderDrafts.click();
        waitForElementVisible(folderDrafts);
        return lineAbsenseOfAnyLetterInDraft.isDisplayed();
    }
}
