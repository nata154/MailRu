package com.nata_PageObject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class DraftPage extends AbstractPage {

    public DraftPage(WebDriver driver) {
        super(driver);
    }

    private static final By FOLDER_DRAFTS_LOCATOR = By.xpath("//a[@href='/drafts/']/div");
    private static final By LINES_OF_LETTER_IN_DRAFTS_LOCATOR = By.xpath("//child::div[@class='dataset__items']");
    //private static final By LINES_OF_LETTER_IN_DRAFTS_LOCATOR = By.xpath("//div[contains(@class,'dataset__items'][0]");
    private static final By CHECK_ADRESS_OF_LETTER_IN_DRAFT_LOCATOR = By.xpath("//div[@class='container--3B3Lm status_base--wsRcM']/span[@class='text--1tHKB']");
    private static final By CHECK_SUBJECT_OF_LETTER_IN_DRAFT_LOCATOR = By.xpath("//div[@class='subject__container--HWnat']//input[@class='container--H9L5q size_s--3_M-_']");
    private static final By CHECK_BODY_OF_LETTER_IN_DRAFT_LOCATOR = By.xpath("//div[@class='js-helper js-readmsg-msg']/div[1]/div[1]/div[1]/div");

    public DraftPage moveToDraftPage() {
        waitForElementVisible(FOLDER_DRAFTS_LOCATOR);
        driver.findElement(FOLDER_DRAFTS_LOCATOR).click();
        return this;
    }

    public DraftPage openFirstEmailDrafts() {
        waitForElementVisible(LINES_OF_LETTER_IN_DRAFTS_LOCATOR);
        driver.findElement(LINES_OF_LETTER_IN_DRAFTS_LOCATOR).click();
        waitForElementVisible(CHECK_ADRESS_OF_LETTER_IN_DRAFT_LOCATOR);
        return this;
    }

    public String lineVerifyDraftAdress() {
        return driver.findElement(CHECK_ADRESS_OF_LETTER_IN_DRAFT_LOCATOR).getText();
    }

    public String lineVerifyDraftSubject() {
        String txt = driver.findElement(CHECK_SUBJECT_OF_LETTER_IN_DRAFT_LOCATOR).getAttribute("value");
        return txt;
    }

    public String lineVerifyDraftBody() {
        return driver.findElement(CHECK_BODY_OF_LETTER_IN_DRAFT_LOCATOR).getText();
    }

    private static final By BUTTON_SELECT_ALL_TO_DELETE_LETTERS_IN_DRAFTS_LOCATOR = By.xpath("//span[@class='button2__explanation' and contains(text(), 'Выделить все')]");
    private static final By BUTTON_DELETE_LETTERS_IN_DRAFTS_LOCATOR = By.xpath("//span[@class='button2__txt' and contains(text(), 'Удалить')]");

    private static final By BUTTON_SEND_EMAIL_FROM_DRAFTS_LOCATOR = By.xpath("//span[@class='button2__txt' and contains(text(), 'Отправить')]");
    private static final By BUTTON_I_AM_SURE_TO_SEND_EMPTY_EMAIL_LOCATOR = By.xpath("//div[@data-test-id='confirmation:empty-letter']//span[@style='visibility: visible;' and contains(text(), 'Отправить')]");
    private static final By BUTTON_CLOSE_WINDOW_AFTER_SUCCEED_SEND_LOCATOR = By.xpath("//span[@title='Закрыть']");
    private static final By LINE_ABSENSE_OF_ANY_LETTER_IN_DRAFT_LOCATOR = By.xpath("//span[@class='octopus__line' and contains(text(), 'У вас нет незаконченных')]");

    public DraftPage deleteAllExistingEmailsInDrafts() {
        driver.findElement(FOLDER_DRAFTS_LOCATOR).click();
        waitForElementVisible(FOLDER_DRAFTS_LOCATOR);
              try {
            driver.findElement(BUTTON_SELECT_ALL_TO_DELETE_LETTERS_IN_DRAFTS_LOCATOR).click();
            driver.findElement(BUTTON_DELETE_LETTERS_IN_DRAFTS_LOCATOR).click();
            return this;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        try {
            driver.findElement(LINE_ABSENSE_OF_ANY_LETTER_IN_DRAFT_LOCATOR).isDisplayed();
            return this;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return this;
    }

    public DraftPage sendFirstLetterFromDrafts() {
        driver.findElement(BUTTON_SEND_EMAIL_FROM_DRAFTS_LOCATOR).click();//
        driver.findElement(BUTTON_I_AM_SURE_TO_SEND_EMPTY_EMAIL_LOCATOR).click();
        driver.findElement(BUTTON_CLOSE_WINDOW_AFTER_SUCCEED_SEND_LOCATOR).click();
        return this;
    }

    public boolean lineAbsenseOfAnyLetterInDraft() {
        driver.findElement(FOLDER_DRAFTS_LOCATOR).click();
        waitForElementVisible(FOLDER_DRAFTS_LOCATOR);
        return driver.findElement(LINE_ABSENSE_OF_ANY_LETTER_IN_DRAFT_LOCATOR).isDisplayed();
    }
}
