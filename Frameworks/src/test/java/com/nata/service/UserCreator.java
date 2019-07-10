package com.nata.service;

import com.nata.model.User;

public class UserCreator {
    public static final String TESTDATA_USER_MAILBOX_LOGIN = "mailboxLogin";
    public static final String TESTDATA_USER_MAILBOX_PASSWORD = "password";
    public static final String TESTDATA_USER_SUCCEED_LOGIN = "successLoginForCheckCorrectLogIn";

    public static User withCredentialsFromProperty() {
        return new User(TestDataReader.getTestData(TESTDATA_USER_MAILBOX_LOGIN),
                TestDataReader.getTestData(TESTDATA_USER_MAILBOX_PASSWORD),
                    TestDataReader.getTestData(TESTDATA_USER_SUCCEED_LOGIN));
    }
}
