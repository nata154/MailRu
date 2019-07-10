package com.nata.stepDefinition;

import com.epam.lab.driver.DriverSingleton;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ServiceHooks {

    protected WebDriver driver;

    @Before()
    public void setUp() {
        driver = DriverSingleton.getDriver("chrome");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}

