package com.nata.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOutPage extends AbstractPage{

    private final Logger logger = LogManager.getRootLogger();

    public LogOutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id = "PH_logoutLink")
    private WebElement fieldLogOut;

    public void logOutMailRu() {
        waitForElementClickable(fieldLogOut);
        fieldLogOut.click();
        logger.info("Log out succeed!");
    }
}
