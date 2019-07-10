package com.nata_PageObject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SentLettersPage extends AbstractPage {

    public SentLettersPage(WebDriver driver) {
        super(driver);
    }

    private static final By FOLDER_SENT_LOCATOR = By.xpath("//a[@href='/sent/']");
    private static final By LINES_OF_SENT_LETTER_LOCATOR = By.xpath("//child::div[@class='dataset__items']");
    private static final By LINE_OF_SENT_LETTER_LOCATOR = By.xpath("//div[@class='dataset__items']//a[@class='llc js-letter-list-item llc_normal llc_first']");
    private static final By LINE_OF_SENT_LETTER_CHECK_LOCATOR = By.xpath("//div[@class='thread__header']/h2[@class='thread__subject']");

    public SentLettersPage openSentFolder() {
        driver.findElement(FOLDER_SENT_LOCATOR).click();
        waitForElementVisible(LINES_OF_SENT_LETTER_LOCATOR);
        driver.findElements(LINES_OF_SENT_LETTER_LOCATOR).get(0).click();
        return this;
    }

    public String lineOfSentLetterIsPresent() {
        return driver.findElement(LINE_OF_SENT_LETTER_CHECK_LOCATOR).getText();
    }
}
