package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.TransactionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionRequestService {
    public List<TransactionRequest> getAllRequests();

    public TransactionRequest getRequestByRequestId(Long request_id);

    public TransactionRequest saveOrUpdate(TransactionRequest request);

    public void deleteRequest(Long request_id);

    public Page<TransactionRequest> getPaginated(Pageable pageable);

}
