package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.model.User;
import com.example.banking.bank_app.service.UserService;
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
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    UserService userService;

    /*@RequestMapping(value="/list", method= RequestMethod.GET)
    public ModelAndView list() {
        List<User> userList = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("user_list");
        return modelAndView;
    }
*/
    @RequestMapping(value="/list/{page}", method= RequestMethod.GET)
    public ModelAndView list(@PathVariable("page") int page) {
        ModelAndView modelAndView = new ModelAndView("user_list");
        PageRequest pageable = PageRequest.of(page - 1, 15);
        Page<User> userPage = userService.getPaginated(pageable);
        int totalPages = userPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNos = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNos", pageNos);
        }
        modelAndView.addObject("activeUserList", true);
        modelAndView.addObject("userList", userPage.getContent());
        return modelAndView;
    }

/*    @RequestMapping(value="/list", method= RequestMethod.GET)
    public List<User> list() {
        List<User> userList = userService.getAllUsers();
        return userList;
    }*/
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
