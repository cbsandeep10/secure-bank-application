package com.example.banking.bank_app.model;
import javax.persistence.*;
import javax.validation.constraints.*;


public class Transaction {
    @NotNull(message="Account number cannot be null")
    private Integer accountnum;

    @Min(1)
    @Max(4000)
    @NotNull(message="Amount is between 1 and 4000")
    private Integer amount;

    @NotNull
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Email address is invalid")
    private String email_id;

    @NotNull
   // @Pattern(regexp = "^[0-9][0-9][0-9]{3}-[0-9][0-9][0-9]{3}-[0-9][0-9][0-9][0-9]{4}", message="Phone Number Format is XXX-YYY-ZZZZ")
    @Pattern(regexp = "^[2-9]\\d{2}-\\d{3}-\\d{4}$", message="Phone Number Format is XXX-YYY-ZZZZ")
    private String phone_num;

    public Integer getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(Integer accountnum) {
        this.accountnum = accountnum;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }
}
