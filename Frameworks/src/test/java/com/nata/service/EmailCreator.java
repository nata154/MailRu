package com.nata.service;

import com.nata.model.Email;

public class EmailCreator {

    public static final String TESTDATA_EMAIL_ADRESS = "writeEmailTo";
    public static final String TESTDATA_EMAIL_SUBJECT = "subject";
    public static final String TESTDATA_EMAIL_BODY_OF_EMAIL = "bodyOfEmail";

    public static Email emailWithAdressSubjectBody(){
        return new Email(TestDataReader.getTestData(TESTDATA_EMAIL_ADRESS),
                TestDataReader.getTestData(TESTDATA_EMAIL_SUBJECT),
                TestDataReader.getTestData(TESTDATA_EMAIL_BODY_OF_EMAIL));
    }
}
