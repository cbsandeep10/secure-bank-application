package com.example.banking.bank_app.respository;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.model.AccountRequest;
import com.example.banking.bank_app.model.TransactionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface AccountRequestRepository extends CrudRepository<AccountRequest, Long> {

    Page<AccountRequest> findAll(Pageable pageable);
}
