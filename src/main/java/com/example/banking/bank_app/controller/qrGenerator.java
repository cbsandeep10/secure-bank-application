package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.User;
import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.security.authentication.BadCredentialsException;

import java.io.UnsupportedEncodingException;

public class qrGenerator {

    private final static String APP_NAME = "SecureBank";

    private final static String QR_PREFIX =
            "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";

    public String generateQRUrl(User user) throws UnsupportedEncodingException {
        return QR_PREFIX + String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", APP_NAME, user.getEmailId(), user.getSecret(), APP_NAME);

    }

    private boolean isValidLong(String code) {
        try {
            Long.parseLong(code);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean authenticate(User user, String verificationCode){
        if ((user == null)) {
            throw new BadCredentialsException("Invalid username or password");
        }final Totp totp = new Totp(user.getSecret());
        if (!isValidLong(verificationCode) || !totp.verify(verificationCode)) {
            throw new BadCredentialsException("Invalid verfication code");
        }
        return true;
    }

//    public static void main(String args[]){
//        User user = new User();
//        user.setEmailId("sandeep.cb2000@gmail.com");
//        user.setSecret(Base32.random());
//        try{
//            System.out.println(Base32.random());
////            System.out.println(generateQRUrl(user));
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//    }
}
