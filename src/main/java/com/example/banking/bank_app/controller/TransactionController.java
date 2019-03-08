package com.example.banking.bank_app.controller;


import com.example.banking.bank_app.model.*;
import com.example.banking.bank_app.service.AccountService;
import com.example.banking.bank_app.service.TransactionRequestService;
import com.example.banking.bank_app.service.TransactionService;
import com.example.banking.bank_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;

@Controller
public class TransactionController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionRequestService transactionRequestService;

    @GetMapping("/transaction")
    public String transactionForm(Model model) {
        Transfer transfer = new Transfer();
        transfer.setTransfers(new ArrayList<>());
        for (int i=0; i<2; i++){
            transfer.getTransfers().add(new Transaction());
        }
        model.addAttribute("transfer", transfer);
        long id = 1000L;
        model.addAttribute("accounts",userService.getUserByUserId(id).getAccounts());
        return "transaction";
    }

    @RequestMapping(value = "/transaction", method= RequestMethod.POST)
    public String formSubmit(@Valid Transfer transfer, BindingResult bindingResult, Model model) {
        long id = 1000L;
        float amount = transfer.getTransfers().get(0).getTransaction_amount();
        Long request_id = 0L;
        if(amount > Config.LIMIT){
            TransactionRequest transactionRequest = new TransactionRequest();
            transactionRequest.setCreated_by(id);
            transactionRequest.setFrom_account(transfer.getTransfers().get(0).getAccount_no());
            transactionRequest.setTo_account(transfer.getTransfers().get(1).getAccount_no());
            transactionRequest.setStatus_id(Config.PENDING);
            transactionRequest.setTransaction_amount(amount);
            transactionRequest.setCreated_at(new Timestamp(System.currentTimeMillis()));
            request_id = transactionRequestService.saveOrUpdate(transactionRequest).getRequest_id();
        }

        for(int i=0;i<transfer.getTransfers().size();i++){
            Long accountId= transfer.getTransfers().get(i).getAccount_no();
            Transaction transaction = new Transaction();
            Account account = accountService.getAccountByAccountNo(accountId);
            transaction.setAccount_no(transfer.getTransfers().get(i).getAccount_no());
            transaction.setDescription(transfer.getTransfers().get(0).getDescription());
            transaction.setTransaction_amount(amount);
            transaction.setBalance(account.getBalance());
            transaction.setTransaction_timestamp(new Timestamp(System.currentTimeMillis()));
            if (request_id != 0L){
                transaction.setRequest_id(request_id);
                transaction.setStatus(Config.PENDING);
            }
            else{
                transaction.setStatus(Config.APPROVED);
                if(i==0){
                    account.setBalance(account.getBalance() - amount);
                }
                else {
                    account.setBalance(account.getBalance() + amount);
                }
            }
            if(i==0){
                transaction.setTransaction_type(Config.DEBIT);
            }
            else {
                transaction.setTransaction_type(Config.CREDIT);
            }
            accountService.saveOrUpdate(account);
            transactionService.saveOrUpdate(transaction);
        }
        if (bindingResult.hasErrors()) {
            return "transaction";
        }
        return "transaction_success";
    }

}
