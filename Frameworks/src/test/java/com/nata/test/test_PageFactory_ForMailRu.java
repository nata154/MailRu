package com.nata.test;

import com.nata.driver.DriverSingleton;
import com.nata.model.Email;
import com.nata.model.User;
import com.nata.page.*;
import com.nata.service.EmailCreator;
import com.nata.service.UserCreator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class test_PageFactory_ForMailRu {

    private WebDriver driver;

    @BeforeClass
    public void setPropertyAndRunDriver() {
        driver = DriverSingleton.getDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        LogInPage logInPage = new LogInPage(driver);
        logInPage.openLogInPage();
        User user = UserCreator.withCredentialsFromProperty();
        logInPage.enterLoginAndPassword(user);
        logInPage.clickEnterEmail();
    }

    @AfterClass(alwaysRun = true)
    public void shutDownDriver() {
        LogOutPage logOutPage = new LogOutPage(driver);
        logOutPage.logOutMailRu();
        DriverSingleton.closeDriver();
    }

    @Test
    public void testSuccessLogIn() {
        User user = UserCreator.withCredentialsFromProperty();
        LogInPage logInPage = new LogInPage(driver);
        String actualLogin = logInPage.checkLogin();
        Assert.assertEquals(actualLogin, user.getSuccessLoginForCheckCorrectLogIn(), "Incorrect logIn");
    }

    @Test
    public void testWriteLetterInDraftsAndCheckItInFolder() {
        Email email = EmailCreator.emailWithAdressSubjectBody();
        MainPage mainPage = new MainPage(driver)
            .writeMail(email)
            .saveMailToDrafts();
        DraftPage draftPage = new DraftPage(driver)
            .moveToDraftPage()
            .openFirstEmailDrafts();
        String actualAdress = draftPage.lineVerifyDraftAdress();
        String actualSubject = draftPage.lineVerifyDraftSubject();
        Assert.assertEquals(actualAdress, email.getWriteEmailTo(), "The adressee is different");
        Assert.assertEquals(actualSubject, email.getSubject(), "The subject is wrong");
    }

    @Test(enabled = false)
    public void testWriteSendEmailAndEnsureItAppearsInSentFolder() {
        Email email = EmailCreator.emailWithAdressSubjectBody();
        MainPage mainPage = new MainPage(driver)
            .writeMail(email)
            .sendEmail();
        SentMailPage sentMailPage = new SentMailPage(driver)
            .openSentFolder();
        String searchLetterWithSubject = sentMailPage.lineOfSentLetterIsPresent();
        Assert.assertEquals(searchLetterWithSubject, email.getSubject(), "The letter absent in folder Sent letters");
    }

    @Test()
    public void testWriteLetterInDraftSendLetterFromDraftsAndVerifyItDisappearsFromDrafts() {
        Email email = EmailCreator.emailWithAdressSubjectBody();
        MainPage mainPage = new MainPage(driver)
            .writeMail(email)
            .saveMailToDrafts();
        DraftPage draftPage = new DraftPage(driver)
            .moveToDraftPage()
            .openFirstEmailDrafts()
            .sendFirstLetterFromDrafts();
        Boolean folderDraftsIsEmpty = draftPage.lineAbsenseOfAnyLetterInDraft();
        Assert.assertTrue(folderDraftsIsEmpty, "The folder Drafts is not empty!");
    }
}
