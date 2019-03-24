package com.example.banking.bank_app.model;

/**
 * Application constants.
 */

public final class Config {
    //Status fields
    public static final int PENDING = 0;
    public static final int APPROVED = 1;
    public static final int DECLINED = 2;

    //Transaction fields
    public static final int DEBIT = 1;
    public static final int CREDIT = 2;

    //Transaction limit
    public static final float LIMIT = 1000;

    //Transfer type
    public static final String ACCOUNT = "account";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";

    //Request type
    public static final int TRANSFER = 1;
    public static final int DEPOSIT = 2;
    public static final int WITHDRAW = 3;

}
