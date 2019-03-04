package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
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

//    @RequestMapping(value = "/listBooks", method = RequestMethod.GET)
//    public String listBooks(
//            Model model,
//            @RequestParam("page") Optional<Integer> page,
//            @RequestParam("size") Optional<Integer> size) {
//        int currentPage = page.orElse(1);
//        int pageSize = size.orElse(5);
//
//        Page<Book> bookPage = accountService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
//
//        model.addAttribute("bookPage", bookPage);
//
//        int totalPages = bookPage.getTotalPages();
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//
//        return "listBooks.html";
//    }
//
//    @RequestMapping(value="/addAccount/", method= RequestMethod.GET)
//    public Account addAccount() {
//        Account account = new Account();
//        return account;
//    }
//
//    @RequestMapping(value="/updateArticle/{accountNo}", method= RequestMethod.GET)
//    public ModelAndView editArticle(@PathVariable Long accountNo) {
//        ModelAndView model = new ModelAndView("account_form");
//
//        Account account = accountService.getAccountByAccountNo(accountNo);
//        model.addObject("accountForm", account);
//        model.setViewName("account_form");
//
//        return model;
//    }
//
//    @RequestMapping(value="/saveAccount", method= RequestMethod.POST)
//    public ModelAndView save(@ModelAttribute("accountForm") Account account) {
//        accountService.saveOrUpdate(account);
//
//        return new ModelAndView("redirect:/account/list");
//    }
//
//    @RequestMapping(value="/deleteAccount/{accountNo}", method= RequestMethod.GET)
//    public ModelAndView delete(@PathVariable("accountNo") Long accountNo) {
//        accountService.deleteAccount(accountNo);
//
//        return new ModelAndView("redirect:/account/list");
//    }
}
