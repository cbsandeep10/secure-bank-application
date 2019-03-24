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

import javax.validation.Valid;

@Controller

public class AddUserController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView AddUserForm() {
        ModelAndView modelAndView = new ModelAndView();
        AddUser user = new AddUser();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("addUser"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String formSubmit(@Valid AddUser adduser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/addUser";
        }
        //Dont delete the comment
        long id = 1000L;
        User new_user = new User();
        new_user.setName(adduser.getName());
        new_user.setGender(adduser.getGender());
        new_user.setDob(adduser.getDob());
        new_user.setContact(adduser.getContact());
        new_user.setEmailId(adduser.getEmailId());
        new_user.setAddress(adduser.getAddress());
        new_user.setUserType("1");
        userService.saveNewUser(new_user);
        System.out.println("The user added successfully");
        return "user_add_success";
    }
}
