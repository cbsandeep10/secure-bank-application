package com.example.banking.bank_app.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNo;

    @Column(name="balance")
    private float balance;

    @Column(name="routing_no")
    private Integer routingNo;

    @Column(name="account_type")
    private Integer accountType;

    @Column(name="interest")
    private float interest;

    @Column(name="created")
    @Temporal(TemporalType.DATE)
    private Date created;

    @Column(name="updated")
    @Temporal(TemporalType.DATE)
    private Date updated;

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Integer getRoutingNo() {
        return routingNo;
    }

    public void setRoutingNo(Integer routingNo) {
        this.routingNo = routingNo;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
