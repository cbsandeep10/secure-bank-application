package com.example.banking.bank_app.model;

import java.util.List;

public class Transfer {
    List<Transaction> transfers;

    public List<Transaction> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transaction> transfers) {
        this.transfers = transfers;
    }
}
