package com.example.banking.bank_app.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;

    @Column(name="transfer_amount")
    private float transfer_amount;

    @Column(name="transfer_date")
    @Temporal(TemporalType.DATE)
    private Date transfer_date;

    @Column(name="description")
    private String description;

    @Column(name="status")
    private Integer status;

    @Column(name="from_account")
    private Integer from_account;

    @Column(name="to_account")
    private Integer to_account;

    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public float gettransfer_amount() {
        return transfer_amount;
    }

    public void settransfer_amount(float transfer_amount) {
        this.transfer_amount = transfer_amount;
    }

    public Date gettransfer_date() {
        return transfer_date;
    }

    public void settransfer_date(Date transfer_date) {
        this.transfer_date = transfer_date;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public Integer getstatus() {
        return status;
    }

    public void setstatus(Integer status) {
        this.status = status;
    }

    public Integer getfrom_account() {
        return from_account;
    }

    public void setfrom_account(Integer from_account) {
        this.from_account = from_account;
    }

    public Integer getto_account() {
        return to_account;
    }

    public void setto_account(Integer to_account) {
        this.to_account = to_account;
    }
}