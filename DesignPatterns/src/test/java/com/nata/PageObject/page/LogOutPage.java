package com.nata_PageObject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOutPage extends AbstractPage {

    public LogOutPage(WebDriver driver) {
        super(driver);
    }

    private static final By FIELD_LOGOUT_LOCATOR = By.id("PH_logoutLink");

    public void logOutMailRu() {
        waitForElementVisible(FIELD_LOGOUT_LOCATOR);
        driver.findElement(FIELD_LOGOUT_LOCATOR).click();
    }
}
