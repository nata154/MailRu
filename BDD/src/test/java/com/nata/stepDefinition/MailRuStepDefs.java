package com.nata.stepDefinition;

import com.epam.lab.driver.DriverSingleton;
import com.epam.lab.page.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
//import io.cucumber.datatable.DataTableType;
import org.testng.Assert;

import java.util.List;

public class MailRuStepDefs {

    String successLoginForCheckCorrectLogIn = "nata154154@bk.ru";

    @Given("I opened Mail.Ru")
    public void iOpenMailRu() {
        new LogInPage(DriverSingleton.getCurrentDriver()).openLogInPage();
    }

    @When("^I enter login (\\w+)$")
    public void iEnterMailboxLogin(String mail) {
        new LogInPage(DriverSingleton.getCurrentDriver()).enterLogin(mail);
    }

    @And("^I enter password (\\w+)$")
    public void iEnterPasswordPassword(String password) {
        new LogInPage(DriverSingleton.getCurrentDriver()).enterPassword(password);
    }

    @And("^I enter login and password$")
    public void iEnterLoginAndPassword(DataTable table) {
        List<String> list = table.asList(String.class);
        new LogInPage(DriverSingleton.getCurrentDriver()).enterLogin(list.get(0))
                .enterPassword(list.get(1));
    }

    @And("^I click submit button$")
    public void iClickSubmitButton() {
        new LogInPage(DriverSingleton.getCurrentDriver()).clickEnterEmail();
    }

    @Then("^Check i entered successfully$")
    public void iEnteredSuccessfully() {
        String actualLogin = new LogInPage(DriverSingleton.getCurrentDriver()).checkLogin();
        Assert.assertEquals(actualLogin, successLoginForCheckCorrectLogIn, "Log in with incorrect credentials.");
    }

    @When("^I click button create new letter$")
    public void iClickButtonCreateNewLetter(){
        new MainPage(DriverSingleton.getCurrentDriver()).clickButtonCreateNewLetter();
    }

    @And("^I enter write email to writeEmailTo (\\w+@\\w+\\.\\w+)$")
    public void iEnterWriteEmailToWriteEmailTo(String writeEmailTo) {
        new MainPage(DriverSingleton.getCurrentDriver()).writeMailWriteEmailTo(writeEmailTo);
    }

    @And("^I enter subject of email subject (\\w+)$")
    public void iEnterSubjectOfEmailSubject(String subject) {
        new MainPage(DriverSingleton.getCurrentDriver()).writeMailSubject(subject);
    }

    @And("^I enter body of email bodyOfEmail (\\w+)$")
    public void iEnterBodyOfEmailBodyOfEmail(String bodyOfEmail) {
        new MainPage(DriverSingleton.getCurrentDriver()).writeMailBodyOfEmaill(bodyOfEmail);
    }

    @And("^Click Send Email$")
    public void clickSendEmail() {
        new MainPage(DriverSingleton.getCurrentDriver()).sendEmail();
    }

    @Then("^Letter appears in folder Sent (\\w+)$")
    public void letterAppearsInFolderSent(String subjectOfEmail) {
        // new SentMailPage(DriverSingleton.getDriver()).openSentFolder();
        String actualSubjectOfLetterInSent = new SentMailPage(DriverSingleton.getCurrentDriver()).openSentFolder().lineOfSentLetterIsPresent();
        Assert.assertEquals(actualSubjectOfLetterInSent, subjectOfEmail, "The letter absent in folder Sent letters");
    }

    @And("^Log out$")
    public void logOut() {
        new LogOutPage(DriverSingleton.getCurrentDriver()).logOutMailRu();
    }


    @And("^Click button save email to drafts$")
    public void clickButtonSaveEmailToDrafts() {
        new MainPage(DriverSingleton.getCurrentDriver()).saveMailToDrafts();
    }

    @And("^Open drafts folder$")
    public void openDraftsFolder()  {
        new DraftPage(DriverSingleton.getCurrentDriver()).openFirstEmailDrafts();
    }

    @And("^Letter appears in folder Drafts (\\w+@\\w+\\.\\w+)$")
    public void letterAppearsInFolderDrafts(String writeEmailTo) {
        String currentAddresseInDraft = new DraftPage(DriverSingleton.getCurrentDriver()).lineVerifyDraftAdress();
        Assert.assertEquals(currentAddresseInDraft, writeEmailTo, "Wrong adressee in letter while checking it in drafts");
    }


}

