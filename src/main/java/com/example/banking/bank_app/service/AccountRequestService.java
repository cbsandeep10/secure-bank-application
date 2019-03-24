package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.model.AccountRequest;
import com.example.banking.bank_app.model.TransactionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountRequestService {
    public List<AccountRequest> getAllAccounts();

    public AccountRequest getAccountByAccountNo(Long requestid);

    public void saveOrUpdate(AccountRequest accountRequest);

    public void deleteAccount(Long id);

    public Page<AccountRequest> getPaginated(Pageable pageable);

}
