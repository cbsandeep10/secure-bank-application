package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.Transaction;
import com.example.banking.bank_app.respository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionByTransactionId(Long transferid) {
        return transactionRepository.findById(transferid).get();
    }

    @Override
    public void saveOrUpdate(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long transferid) {
        transactionRepository.deleteById(transferid);
    }


    @Override
    public Page<Transaction> getPaginated(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

}