package com.example.banking.bank_app.model;

import javax.persistence.*;

@Entity
@Table(name="checks")

public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkId;

    @Column(name="accountno")
    private Long accountno;

    @Column(name="amount")
    private Float amount;

    @Column(name="routing_no")
    private Long routingNo;
}
