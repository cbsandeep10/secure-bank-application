package com.example.banking.bank_app.model;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private Timestamp created;

    @Column(name="updated")
    private Timestamp updated;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
