package com.nata.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SentMailPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    public SentMailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//a[@href='/sent/']")
    private WebElement folderSent;

    @FindBy(xpath = "//child::div[@class='dataset__items']")
    private WebElement linesOfSentLetter;

    @FindBy(xpath = "//a[@class='llc js-tooltip-direction_letter-bottom js-letter-list-item llc_normal llc_first']")
    private WebElement lineOfSentLetter;

    @FindBy(xpath = "//div[@class='thread__header']/h2[@class='thread__subject']")
    private WebElement lineOfSentLetterCheck;

    public SentMailPage openSentFolder() {
        folderSent.click();
        logger.info("Folder Sent was opened.");
        waitForElementVisible(lineOfSentLetter);
        lineOfSentLetter.click();
        return this;
    }

    public String lineOfSentLetterIsPresent() {
        return lineOfSentLetterCheck.getText();
    }
}
