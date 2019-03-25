package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public Page<Employee> getPaginated(Pageable pageable, int tier);

    public void deleteEmployee(Long id);

    public void saveOrUpdate(Employee employee);

    public Employee getEmployeeById(Long id);

    Long findUserByEmail(String email);
}

