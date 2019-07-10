package com.nata.model;

import java.util.Objects;

public class User {
    private String mailboxLogin;
    private String password;
    private String successLoginForCheckCorrectLogIn;

    public User(String mailboxLogin, String password, String successLoginForCheckCorrectLogIn) {
        this.mailboxLogin = mailboxLogin;
        this.password = password;
        this.successLoginForCheckCorrectLogIn = successLoginForCheckCorrectLogIn;
    }

    public String getMailboxLogin() {
        return mailboxLogin;
    }

    public void setMailboxLogin(String mailboxLogin) {
        this.mailboxLogin = mailboxLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSuccessLoginForCheckCorrectLogIn() {
        return successLoginForCheckCorrectLogIn;
    }

    public void setSuccessLoginForCheckCorrectLogIn(String successLoginForCheckCorrectLogIn) {
        this.successLoginForCheckCorrectLogIn = successLoginForCheckCorrectLogIn;
    }

    @Override
    public String toString() {
        return "User{" +
                "mailboxLogin='" + mailboxLogin + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getMailboxLogin(), user.getMailboxLogin()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMailboxLogin(), getPassword());
    }
}
