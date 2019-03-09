package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.service.AccountService;
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
@RequestMapping(value="/account")
public class  AccountController {

    @Autowired
    AccountService accountService;

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
        modelAndView.addObject("activeAccountList", true);
        modelAndView.addObject("accountList", accountPage.getContent());
        return modelAndView;
    }


    @RequestMapping(value="/addAccount/", method= RequestMethod.GET)
    public Account addAccount() {
        Account account = new Account();
        return account;
    }
}
