package com.example.banking.bank_app.service;
import com.example.banking.bank_app.model.Help;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface HelpService {

    public List<Help> getAllHelp();

    public Help getCheckByID(Integer helpid);

    public void saveOrUpdate(Help help );

    public void deleteHelp(Integer id);

    public Page<Help> getPaginated(Pageable pageable);
}
