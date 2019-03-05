package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RequestService {
    public List<Request> getAllRequests();

    public Request getRequestByRequestId(Long request_id);

    public void saveOrUpdate(Request request);

    public void deleteRequest(Long request_id);

    public Page<Request> getPaginated(Pageable pageable);

}
