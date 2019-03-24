package com.example.banking.bank_app.service;
import com.example.banking.bank_app.model.Employee;
import com.example.banking.bank_app.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRespository;

    @Override
    public List<Employee> getAllEmployees() {

        //System.out.println((List<Log>) logRepository.findAll());
        return (List<Employee>) employeeRespository.findAll();
    }

    @Override
    public Page<Employee> getPaginated(Pageable pageable, int tier) {
        return employeeRespository.findAll(pageable, tier);
    }
    @Override
    public void deleteEmployee(Integer id){
        employeeRespository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(Employee employee){
        employeeRespository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Integer id){
        return employeeRespository.findById(id).get();
    }
}
