package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface TransactionService {
    public List<Transaction> getAllTransactions();

    public Transaction getTransactionByTransactionId(Long transfer_id);

    public void saveOrUpdate(Transaction transfer_id);

    public void deleteTransaction(Long transfer_id);

    public Page<Transaction> getPaginated(Pageable pageable);
}


