package com.example.banking.bank_app.respository;

import com.example.banking.bank_app.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {


}
