package com.example.banking.bank_app.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="request")

public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long request_id;

    @Column(name="request_type")
    private String request_type;

    @Column(name="status_id")
    private String status_id;

    @Column(name="created_by")
    private String created_by;

    @Column(name="created_at")
    //@Temporal(TemporalType.TIMESTAMP)
    private Timestamp created_at;

    @Column(name="approved_by")
    private String approved_by;

    @Column(name="approved_at")
   // @Temporal(TemporalType.TIMESTAMP)
    private Timestamp approved_at;

    public Long getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Long request_id) {
        this.request_id = request_id;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }

    public Timestamp getApproved_at() {
        return approved_at;
    }

    public void setApproved_at(Timestamp approved_at) {
        this.approved_at = approved_at;
    }
}