package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.service.AccountService;
import com.example.banking.bank_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value="/account")
public class  AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value="/list/{page}", method= RequestMethod.GET)
    public ModelAndView list(@PathVariable("page") int page) {
        ModelAndView modelAndView = new ModelAndView("account_list");
        PageRequest pageable = PageRequest.of(page - 1, 15);
        Page<Account> accountPage = accountService.getPaginated(pageable);
        int totalPages = accountPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("accountList", accountPage.getContent());
        Account account = new Account();
        modelAndView.addObject("account", account);
        return modelAndView;
    }

    @RequestMapping(value="/get/{account_no}", method= RequestMethod.GET)
    public Account getAccount(@PathVariable("account_no") Long account_no) {
        return accountService.getAccountByAccountNo(account_no);
    }

    @RequestMapping(value="/edit", method= RequestMethod.POST)
    public ModelAndView editAccount(@Valid Account account) {
        Account oldAccount = accountService.getAccountByAccountNo(account.getAccountNo());
        account.setCreated(oldAccount.getCreated());
        account.setRoutingNo(oldAccount.getRoutingNo());
        account.setUserId(oldAccount.getUserId());
        account.setUpdated(new Timestamp(System.currentTimeMillis()));
        accountService.saveOrUpdate(account);
        return new ModelAndView("redirect:/account/list/1");
    }

    @RequestMapping(value="/delete/{account_no}", method= RequestMethod.POST)
    public ModelAndView deleteAccount(@PathVariable("account_no") Long account_no) {
        accountService.deleteAccount(account_no);
        return new ModelAndView("redirect:/account/list/1");
    }
}
