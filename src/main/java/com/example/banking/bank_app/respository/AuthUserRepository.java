package com.example.banking.bank_app.respository;

import com.example.banking.bank_app.model.Auth_user;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends CrudRepository<Auth_user, Long> {


}