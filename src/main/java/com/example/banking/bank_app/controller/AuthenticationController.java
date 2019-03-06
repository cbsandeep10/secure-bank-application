package com.example.banking.bank_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class AuthenticationController {

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        // User user = new User();
        // modelAndView.addObject("user", user);
        modelAndView.setViewName("register"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home"); // resources/template/home.html
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin"); // resources/template/admin.html
        return modelAndView;
    }

    @RequestMapping(value = "/tier1", method = RequestMethod.GET)
    public ModelAndView tier1() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tier1"); // resources/template/tier1.html
        return modelAndView;
    }

    @RequestMapping(value = "/tier2", method = RequestMethod.GET)
    public ModelAndView tier2() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tier2"); // resources/template/tier2.html
        return modelAndView;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user"); // resources/template/user.html
        return modelAndView;
    }

    @RequestMapping(value = "/merchant", method = RequestMethod.GET)
    public ModelAndView merchant() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("merchant"); // resources/template/merchant.html
        return modelAndView;
    }




}
