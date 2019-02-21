package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value="/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value="/list", method= RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("account_list");
        List<Account> accountList = accountService.getAllAccounts();
        model.addObject("accountList", accountList);

        return model;
    }

    @RequestMapping(value="/addAccount/", method= RequestMethod.GET)
    public ModelAndView addAccount() {
        ModelAndView model = new ModelAndView();

        Account account = new Account();
        model.addObject("accountForm", account);
        model.setViewName("account_form");

        return model;
    }

    @RequestMapping(value="/updateArticle/{accountNo}", method= RequestMethod.GET)
    public ModelAndView editArticle(@PathVariable Long accountNo) {
        ModelAndView model = new ModelAndView("account_form");

        Account account = accountService.getAccountByAccountNo(accountNo);
        model.addObject("accountForm", account);
        model.setViewName("account_form");

        return model;
    }

    @RequestMapping(value="/saveAccount", method= RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("accountForm") Account account) {
        accountService.saveOrUpdate(account);

        return new ModelAndView("redirect:/account/list");
    }

    @RequestMapping(value="/deleteAccount/{accountNo}", method= RequestMethod.GET)
    public ModelAndView delete(@PathVariable("accountNo") Long accountNo) {
        accountService.deleteAccount(accountNo);

        return new ModelAndView("redirect:/account/list");
    }
}
