package com.example.banking.bank_app.service;
import com.example.banking.bank_app.model.Check;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CheckService {
    public List<Check> getAllChecks();

    public Check getCheckByAccountNo(Long accountNo);

    public void saveOrUpdate(Check check);

    public void deleteCheck(Long id);

    public Page<Check> getPaginated(Pageable pageable);

}
