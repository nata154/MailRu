package com.nata_PageFactory.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOutPage extends AbstractPage{

    public LogOutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id = "PH_logoutLink")
    private WebElement fieldLogOut;

    public void logOutMailRu() {
        waitForElementClickable(fieldLogOut);
        fieldLogOut.click();
    }
}
