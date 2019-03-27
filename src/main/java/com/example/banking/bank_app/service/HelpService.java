package com.example.banking.bank_app.service;
import com.example.banking.bank_app.model.Help;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface HelpService {

    List<Help> getAllHelp();

    Help getCheckByID(Integer helpid);

    void saveOrUpdate(Help help );

    void deleteHelp(Integer id);

    Page<Help> getPaginated(Pageable pageable);
}
