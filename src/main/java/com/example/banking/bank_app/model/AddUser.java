package com.example.banking.bank_app.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddUser {


    @NotNull(message="Name cannot be empty")
    private String name;

    @NotNull(message="Gender cannot be empty")
    private String gender;

    @NotNull(message="Gender cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @NotNull(message="Contact cannot be empty")
    private String contact;

    @NotNull(message="Email id cannot be empty")
    private String emailId;

    @NotNull(message="Address cannot be empty")
    private String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
