package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.Account;

import java.util.List;

public interface AccountService {
    public List<Account> getAllAccounts();

    public Account getAccountByAccountNo(Long accountNo);

    public void saveOrUpdate(Account account);

    public void deleteAccount(Long id);

}
