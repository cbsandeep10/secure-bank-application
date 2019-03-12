package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.Transaction;

import java.util.List;
public interface TransactionService {
    public List<Transaction> getAllTransactions();

    public Transaction getTransactionByTransactionId(Long transfer_id);

    public void saveOrUpdate(Transaction transfer_id);

    public void deleteTransaction(Long transfer_id);

    public List<Transaction> findAllByAccountNo(Long account_no);

    public List<Transaction> findAllByRequest_id(Long request_id);
}