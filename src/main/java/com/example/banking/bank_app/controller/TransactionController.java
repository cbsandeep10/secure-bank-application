package com.example.banking.bank_app.controller;


import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.model.Config;
import com.example.banking.bank_app.model.Transaction;
import com.example.banking.bank_app.model.Transfer;
import com.example.banking.bank_app.service.AccountService;
import com.example.banking.bank_app.service.TransactionRequestService;
import com.example.banking.bank_app.service.TransactionService;
import com.example.banking.bank_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @RequestMapping(value="/statement/list/{page}", method= RequestMethod.GET)
    public ModelAndView statement_list(@PathVariable("page") int page) {
        ModelAndView modelAndView = new ModelAndView("statement_list");
        PageRequest pageable = PageRequest.of(page - 1, 15);
        Page<Transaction> accountPage = transactionService.getPaginated(pageable);
        int totalPages = accountPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("statementList", accountPage.getContent());
        return modelAndView;
    }

    @GetMapping("/transfer/{type}")
    public String transactionForm(Model model, @PathVariable String type) {
        Transfer transfer = new Transfer();
        model.addAttribute("transfer", transfer);
        model.addAttribute("type", type);
        long id = 1000L;
        model.addAttribute("accounts",userService.getUserByUserId(id).getAccounts());
        return "transaction";
    }

    @RequestMapping(value = "/getaccounts/{type}", method= RequestMethod.POST)
    public List<Account> getAccounts(@Valid Transfer transfer, @PathVariable String type) {
        long account, userId;
        List<Account> accounts = new ArrayList<>() ;
        switch (type) {
            case Config.ACCOUNT:
                account = transfer.getFrom_account_no();
                accounts.add(accountService.getAccountByAccountNo(account));
                break;
            case Config.EMAIL:
                userId = userService.findUserByColumn(transfer.getEmail(), type);
                accounts = accountService.findAccountByUserId(userId);
            case Config.PHONE:
                userId = userService.findUserByColumn(transfer.getPhone(), type);
                accounts = accountService.findAccountByUserId(userId);
        }
        return accounts;
    }

    @RequestMapping(value = "/transfer/{type}", method= RequestMethod.POST)
    public String formSubmit(@Valid Transfer transfer, BindingResult bindingResult, Model model, @PathVariable String type) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getTarget());
            return "redirect:/transfer/"+type;
        }
        //Dont delete the comment
//        long id = 1000L;
//        long account, userId;
//        List<Account> accounts;
//        switch (type){
//            case Config.ACCOUNT:
//                account = transfer.getFrom_account_no();
//                break;
//            case Config.EMAIL:
//                userId = userService.findUserByColumn(transfer.getEmail(), type);
//                accounts = accountService.findAccountByUserId(userId);
//            case Config.PHONE:
//                userId = userService.findUserByColumn(transfer.getPhone(), type);
//                accounts = accountService.findAccountByUserId(userId);
//        }
//        float amount = transfer.getTransfers().get(0).getTransaction_amount();
//        Long request_id = 0L;
//        if(amount > Config.LIMIT){
//            TransactionRequest transactionRequest = new TransactionRequest();
//            transactionRequest.setCreated_by(id);
//            transactionRequest.setFrom_account(transfer.getTransfers().get(0).getAccount_no());
//            transactionRequest.setTo_account(transfer.getTransfers().get(1).getAccount_no());
//            transactionRequest.setStatus_id(Config.PENDING);
//            transactionRequest.setTransaction_amount(amount);
//            transactionRequest.setCreated_at(new Timestamp(System.currentTimeMillis()));
//            request_id = transactionRequestService.saveOrUpdate(transactionRequest).getRequest_id();
//        }
//
//        for(int i=0;i<transfer.getTransfers().size();i++){
//            Long accountId= transfer.getTransfers().get(i).getAccount_no();
//            Transaction transaction = new Transaction();
//            Account account = accountService.getAccountByAccountNo(accountId);
//            transaction.setAccount_no(transfer.getTransfers().get(i).getAccount_no());
//            transaction.setDescription(transfer.getTransfers().get(0).getDescription());
//            transaction.setTransaction_amount(amount);
//            transaction.setBalance(account.getBalance());
//            transaction.setTransaction_timestamp(new Timestamp(System.currentTimeMillis()));
//            if (request_id != 0L){
//                transaction.setRequest_id(request_id);
//                transaction.setStatus(Config.PENDING);
//            }
//            else{
//                transaction.setStatus(Config.APPROVED);
//                if(i==0){
//                    account.setBalance(account.getBalance() - amount);
//                }
//                else {
//                    account.setBalance(account.getBalance() + amount);
//                }
//            }
//            if(i==0){
//                transaction.setTransaction_type(Config.DEBIT);
//            }
//            else {
//                transaction.setTransaction_type(Config.CREDIT);
//            }
//            accountService.saveOrUpdate(account);
//            transactionService.saveOrUpdate(transaction);
//        }
        return "transaction_success";
    }

}
