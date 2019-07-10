package com.nata_PageFactory.test;

import com.epam.lab.Module6_PageFactory.page.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class test_PageFactory_ForMailRu {

    private WebDriver driver;

    String mailboxLogin = "nata154154";
    String password = "1z2x3c4v";
    String myMail = "nata154154@bk.ru";
    String writeEmailTo = "nata154154@bk.ru";
    String subject = "first attempt";
    String bodyOfEmail = "bodyOfEmail bodyOfEmail bodyOfEmail";

    @BeforeClass
    public void setPropertyAndRunDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\Individual_work\\DesignPatterns\\src\\test\\resources\\chromedriver.exe");\\переиначить на драйвер менеджер
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        LogInPage logInPage = new LogInPage(driver);
        logInPage.openLogInPage();
        logInPage.enterLoginAndPassword(mailboxLogin, password);
        logInPage.clickEnterEmail();
    }

    @AfterClass
    public void shutDownDriver() {
        LogOutPage logOutPage = new LogOutPage(driver);
        logOutPage.logOutMailRu();
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSuccessLogIn() {
        LogInPage logInPage = new LogInPage(driver);
        String actualLogin = logInPage.checkLogin();
        Assert.assertEquals(actualLogin, myMail, "Incorrect logIn");
    }

    @Test
    public void testWriteLetterInDraftsAndCheckItInFolder() {
        MainPage mainPage = new MainPage(driver);
        mainPage.writeMail(writeEmailTo, subject, bodyOfEmail);
        mainPage.saveMailToDrafts();
        DraftPage draftPage = new DraftPage(driver);
        draftPage.moveToDraftPage();
        draftPage.openFirstEmailDrafts();
        String actualAdress = draftPage.lineVerifyDraftAdress();
        String actualSubject = draftPage.lineVerifyDraftSubject();
        Assert.assertEquals(actualAdress, writeEmailTo, "The adressee is different");
        Assert.assertEquals(actualSubject, subject, "The subject is wrong");
    }

    @Test
    public void testWriteSendEmailAndEnsureItAppearsInSentFolder() {
        MainPage mainPage = new MainPage(driver);
        mainPage.writeMail(writeEmailTo, subject, bodyOfEmail);
        mainPage.sendEmail();
        SentMailPage sentMailPage = new SentMailPage(driver);
        sentMailPage.openSentFolder();
        String searchLetterWithSubject = sentMailPage.lineOfSentLetterIsPresent();
        Assert.assertEquals(searchLetterWithSubject, subject, "The letter absent in folder Sent letters");
    }

    @Test()
    public void testWriteLetterInDraftSendLetterFromDraftsAndVerifyItDisappearsFromDrafts() {
        MainPage mainPage = new MainPage(driver);
        mainPage.writeMail(writeEmailTo, subject, bodyOfEmail);
        mainPage.saveMailToDrafts();
        DraftPage draftPage = new DraftPage(driver);
        draftPage.moveToDraftPage();
        draftPage.openFirstEmailDrafts();
        draftPage.sendFirstLetterFromDrafts();
        Boolean folderDraftsIsEmpty = draftPage.lineAbsenseOfAnyLetterInDraft();
        Assert.assertTrue(folderDraftsIsEmpty, "The folder Drafts is not empty!");
    }
}
