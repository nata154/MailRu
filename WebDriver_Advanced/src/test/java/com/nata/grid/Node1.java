package com.nata.grid;

import com.epam.lab.page.MainPage;
import com.epam.lab.page.LogOutPage;
import com.epam.lab.page.DraftPage;
import com.epam.lab.page.LogInPage;
import com.epam.lab.page.SentLettersPage;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Node1 {
    private WebDriver driver;

    String mailboxLogin = "nata154154";
    String password = "1z2x3c4v";
    String myMail = "nata154154@bk.ru";
    String writeEmailTo = "nata154154@bk.ru";
    String subject = "first attempt";
    String bodyOfEmail = "bodyOfEmail bodyOfEmail bodyOfEmail";
    String nodeUrl;

    @BeforeClass
    public void setPropertyAndRunDriver() {
        nodeUrl = "http://10.6.102.255:20743/wd/hub";
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WIN10);
        try {
            driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        LogInPage logInPage = new LogInPage(driver);
        logInPage.openLogInPage().enterLoginAndPassword(mailboxLogin, password).clickEnterEmail();
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
        MainPage mainPage = new MainPage(driver);
        String rememberLogin = mainPage.checkLogin();
        Assert.assertEquals(rememberLogin, myMail, "Incorrect logIn");
    }

    @Test
    public void testWriteLetterInDraftsAndCheckItInFolder() {
        MainPage mainPage = new MainPage(driver);
        mainPage.writeMail(writeEmailTo, subject, bodyOfEmail).saveMailToDrafts();
        DraftPage draftPage = new DraftPage(driver);
        draftPage.moveToDraftPage().openFirstEmailDrafts();
        String currentAdresseInLetter = draftPage.lineVerifyDraftAdress();
        String currentSubjectInLetter = draftPage.lineVerifyDraftSubject();
        Assert.assertEquals(currentAdresseInLetter, writeEmailTo, "The adressee is different");
        Assert.assertEquals(currentSubjectInLetter, subject, "The subject is wrong");
    }

    @Test
    public void testWriteSendEmailAndEnsureItAppearsInSentFolder() {
        MainPage mainPage = new MainPage(driver);
        mainPage.writeMail(writeEmailTo, subject, bodyOfEmail).sendEmail();
        SentLettersPage sentLettersPage = new SentLettersPage(driver);
        sentLettersPage.openSentFolder();
        String currentSubjectInLetterSent = sentLettersPage.lineOfSentLetterIsPresent();
        Assert.assertEquals(currentSubjectInLetterSent, subject, "The letter absent in folder Sent letters");
    }

    @Test
    public void testWriteLetterInDraftSendLetterFromDraftsAndVerifyItDisappearsFromDrafts() {
        DraftPage draftPage = new DraftPage(driver);
        draftPage.moveToDraftPage().deleteAllExistingEmailsInDrafts();
        MainPage mainPage = new MainPage(driver);
        mainPage.writeMail(writeEmailTo, subject, bodyOfEmail).saveMailToDrafts();
        draftPage.moveToDraftPage().openFirstEmailDrafts().sendFirstLetterFromDrafts();
        Boolean lettersAbsentInDraftFolder = draftPage.lineAbsenseOfAnyLetterInDraft();
        Assert.assertTrue(lettersAbsentInDraftFolder, "The letter that we should send still exists in folder Drafts");
    }

    @Test
    public void testHighlightAndClickPortalMenu() {
        MainPage mainPage = new MainPage(driver);
        mainPage.highlightPortalMenu();
        mainPage.clickMenuMailRu();
        String rememberLogin = mainPage.checkLogin();
        Assert.assertEquals(rememberLogin, myMail, "Incorrect logIn");
    }
}
