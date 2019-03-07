package com.example.banking.bank_app.respository;

import com.example.banking.bank_app.model.TransactionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRequestRepository extends CrudRepository<TransactionRequest, Long> {

    Page<TransactionRequest> findAll(Pageable pageable);

}


