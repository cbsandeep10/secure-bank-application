package com.example.banking.bank_app.respository;

import com.example.banking.bank_app.model.AuthUserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRoleRepository extends CrudRepository<AuthUserRole, Long> {


}
