package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.*;
import com.example.banking.bank_app.service.AccountService;
import com.example.banking.bank_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String formSubmit(@Valid AddUser adduser, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
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
        new_user.setCreated(new Timestamp(System.currentTimeMillis()));
        userService.saveOrUpdate(new_user);

        Account account = new Account();
        account.setUserId(1000L);
        account.setBalance(10);
        account.setRoutingNo(123);
        account.setAccountType(1);

        accountService.saveOrUpdate(account);
        redirectAttributes.addFlashAttribute("message","Successfully created");
        return "redirect:/addUser";
    }
}
