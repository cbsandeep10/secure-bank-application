package com.example.banking.bank_app.respository;

import com.example.banking.bank_app.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Long> {

    Page<Request> findAll(Pageable pageable);

}


