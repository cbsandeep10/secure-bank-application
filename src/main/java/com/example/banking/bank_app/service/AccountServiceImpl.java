package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.respository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    public Account getAccountByAccountNo(Long accountNo) {
        return accountRepository.findById(accountNo).get();
    }

    @Override
    public void saveOrUpdate(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long accountNo) {
        accountRepository.deleteById(accountNo);
    }

}