package com.example.banking.bank_app.controller;

import com.example.banking.bank_app.model.Auth_user;
import com.example.banking.bank_app.model.User;
import com.example.banking.bank_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    UserService userService;

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String main() { // resources/template/login.html
        return "redirect:/login";
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        Auth_user user = new Auth_user();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home"); // resources/template/home.html
        Long id = 1000L;
        User user = userService.getUserByUserId(id);
        modelAndView.addObject("user",user);
        return modelAndView;
    }
    @RequestMapping (value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid Auth_user user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();

        //check for the validations
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please Correct the Errors!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if (userService.userAlreadyExist(user)){
            modelAndView.addObject("successMessage", "user already exists!");
        }
        // if no errors, then save user
        else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User Registered Successfully");
        }
        modelAndView.addObject("user", new Auth_user());
        modelAndView.setViewName("register");
        return modelAndView;
    }
}
