package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.model.Config;
import com.example.banking.bank_app.model.User;
import com.example.banking.bank_app.service.AccountService;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

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

    @RequestMapping(value = "/accounts/{type}/{data}", method= RequestMethod.GET)
    public List<Account> getAccounts(@PathVariable String type, @PathVariable String data){
        long userId;
        List<Account> accounts = new ArrayList<>() ;
        if(data!=null){
            switch (type) {
                case Config.EMAIL:
                    userId = userService.findUserByEmail(data);
                    accounts = userService.getUserByUserId(userId).getAccounts();
                    break;
                case Config.PHONE:
                    userId = userService.findUserByPhone(data);
                    accounts = userService.getUserByUserId(userId).getAccounts();
                    break;
            }
        }
        return accounts;
    }

    @RequestMapping(value="/edit", method= RequestMethod.POST)
    public ModelAndView editAccount(@Valid User user, Authentication authentication) {
        Long id =  userService.findUserByEmail(authentication.getName());
        User old_user = userService.getUserByUserId(id);
        old_user.setAddress(user.getAddress());
        old_user.setContact(user.getContact());
        old_user.setEmailId(user.getEmailId());
        old_user.setDob(user.getDob());
        userService.saveOrUpdate(old_user);
        return new ModelAndView("redirect:/home");
    }
}
