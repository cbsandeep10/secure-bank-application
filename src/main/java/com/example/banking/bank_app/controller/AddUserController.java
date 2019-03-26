package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.Account;
import com.example.banking.bank_app.model.AddUser;
import com.example.banking.bank_app.model.User;
import com.example.banking.bank_app.service.AccountService;
import com.example.banking.bank_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Timestamp;

@Controller

public class AddUserController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView AddUserForm(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        AddUser user = new AddUser();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("addUser"); // resources/template/register.html
        modelAndView.addObject("message",message);
        return modelAndView;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String formSubmit(@Valid AddUser adduser, BindingResult bindingResult, Model model,Authentication authentication, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message","Please fix the errors");
            return "redirect:/addUser";
        }
        User new_user = new User();
        new_user.setName(adduser.getName());
        new_user.setGender(adduser.getGender());
        new_user.setDob(adduser.getDob());
        new_user.setContact(adduser.getContact());
        new_user.setEmailId(adduser.getEmailId());
        new_user.setAddress(adduser.getAddress());
        new_user.setUserType(1);
        User user=userService.saveOrUpdate(new_user);

        Account account = new Account();
        account.setUserId(user.getUserId());
        account.setBalance(adduser.getBalance());
        account.setRoutingNo(adduser.getRoutingNo());
        account.setAccountType(adduser.getAccountType());
        account.setInterest(adduser.getInterest());
        account.setCreated(new Timestamp(System.currentTimeMillis()));
        account.setUpdated(new Timestamp(System.currentTimeMillis()));
        accountService.saveOrUpdate(account);
        System.out.println("The user & account added successfully");
        redirectAttributes.addFlashAttribute("message","Successfully created");
        return "redirect:/addUser";
    }
}
