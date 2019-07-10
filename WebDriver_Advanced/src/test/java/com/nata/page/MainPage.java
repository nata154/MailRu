package com.nata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private static final By CHECK_LOGIN_IS_IN_RIGHT_CORNER_LOCATOR = By.xpath("//i[@id='PH_user-email']");
    //By buttonCreateNewLetter = By.xpath("//span[@class='compose-button__wrapper']");
    //By buttonCreateNewLetter = By.xpath("//span[@title='Написать письмо (N)']");
    //By buttonCreateNewLetter = By.xpath("//span[contains(@title, 'Написать письмо')]");
    private static final By BUTTON_CREATE_NEW_LETTER_LOCATOR = By.xpath("//span[contains(text(), 'Написать письмо')]");
    private static final By FIELD_TO_IN_LETTER_LOCATOR = By.xpath("//div[@class='compose-app__compose']//input[@type='text']");
    private static final By FIELD_SUBJECT_LOCATOR = By.xpath("//div[@class='subject__container--HWnat']//input[@class='container--H9L5q size_s--3_M-_']");
    private static final By FIELD_BODY_OF_EMAIL_LOCATOR = By.xpath("//div[@class='compose-app__compose']//div[contains(@role, 'textbox')]");
    private static final By BUTTON_SAVE_TO_DRAFTS_LOCATOR = By.xpath("//span[@class='button2__txt' and contains(text(), 'Сохранить')]");
    private static final By BUTTON_CLOSE_WINDOW_ARTER_WRITING_LETTER_LOCATOR = By.xpath("//button[@title='Закрыть']");

    private static final By BUTTON_SEND_EMAIL_LOCATOR = By.xpath("//span[@class='button2__txt' and contains(text(), 'Отправить')]");
    private static final By BUTTON_CLOSE_WINDOW_ARTER_SENDING_LETTER_LOCATOR = By.xpath("//span[@title='Закрыть']/span");

    public String checkLogin() {
        return driver.findElement(CHECK_LOGIN_IS_IN_RIGHT_CORNER_LOCATOR).getText();
    }

    public MainPage writeMail(String writeEmailTo, String subject, String bodyOfEmail) {
        waitForElementVisible(BUTTON_CREATE_NEW_LETTER_LOCATOR);
        driver.findElement(BUTTON_CREATE_NEW_LETTER_LOCATOR).click();
        driver.findElement(FIELD_TO_IN_LETTER_LOCATOR).sendKeys(writeEmailTo);
        driver.findElement(FIELD_SUBJECT_LOCATOR).sendKeys(subject);
        driver.findElement(FIELD_BODY_OF_EMAIL_LOCATOR).sendKeys(bodyOfEmail);
        return this;
    }

    public MainPage saveMailToDrafts() {
        driver.findElement(BUTTON_SAVE_TO_DRAFTS_LOCATOR).click();
        waitForElementVisible(BUTTON_CLOSE_WINDOW_ARTER_WRITING_LETTER_LOCATOR);
        driver.findElement(BUTTON_CLOSE_WINDOW_ARTER_WRITING_LETTER_LOCATOR).click();
        return this;
    }

    public MainPage sendEmail() {
        driver.findElement(BUTTON_SEND_EMAIL_LOCATOR).click();
        driver.findElement(BUTTON_CLOSE_WINDOW_ARTER_SENDING_LETTER_LOCATOR).click();
        return this;
    }

    public MainPage highlightPortalMenu() {
        highlightElement(driver, driver.findElement(PORTAL_MENU_LOCATOR));
        scrollPage(driver);
        return this;
    }

    private static final By PORTAL_MENU_LOCATOR = By.xpath("//div[@class='portal-menu js-shortcut']");
    private static final By MAILRU_MENU_LOCATOR = By.xpath("//a[@class='portal-menu__logo']");

    public void highlightElement(WebDriver driver, WebElement element) {
        String bg = element.getCssValue("background-color");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background-color: yellow');", element);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute('style', 'background-color: " + bg +"');", element);
    }

    public void scrollPage(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scroll(0, 250);");
            }

    public MainPage clickMenuMailRu() {
        clickElement(driver, driver.findElement(MAILRU_MENU_LOCATOR));
        return this;
    }

    public void clickElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element);
    }
}
