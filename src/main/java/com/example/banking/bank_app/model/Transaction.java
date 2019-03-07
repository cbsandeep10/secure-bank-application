package com.example.banking.bank_app.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;

    @Column(name="transaction_amount")
    private float transaction_amount;

    @Column(name="transaction_timestamp")
    @Temporal(TemporalType.DATE)
    private Date transaction_timestamp;

    @Column(name="description")
    private String description;

    @Column(name="status")
    private Integer status;

    @Column(name="account_no")
    private Integer account_no;

    @Column(name="balance")
    private float balance;

    @Column(name="transaction_type")
    private int transaction_type;

    @ManyToOne()
    @JoinColumn(name="request_id",referencedColumnName="request_id", insertable=false, updatable=false)
    private TransactionRequest request;


    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public float getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(float transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public Date getTransaction_timestamp() {
        return transaction_timestamp;
    }

    public void setTransaction_timestamp(Date transaction_timestamp) {
        this.transaction_timestamp = transaction_timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAccount_no() {
        return account_no;
    }

    public void setAccount_no(Integer account_no) {
        this.account_no = account_no;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public TransactionRequest getRequest() {
        return request;
    }

    public void setRequest(TransactionRequest request) {
        this.request = request;
    }
}