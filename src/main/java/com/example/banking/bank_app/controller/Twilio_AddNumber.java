package com.example.banking.bank_app.controller;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.ValidationRequest;

public class Twilio_AddNumber {
    public static final String ACCOUNT_SID = "ACd6818abaa8a05f3974bb728b97f4962b";
    public static final String AUTH_TOKEN = "1f54ef2518984ea7a7471662b4e53ea5";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ValidationRequest validationRequest = ValidationRequest.creator(
                new com.twilio.type.PhoneNumber("+18014275057"))
                .setFriendlyName("My Home Phone Number")
                .create();

        System.out.println(validationRequest.getFriendlyName());
    }
}