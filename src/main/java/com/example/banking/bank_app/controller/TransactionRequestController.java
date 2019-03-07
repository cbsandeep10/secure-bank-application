package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.TransactionRequest;
import com.example.banking.bank_app.service.TransactionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value="/request")
public class TransactionRequestController {

    @Autowired
    TransactionRequestService transactionRequestService;
    @RequestMapping(value="/list/{page}", method= RequestMethod.GET)
    public ModelAndView list(@PathVariable("page") int page) {
        ModelAndView modelAndView = new ModelAndView("request_list");
        PageRequest pageable = PageRequest.of(page - 1, 10);
        Page<TransactionRequest> requestPage = transactionRequestService.getPaginated(pageable);
        int totalPages = requestPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNums = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNums", pageNums);
        }
        modelAndView.addObject("activeRequestList", true);
        modelAndView.addObject("requestList", requestPage.getContent());
        return modelAndView;
    }}