package com.example.banking.bank_app.respository;


import com.example.banking.bank_app.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;



public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Page<Employee> findAll(Pageable pageable);
}
