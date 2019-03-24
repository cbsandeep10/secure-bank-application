package com.example.banking.bank_app.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.SerializationUtils;

import javax.persistence.Convert;
import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="account_request")
public class AccountRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long request_id;

    @Column(name="status_id")
    private Integer status_id;

    @Column(name="created_at")
    private Timestamp created_at;

    @Column(name="approved_at")
    private Timestamp approved_at;

    @Column(name="type")
    private int type;

    @Column(name="created_by")
    private Long created_by;

    @Column(name="approved_by")
    private Long approved_by;

    @Column(name="description")
    private String description;

    @Column(name="account")
    private String account;

    @Column(name="user")
    private String user;

    @Column(name="role")
    private Integer role;

    @ManyToOne
    @JoinColumn(name="approved_by", insertable = false, updatable = false)
    private Employee approved_user;

    @ManyToOne(optional=false)
    @JoinColumn(name="created_by",nullable=false, insertable = false, updatable = false)
    private User created_user;

    @Transient
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> userJson;

    @Transient
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> accountJson;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void serializeuser() throws JsonProcessingException {
        this.user = objectMapper.writeValueAsString(userJson);
    }

    public void deserializeuser() throws IOException {
        this.userJson = objectMapper.readValue(user, Map.class);
    }

    public void serializeaccount() throws JsonProcessingException {
        this.account = objectMapper.writeValueAsString(accountJson);
    }

    public void deserializeaccount() throws IOException {
        this.accountJson = objectMapper.readValue(account, Map.class);
    }

    public String getUser() {
        return user;
    }

    public void setUser(Map<String, Object> userJson) {
        this.userJson = userJson;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(Map<String, Object> accountJson) {
        this.accountJson = accountJson;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getUserJson() {
        return userJson;
    }

    public Map<String, Object> getAccountJson() {
        return accountJson;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
