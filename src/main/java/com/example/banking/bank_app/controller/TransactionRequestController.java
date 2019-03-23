package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.model.Config;
import com.example.banking.bank_app.model.Transaction;
import com.example.banking.bank_app.model.TransactionRequest;
import com.example.banking.bank_app.service.AccountService;
import com.example.banking.bank_app.service.TransactionRequestService;
import com.example.banking.bank_app.service.TransactionService;
import com.example.banking.bank_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value="/request")
public class TransactionRequestController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionRequestService transactionRequestService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

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
    }

    @RequestMapping(value="/approve/{id}", method= RequestMethod.POST)
    public ModelAndView approve(@PathVariable("id") int id, Authentication authentication) {
        Long userId =  userService.findUserByEmail(authentication.getName());
        TransactionRequest transactionRequest = transactionRequestService.getRequestByRequestId(new Long(id));
        transactionRequest.setApproved_at(new Timestamp(System.currentTimeMillis()));
        transactionRequest.setApproved_by(1L); //Remeber to change this
        transactionRequest.setStatus_id(Config.APPROVED);
        transactionRequestService.saveOrUpdate(transactionRequest);

        if(transactionRequest.getType() == Config.TRANSFER){
            Account from_account = accountService.getAccountByAccountNo(transactionRequest.getFrom_account());
            from_account.setBalance(from_account.getBalance()-transactionRequest.getTransaction_amount());
            Account to_account = accountService.getAccountByAccountNo(transactionRequest.getTo_account());
            to_account.setBalance(to_account.getBalance()+transactionRequest.getTransaction_amount());
            accountService.saveOrUpdate(from_account);
            accountService.saveOrUpdate(to_account);

            List<Transaction> transactions = transactionService.findAllByRequest_id(transactionRequest.getRequest_id());
            for(int i=0;i<transactions.size();i++){
                if(transactions.get(i).getTransaction_type() == Config.DEBIT){
                    transactions.get(i).setBalance(from_account.getBalance());
                }else{
                    transactions.get(i).setBalance(to_account.getBalance());
                }
                transactions.get(i).setStatus(Config.APPROVED);
                transactionService.saveOrUpdate(transactions.get(i));
            }
        }
        else{
            Account account = accountService.getAccountByAccountNo(transactionRequest.getFrom_account());
            if(transactionRequest.getType() == Config.WITHDRAW){
                account.setBalance(account.getBalance()-transactionRequest.getTransaction_amount());
            }else{
                account.setBalance(account.getBalance()+transactionRequest.getTransaction_amount());
            }
            accountService.saveOrUpdate(account);

            List<Transaction> transactions = transactionService.findAllByRequest_id(transactionRequest.getRequest_id());
            for(int i=0;i<transactions.size();i++){
                transactions.get(i).setBalance(account.getBalance());
                transactions.get(i).setStatus(Config.APPROVED);
                transactionService.saveOrUpdate(transactions.get(i));
            }
        }

        return new ModelAndView("redirect:/request/list/1");
    }

    @RequestMapping(value="/decline/{id}", method= RequestMethod.POST)
    public ModelAndView decline(@PathVariable("id") int id) {
        TransactionRequest transactionRequest = transactionRequestService.getRequestByRequestId(new Long(id));
        transactionRequest.setApproved_at(new Timestamp(System.currentTimeMillis()));
        transactionRequest.setApproved_by(1L);
        transactionRequest.setStatus_id(Config.DECLINED);
        transactionRequestService.saveOrUpdate(transactionRequest);
        return new ModelAndView("redirect:/request/list/1");
    }

}