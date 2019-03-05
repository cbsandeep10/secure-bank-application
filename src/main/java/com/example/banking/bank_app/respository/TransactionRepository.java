package com.example.banking.bank_app.respository;

import com.example.banking.bank_app.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{
    Page<Transaction> findAll(Pageable pageable);
}
