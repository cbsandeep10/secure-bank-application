package com.example.banking.bank_app.service;


import com.example.banking.bank_app.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LogService {

    public List<Log> getAllLogs();

    public Page<Log> getPaginated(Pageable pageable);

}
