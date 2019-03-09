package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface AccountService {
    public List<Account> getAllAccounts();

    public Account getAccountByAccountNo(Long accountNo);

    public void saveOrUpdate(Account account);

    public void deleteAccount(Long id);

    public Page<Account> getPaginated(Pageable pageable);

    public List<Account> findAccountByUserId(@NotNull final Long userId);

}
