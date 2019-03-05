package com.example.banking.bank_app.service;

import com.example.banking.bank_app.model.Request;
import com.example.banking.bank_app.respository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Override
    public List<Request> getAllRequests() {
        return (List<Request>) requestRepository.findAll();
    }

    @Override
    public Request getRequestByRequestId(Long request_id) {
        return requestRepository.findById(request_id).get();
    }

    @Override
    public void saveOrUpdate(Request request) {
        requestRepository.save(request);
    }

    @Override
    public void deleteRequest(Long request_id) {
        requestRepository.deleteById(request_id);
    }

    @Override
    public Page<Request> getPaginated(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

}