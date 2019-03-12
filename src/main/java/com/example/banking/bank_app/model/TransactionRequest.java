package com.example.banking.bank_app.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="transaction_request")

public class TransactionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long request_id;

    @Column(name="status_id")
    private Integer status_id;

    @Column(name="created_at")
    private Timestamp created_at;

    @Column(name="approved_at")
    private Timestamp approved_at;

    @Column(name="transaction_amount")
    private float transaction_amount;

    @Column(name="from_account")
    private Long from_account;

    @Column(name="to_account")
    private Long to_account;

    @Column(name="created_by")
    private Long created_by;

    @Column(name="approved_by")
    private Long approved_by;

    @ManyToOne
    @JoinColumn(name="approved_by", insertable = false, updatable = false)
    private Employee approved_user;

    @ManyToOne(optional=false)
    @JoinColumn(name="created_by",nullable=false, insertable = false, updatable = false)
    private User created_user;

    public Long getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Long request_id) {
        this.request_id = request_id;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getApproved_at() {
        return approved_at;
    }

    public void setApproved_at(Timestamp approved_at) {
        this.approved_at = approved_at;
    }

    public Employee getApproved_user() {
        return approved_user;
    }

    public void setApproved_user(Employee approved_user) {
        this.approved_user = approved_user;
    }

    public User getCreated_user() {
        return created_user;
    }

    public void setCreated_user(User created_user) {
        this.created_user = created_user;
    }

    public float getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(float transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public Long getFrom_account() {
        return from_account;
    }

    public void setFrom_account(Long from_account) {
        this.from_account = from_account;
    }

    public Long getTo_account() {
        return to_account;
    }

    public void setTo_account(Long to_account) {
        this.to_account = to_account;
    }

    public Long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Long created_by) {
        this.created_by = created_by;
    }

    public Long getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(Long approved_by) {
        this.approved_by = approved_by;
    }
}