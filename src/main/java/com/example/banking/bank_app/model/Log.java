package com.example.banking.bank_app.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="admin_log")

public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="type_id")
    private Integer typeID;


    @Column(name="log_timestamp")
    private Timestamp logTimeStamp;

    @Column(name="related_user_id")
    private Integer relatedUserID;

    @Column(name="message")
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public Timestamp getLogTimeStamp() {
        return logTimeStamp;
    }

    public void setLogTimeStamp(Timestamp logTimeStamp) {
        this.logTimeStamp = logTimeStamp;
    }

    public Integer getRelatedUserID() {
        return relatedUserID;
    }

    public void setRelatedUserID(Integer relatedUserID) {
        this.relatedUserID = relatedUserID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
