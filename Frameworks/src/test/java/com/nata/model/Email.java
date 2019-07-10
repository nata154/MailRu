package com.nata.model;

import java.util.Objects;

public class Email {

    String writeEmailTo;
    String subject;
    String bodyOfEmail;

    public Email(String writeEmailTo, String subject, String bodyOfEmail) {
        this.writeEmailTo = writeEmailTo;
        this.subject = subject;
        this.bodyOfEmail = bodyOfEmail;
    }

    public String getWriteEmailTo() {
        return writeEmailTo;
    }

    public void setWriteEmailTo(String writeEmailTo) {
        this.writeEmailTo = writeEmailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBodyOfEmail() {
        return bodyOfEmail;
    }

    public void setBodyOfEmail(String bodyOfEmail) {
        this.bodyOfEmail = bodyOfEmail;
    }

    @Override
    public String toString() {
        return "Email{" +
                "writeEmailTo='" + writeEmailTo + '\'' +
                ", subject='" + subject + '\'' +
                ", bodyOfEmail='" + bodyOfEmail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email email = (Email) o;
        return Objects.equals(getWriteEmailTo(), email.getWriteEmailTo()) &&
                Objects.equals(getSubject(), email.getSubject()) &&
                Objects.equals(getBodyOfEmail(), email.getBodyOfEmail());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getWriteEmailTo(), getSubject(), getBodyOfEmail());
    }
}
